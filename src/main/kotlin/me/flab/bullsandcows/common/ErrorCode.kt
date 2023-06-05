package me.flab.bullsandcows.common

enum class ErrorCode(
    val message: String,
    val status: Int
) {
    ALREADY_CLOSED("이미 종료된 게임입니다.", 400),
    INVALID_ANSWER("점수판의 정답을 생성하던 중 문제가 발생했습니다.", 500),
    ROOM_NOT_FOUND("해당 방을 찾을 수 없습니다.", 404)
}