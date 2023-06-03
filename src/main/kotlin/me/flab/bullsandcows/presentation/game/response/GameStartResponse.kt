package me.flab.bullsandcows.presentation.game.response

import me.flab.bullsandcows.application.dto.GameData

data class GameStartResponse(val roomId: Long) {
    companion object {
        fun from(newGameData: GameData): GameStartResponse {
            return GameStartResponse(newGameData.roomId)
        }
    }
}
