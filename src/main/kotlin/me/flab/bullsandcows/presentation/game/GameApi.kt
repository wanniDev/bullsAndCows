package me.flab.bullsandcows.presentation.game

import me.flab.bullsandcows.application.GameStartProcessor
import me.flab.bullsandcows.common.ApiResult
import me.flab.bullsandcows.presentation.game.response.GameStartResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("game")
class GameApi(private val gameStartProcessor: GameStartProcessor) {
    @PostMapping(path = ["start"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun gameStart(): ResponseEntity<ApiResult<GameStartResponse>> {
        val newGameData = gameStartProcessor.startNewGame()
        val payload = GameStartResponse.from(newGameData)
        val apiResult = ApiResult.success(payload)
        return ResponseEntity.ok(apiResult)
    }
}