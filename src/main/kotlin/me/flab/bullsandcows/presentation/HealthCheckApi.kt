package me.flab.bullsandcows.presentation

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class HealthCheckApi {
    @GetMapping(path = ["curr/mills"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCurrentMills() = hashMapOf(Pair("currentMilliseconds", System.currentTimeMillis()))
}