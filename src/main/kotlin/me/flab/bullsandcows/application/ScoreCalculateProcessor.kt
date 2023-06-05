package me.flab.bullsandcows.application

import me.flab.bullsandcows.application.command.GuessCommand
import me.flab.bullsandcows.application.dto.GuessResultData
import me.flab.bullsandcows.domain.repository.GameRepository
import org.springframework.stereotype.Component

@Component
class ScoreCalculateProcessor(private val gameRepository: GameRepository) {
    fun calculate(command: GuessCommand): GuessResultData {
        val roomData = gameRepository.findByRoomId(command.roomId)
        val result = roomData.scoreBox.measure(command.guess)
        return GuessResultData.from(result)
    }
}