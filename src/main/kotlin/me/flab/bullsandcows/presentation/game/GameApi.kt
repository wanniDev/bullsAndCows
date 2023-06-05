package me.flab.bullsandcows.presentation.game

import me.flab.bullsandcows.application.GameStartProcessor
import me.flab.bullsandcows.application.ScoreCalculateProcessor
import me.flab.bullsandcows.application.command.GuessCommand
import me.flab.bullsandcows.common.ApiResult
import me.flab.bullsandcows.presentation.game.response.GameProceedResponse
import me.flab.bullsandcows.presentation.game.response.GameStartResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("game")
class GameApi(private val gameStartProcessor: GameStartProcessor,
    private val scoreCalculateProcessor: ScoreCalculateProcessor
) {
    @PostMapping(path = ["start"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun gameStart(): ResponseEntity<ApiResult<GameStartResponse>> {
        val newGameData = gameStartProcessor.startNewGame()
        val payload = GameStartResponse.from(newGameData)
        val apiResult = ApiResult.success(payload)
        return ResponseEntity.ok(apiResult)
    }

    @PostMapping(path = ["{roomId}/answer"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun proceedGame(@PathVariable roomId: String, @RequestBody guessRequest: GuessRequest): ResponseEntity<ApiResult<GameProceedResponse>> {
        val guessResult = scoreCalculateProcessor.calculate(GuessCommand.toCommand(roomId.toLong(), guessRequest.guess))
        val payload = GameProceedResponse.from(guessResult)
        val apiResult = ApiResult.success(payload)
        return ResponseEntity.ok(apiResult)
    }

    
}