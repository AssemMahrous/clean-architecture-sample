package net.mobiquity.core.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import net.mobiquity.core.data.remote.RemoteDataSource
import net.mobiquity.core.data.remote.RemoteDataSourceImpl
import net.mobiquity.core.networkError.RxErrorHandlingCallAdapterFactory
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
    fun provideRxErrorHandlingCallAdapterFactory(): RxErrorHandlingCallAdapterFactory =
        RxErrorHandlingCallAdapterFactory.create()
}