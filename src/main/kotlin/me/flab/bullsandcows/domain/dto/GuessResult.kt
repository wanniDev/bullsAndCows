package me.flab.bullsandcows.domain.dto

data class GuessResult(
    val bulls: Int,
    val cows: Int,
    val out: Int,
) {
    fun isCorrect(): Boolean {
        return cows == 3;
    }
}