package me.flab.bullsandcows.domain.exception

import me.flab.bullsandcows.common.ErrorCode
import me.flab.bullsandcows.common.ServiceException

class RoomNotFoundException: ServiceException(ErrorCode.ROOM_NOT_FOUND) {
}