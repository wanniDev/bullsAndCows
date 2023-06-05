package me.flab.bullsandcows.application.command

data class GuessCommand(
    val roomId: Long,
    val guess: String
) {
    companion object {
        fun toCommand(roomId: Long, guess: String): GuessCommand {
            return GuessCommand(roomId, guess)
        }
    }
}
