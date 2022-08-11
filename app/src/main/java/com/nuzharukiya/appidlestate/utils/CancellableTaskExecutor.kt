package com.nuzharukiya.appidlestate.utils

import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.logging.Level
import java.util.logging.Logger

class CancellableTaskExecutor(
    private val worker: ScheduledExecutorService,
    private val log: Logger
) {

    fun schedule(task: Runnable, delay: Long, unit: TimeUnit?): Execution {
        val runnable = CancellableRunnable(task)
        val future = worker.schedule(runnable, delay, unit)
        return Execution(future, runnable)
    }

    inner class Execution(
        private val future: ScheduledFuture<*>,
        private val runnable: CancellableRunnable
    ) {
        /**
         * @return true when the task has been successfully cancelled and it's guaranteed that
         * the task won't get executed. otherwise false
         */
        fun cancel(): Boolean {
            val cancelled = runnable.cancel()

            // the return value of this call is unreliable; see https://stackoverflow.com/q/55922874/3591946
            future.cancel(false)
            return cancelled
        }
    }

    inner class CancellableRunnable(private val task: Runnable) :
        Runnable {
        private val cancelledOrStarted = AtomicBoolean()
        override fun run() {
            if (!cancelledOrStarted.compareAndSet(false, true)) {
                return  // cancelled, forget about the task
            }
            try {
                task.run()
            } catch (e: Throwable) {
                log.log(Level.WARNING, "Uncaught Exception", e)
            }
        }

        fun cancel(): Boolean {
            return cancelledOrStarted.compareAndSet(false, true)
        }
    }
}