package me.flab.bullsandcows.infrastructure

import me.flab.bullsandcows.application.AnswerSettingsGenerator
import me.flab.bullsandcows.domain.repository.GameRepository
import me.flab.bullsandcows.infrastructure.memory.InMemoryGameRepositoryAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GameModuleConfiguration {
    @Bean
    fun gameRepository(): GameRepository {
        return InMemoryGameRepositoryAdapter()
    }

    @Bean
    fun answerSettingsGenerator(): AnswerSettingsGenerator {
        return SimpleAnswerSettingsGenerator(10)
    }
}