package me.flab.bullsandcows.domain.model

import me.flab.bullsandcows.domain.dto.GuessResult
import me.flab.bullsandcows.domain.exception.AlreadyClosedException
import java.time.LocalDateTime

class Game(
    var roomId: Long? = null,
    val scoreBox: ScoreBox,
    var status: Status = Status.IN_PROCESS,
    private val createTime: LocalDateTime? = LocalDateTime.now(),
    private val updateTime: LocalDateTime? = LocalDateTime.now()
) {

   fun guess(guessNumber: String): GuessResult {
       if (this.status == Status.CLOSED) throw AlreadyClosedException()
       val result = scoreBox.measure(guessNumber)
       if (!this.scoreBox.isAvailable() || result.isCorrect())
           this.status = Status.CLOSED
       return result
   }

    enum class Status {
        IN_PROCESS,
        CLOSED,
    }

    companion object {
        fun newInstance(roomId: Long? = null,answer: String, threshold: Int): Game {
            val scoreBox = ScoreBox.newInstance(answer = answer, threshold = threshold)
            return Game(roomId, scoreBox)
        }
    }
}