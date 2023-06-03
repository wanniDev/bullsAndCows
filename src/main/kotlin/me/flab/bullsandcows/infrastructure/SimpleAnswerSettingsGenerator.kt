package me.flab.bullsandcows.infrastructure

import me.flab.bullsandcows.application.AnswerSettingsGenerator
import me.flab.bullsandcows.application.dto.AnswerSettings

class SimpleAnswerSettingsGenerator(
    private val threshold: Int,
): AnswerSettingsGenerator {
    override fun generate(): AnswerSettings {
        return AnswerSettings(createRandom(), threshold)
    }

    private fun createRandom(): String {
        val dic = IntArray(10)
        val sb = StringBuilder()

        while (sb.length < 3) {
            val randomInt = (0 until 10).random()
            dic[randomInt]++
            if (dic[randomInt] <= 1) {
                sb.append(randomInt)
            }
        }

        return sb.toString()
    }
}