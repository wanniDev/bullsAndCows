package me.flab.bullsandcows.presentation.game.response

import me.flab.bullsandcows.application.dto.GuessResultData

data class GameProceedResponse(
    val correct: Boolean,
    val remainingCount: Int,
    val strike: Int,
    val ball: Int,
    val out: Int
) {
    companion object {
        fun from(guessResultData: GuessResultData): GameProceedResponse {
            return GameProceedResponse(
                guessResultData.correct,
                guessResultData.remainingCount,
                guessResultData.strike,
                guessResultData.ball,
                guessResultData.out
            )
        }
    }
}