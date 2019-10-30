package net.mobiquity.core.utils

sealed class Status<T> {

    data class Error(val error: String? = null, val errorRes: Int? = null) :
        Status<Nothing>()

    data class Loading(val show: Boolean = false) : Status<Nothing>()
}

class ApplicationException(var error: Status.Error) : RuntimeException()
