package me.flab.bullsandcows.application

import me.flab.bullsandcows.application.dto.AnswerSettings

interface AnswerSettingsGenerator {
    fun generate(): AnswerSettings
}