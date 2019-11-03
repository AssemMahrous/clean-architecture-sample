package net.mobiquity.core.data

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocalScheduler : LocalSchedulerInterface {
    override fun io(): Scheduler = Schedulers.io()

    override fun main(): Scheduler =
        AndroidSchedulers.mainThread()
}