package net.mobiquity.core.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.mobiquity.feature.detail.DetailFragment
import net.mobiquity.feature.home.HomeFragment
import net.mobiquity.main.MainActivity

@Suppress("unused")
@Module
abstract class Modules {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment
}
