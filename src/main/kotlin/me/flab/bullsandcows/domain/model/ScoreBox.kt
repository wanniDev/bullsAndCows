package me.flab.bullsandcows.domain.model

import me.flab.bullsandcows.domain.dto.GuessResult
import me.flab.bullsandcows.domain.exception.IllegalAnswerArgumentException

class ScoreBox private constructor(
    val answer: String,
    var tryCount: Int = 0,
    val threshold: Int = 10,
) {
    fun measure(guessNumber: String): GuessResult {
        var ball = 0
        var strike = 0
        val dic = IntArray(10)

        this.tryCount.plus(1)

        // cows 측정
        for (i in answer.indices) {
            val ac = answer[i]
            val gc = guessNumber[i]

            if (ac == gc) {
                strike++
            } else {
                dic[ac - '0']++
            }
        }

        // bulls 측정
        for (i in guessNumber.indices) {
            val ac = answer[i]
            val gc = guessNumber[i]

            if (ac != gc) {
                if (dic[gc - '0'] > 0) {
                    ball++
                    dic[gc - '0']--
                }
            }
        }

        return GuessResult(this.threshold - this.tryCount, ball, strike, 3 - (ball + strike))
    }

    fun isAvailable(): Boolean {
        return this.tryCount < this.threshold
    }

    companion object {
        fun newInstance(answer: String, threshold: Int): ScoreBox {
            validateAnswer(answer)
            return ScoreBox(
                answer = answer,
                threshold = threshold
            )
        }

        private fun validateAnswer(answer: String) {
            val dic = IntArray(10)
            for (i in answer.indices) {
                val ans = answer[i]
                dic[ans - '0']++
                if (dic[ans - '0'] > 1) {
                    throw IllegalAnswerArgumentException()
                }
            }
        }
    }
}