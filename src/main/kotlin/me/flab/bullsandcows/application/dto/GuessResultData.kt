package me.flab.bullsandcows.application.dto

import me.flab.bullsandcows.domain.dto.GuessResult

data class GuessResultData(
    val correct: Boolean,
    val remainingCount: Int,
    val strike: Int,
    val ball: Int,
    val out: Int
) {
    companion object {
        fun from(guessResult: GuessResult): GuessResultData {
            return GuessResultData(
                guessResult.isCorrect(),
                guessResult.remaining,
                guessResult.cows,
                guessResult.bulls,
                guessResult.out
            )
        }
    }
}
