package me.flab.bullsandcows.presentation.game

import me.flab.bullsandcows.common.ApiResult
import me.flab.bullsandcows.presentation.game.response.GameStartResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("game")
class GameApi {
    @PostMapping(path = ["start"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun gameStart(): ResponseEntity<ApiResult<GameStartResponse>> {
        val apiResult = ApiResult.success(GameStartResponse(1L))
        return ResponseEntity.ok(apiResult)
    }
}