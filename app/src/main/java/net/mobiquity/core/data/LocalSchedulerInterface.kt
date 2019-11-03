package net.mobiquity.core.data

import io.reactivex.Scheduler

interface LocalSchedulerInterface {
    fun io(): Scheduler
    fun main(): Scheduler
}