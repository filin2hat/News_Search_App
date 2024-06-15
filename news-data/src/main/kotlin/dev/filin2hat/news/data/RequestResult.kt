package dev.filin2hat.news.data

/**
 * Generic-класс для получения результатов запросов
 */
sealed class RequestResult<out E : Any>(open val data: E? = null) {
    class InProgress<E : Any>(data: E? = null) : RequestResult<E>(data)

    class Success<E : Any>(override val data: E) : RequestResult<E>(data)

    class Error<E : Any>(data: E? = null, val error: Throwable? = null) : RequestResult<E>(data)
}

/**
 * Функция преобразования RequestResult в другой тип
 */
fun <I : Any, O : Any> RequestResult<I>.map(mapper: (I) -> O): RequestResult<O> = when (this) {
    is RequestResult.Success -> RequestResult.Success(mapper(data))

    is RequestResult.Error -> RequestResult.Error(data?.let(mapper))

    is RequestResult.InProgress -> RequestResult.InProgress(data?.let(mapper))
}

/**
 * Функция для преобразования Result в RequestResult
 */
internal fun <T : Any> Result<T>.toRequestResult(): RequestResult<T> = when {
    isSuccess -> RequestResult.Success(getOrThrow())

    isFailure -> RequestResult.Error()

    else -> error("Impossible condition")
}
