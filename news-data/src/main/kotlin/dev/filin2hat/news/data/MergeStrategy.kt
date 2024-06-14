package dev.filin2hat.news.data

interface MergeStrategy<T> {
    fun merge(
        cache: T,
        server: T,
    ): T
}

internal class RequestResultMergeStrategy<T : Any> : MergeStrategy<RequestResult<T>> {
    override fun merge(
        cache: RequestResult<T>,
        server: RequestResult<T>,
    ): RequestResult<T> =
        when {
            cache is RequestResult.InProgress && server is RequestResult.InProgress -> {
                merge(cache, server)
            }

            cache is RequestResult.Success && server is RequestResult.InProgress -> {
                merge(cache, server)
            }

            cache is RequestResult.InProgress && server is RequestResult.Success -> {
                merge(cache, server)
            }

            cache is RequestResult.Success && server is RequestResult.Error -> {
                merge(cache, server)
            }

            else -> error("Unimplemented branch")
        }

    private fun merge(
        cache: RequestResult.InProgress<T>,
        server: RequestResult.InProgress<T>,
    ): RequestResult<T> =
        when {
            cache.data != null -> RequestResult.InProgress(cache.data)
            else -> RequestResult.InProgress(server.data)
        }

    private fun merge(
        cache: RequestResult.Success<T>,
        server: RequestResult.InProgress<T>,
    ): RequestResult<T> = RequestResult.InProgress(server.data)

    private fun merge(
        cache: RequestResult.InProgress<T>,
        server: RequestResult.Success<T>,
    ): RequestResult<T> = RequestResult.InProgress(cache.data)

    private fun merge(
        cache: RequestResult.Success<T>,
        server: RequestResult.Error<T>,
    ): RequestResult<T> = RequestResult.Error(data = cache.data, error = server.error)
}
