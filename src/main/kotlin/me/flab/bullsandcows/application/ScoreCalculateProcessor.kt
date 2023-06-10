package me.flab.bullsandcows.application

import me.flab.bullsandcows.application.command.GuessCommand
import me.flab.bullsandcows.application.dto.GuessResultData
import me.flab.bullsandcows.domain.repository.GameRepository
import org.springframework.stereotype.Component

@Component
class ScoreCalculateProcessor(private val gameRepository: GameRepository) {
    fun calculate(command: GuessCommand): GuessResultData {
        val room = gameRepository.findByRoomId(command.roomId)
        val result = room.guess(command.guess)
        return GuessResultData.from(result)
    }
}