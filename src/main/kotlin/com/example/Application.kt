package com.example

import com.example.api.flowerApi
import com.example.model.Flower
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import java.util.ArrayList

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    routing {
        flowerApi()
    }
}

object Output {
    var flowerList: ArrayList<Flower> = arrayListOf(
        Flower(id = 1, name = "Rose", color = "Red", type = "Perennial"),
        Flower(id = 2, name = "Tulip", color = "Yellow", type = "Perennial"),
        Flower(id = 3, name = "Daisy", color = "White", type = "Annual"),
        Flower(id = 4, name = "Orchid", color = "Purple", type = "Perennial"),
        Flower(id = 5, name = "Sunflower", color = "Yellow", type = "Annual"),
        Flower(id = 6, name = "Lily", color = "Pink", type = "Perennial"),
    )
}