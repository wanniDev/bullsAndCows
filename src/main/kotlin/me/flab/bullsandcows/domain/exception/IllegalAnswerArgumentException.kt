package me.flab.bullsandcows.domain.exception

import me.flab.bullsandcows.common.ErrorCode
import me.flab.bullsandcows.common.ServiceException

class IllegalAnswerArgumentException: ServiceException(ErrorCode.INVALID_ANSWER)