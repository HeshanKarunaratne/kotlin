package kotlin_classes

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Failure(val ex: Exception) : ApiResponse<Nothing>()
    data object Processing : ApiResponse<Nothing>()
}