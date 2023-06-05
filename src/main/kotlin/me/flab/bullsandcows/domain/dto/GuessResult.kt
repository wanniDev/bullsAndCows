package me.flab.bullsandcows.domain.dto

data class GuessResult(
    val remaining: Int,
    val ball: Int,
    val strike: Int,
    val out: Int,
) {
    fun isCorrect(): Boolean {
        return strike == 3;
    }
}