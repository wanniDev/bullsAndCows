package me.flab.bullsandcows.infrastructure.memory

import me.flab.bullsandcows.domain.exception.RoomNotFoundException
import me.flab.bullsandcows.domain.model.Game
import me.flab.bullsandcows.domain.repository.GameRepository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

class InMemoryGameRepositoryAdapter: GameRepository {
    private val collection = ConcurrentHashMap<Long, Game>()
    private val keyGenerator = AtomicLong(0)

    override fun save(entity: Game): Game {
        val roomId = keyGenerator.addAndGet(1)
        entity.roomId = roomId
        collection[roomId] = entity
        return entity
    }

    override fun findByRoomId(roomId: Long): Game {
        return collection[roomId] ?: throw RoomNotFoundException()
    }
}