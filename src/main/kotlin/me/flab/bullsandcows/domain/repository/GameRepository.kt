package me.flab.bullsandcows.domain.repository

import me.flab.bullsandcows.domain.model.Game

interface GameRepository {
    fun save(entity: Game): Game
}