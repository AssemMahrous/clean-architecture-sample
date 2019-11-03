package net.mobiquity.core.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import net.mobiquity.core.data.LocalScheduler
import net.mobiquity.core.data.LocalSchedulerInterface
import net.mobiquity.core.data.local.LocalDataSource
import net.mobiquity.core.data.local.LocalDataSourceImpl
import net.mobiquity.core.data.remote.RemoteDataSource
import net.mobiquity.core.data.remote.RemoteDataSourceImpl
import net.mobiquity.core.utils.ConnectivityUtils
import net.mobiquity.core.utils.IConnectivityUtils
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, ViewModelModule::class, RepositoriesModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
            .newBuilder()
            // add any type converter or date format here
            .create()
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(retrofit: Retrofit): RemoteDataSource =
        RemoteDataSourceImpl(retrofit)

    @Singleton
    @Provides
    fun provideLocalDataSource(): LocalDataSource = LocalDataSourceImpl()

    @Singleton
    @Provides
    fun provideConnectivityUtils(context: Context): IConnectivityUtils = ConnectivityUtils(context)

    @Singleton
    @Provides
    fun provideLocalScheduler(): LocalSchedulerInterface =
        LocalScheduler()
}