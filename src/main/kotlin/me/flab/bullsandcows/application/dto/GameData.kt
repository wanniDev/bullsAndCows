package me.flab.bullsandcows.application.dto

import me.flab.bullsandcows.domain.model.Game

data class GameData(val roomId: Long,
                    val answer: String,
                    val tryCount: Int,
                    val threshold: Int,
                    val status: Game.Status
) {
    companion object {
        fun from(game: Game): GameData {
            return GameData(
                game.roomId!!,
                game.scoreBox.answer,
                game.scoreBox.tryCount,
                game.scoreBox.threshold,
                game.status
            )
        }
    }
}
