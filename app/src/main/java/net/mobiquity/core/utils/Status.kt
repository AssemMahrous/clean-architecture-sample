package net.mobiquity.core.utils

sealed class Status<T> {
    data class Error(
        val errorType: ErrorType,
        val error: String? = null,
        val errorRes: Int? = null
    ) :
        Status<Nothing>()

    data class Loading(val show: Boolean = false) : Status<Nothing>()
}

class ApplicationException(var error: Status.Error) : RuntimeException()

sealed class ErrorType {
    object Unexpected : ErrorType()
    object NetworkError : ErrorType()
    object NoInternetConnection : ErrorType()
}
