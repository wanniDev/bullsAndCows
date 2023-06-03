package me.flab.bullsandcows.application

import me.flab.bullsandcows.application.dto.GameData
import me.flab.bullsandcows.domain.model.Game
import me.flab.bullsandcows.domain.repository.GameRepository
import org.springframework.stereotype.Component

@Component
class GameStartProcessor(
    private val gameRepository: GameRepository,
    private val answerSettingsGenerator: AnswerSettingsGenerator,
    ) {
    fun startNewGame(): GameData {
        val settings = answerSettingsGenerator.generate()
        val newGame = Game.newInstance(answer = settings.answer, threshold = settings.threshold)
        val result = gameRepository.save(newGame)
        return GameData.from(result)
    }
}