package me.flab.bullsandcows.common

class ApiResult<T> private constructor(val data: T, val success: Boolean) {
    companion object {
        fun <T> success(data: T): ApiResult<T> {
            return ApiResult(data, true)
        }

        fun error(errorCode: ErrorCode): ApiResult<ErrorBody> {
            return ApiResult(
                ErrorBody(errorCode.name, errorCode.message),
                false
            )
        }

        fun error(errorCode: ErrorCode,
                  exception: Exception
        ): ApiResult<ErrorBody> {
            return ApiResult(
                ErrorBody(errorCode.name, exception.message!!),
                false
            )
        }

        class ErrorBody(val code: String, val message: String)
    }
}