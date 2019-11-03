package net.mobiquity

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import net.mobiquity.core.data.LocalSchedulerInterface

class TestLocalScheduler : LocalSchedulerInterface {
    override fun io(): Scheduler = Schedulers.trampoline()

    override fun main(): Scheduler = Schedulers.trampoline()
}