package net.mobiquity.core.platform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import net.mobiquity.R
import net.mobiquity.core.networkError.RetrofitException
import net.mobiquity.core.utils.*
import timber.log.Timber
import java.net.HttpURLConnection
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    @Inject
    lateinit var connectivityUtils: IConnectivityUtils

    private val compositeDisposable = CompositeDisposable()
    private val _status = MutableLiveData<Status<*>>()
    val status: LiveData<Event<Status<*>>> = Transformations
        .switchMap(_status) { MutableLiveData(Event(it)) }

    private fun handleApplicationError(exception: Throwable) {
        when (exception) {
            is RetrofitException -> when (exception.getKind()) {
                RetrofitException.Kind.NETWORK ->
                    _status.postValue(
                        Status.Error(
                            ErrorType.NoInternetConnection,
                            errorRes = R.string.network_error_no_connection
                        )
                    )

                RetrofitException.Kind.HTTP -> {
                    exception.getResponse()?.let {
                        when {
                            it.code() == HttpURLConnection.HTTP_NOT_FOUND -> _status.postValue(
                                Status.Error(
                                    ErrorType.NetworkError,
                                    errorRes = R.string.network_error_not_found
                                )
                            )
                            else -> isDevelopmentDebug { TODO("wrong path") }
                        }
                    } ?: run {
                        _status.postValue(Status.Error(ErrorType.NetworkError, exception.message))
                    }
                }

                RetrofitException.Kind.UNEXPECTED ->
                    _status.postValue(
                        Status.Error(
                            ErrorType.Unexpected,
                            errorRes = R.string.generic_error
                        )
                    )
            }
            is ApplicationException -> _status.postValue(exception.error)
            else -> {
                _status.postValue(
                    Status.Error(
                        ErrorType.Unexpected,
                        errorRes = R.string.generic_error
                    )
                )
            }
        }
    }

    fun <T> singleNetworkCall(
        single: Single<T>,
        success: Consumer<T>,
        error: Consumer<Throwable> = Consumer { },
        subscribeScheduler: Scheduler = Schedulers.io(),
        observeOnMainThread: Boolean = true,
        showLoading: Boolean = true
    ) {
        if (connectivityUtils.isNetworkConnected.not()) {
            handleApplicationError(
                RetrofitException(
                    null,
                    null,
                    null,
                    RetrofitException.Kind.NETWORK,
                    null,
                    null
                )
            )
            return
        }
        val observerScheduler =
            if (observeOnMainThread) AndroidSchedulers.mainThread()
            else subscribeScheduler

        compositeDisposable.add(
            single
                .subscribeOn(subscribeScheduler)
                .observeOn(observerScheduler)
                .compose { composeSingle<T>(it, showLoading) }
                .subscribe(success, error))
    }

    private fun <T> composeSingle(single: Single<T>, showLoading: Boolean = true): Single<T> {
        return single
            .flatMap { item ->
                Single.just(item)
                //todo response based on retrieved request
            }
            .doOnError {
                Timber.e(it)
                handleApplicationError(it)
            }
            .doOnSubscribe {
                _status.postValue(Status.Loading(showLoading))
            }
            .doAfterTerminate {
                _status.postValue(Status.Loading(false))
            }
    }

    private fun clearSubscription() {
        if (compositeDisposable.isDisposed.not()) compositeDisposable.clear()
    }

    override fun onCleared() {
        clearSubscription()
        super.onCleared()
    }
}