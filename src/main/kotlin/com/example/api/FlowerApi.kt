package com.example.api

import com.example.Output
import com.example.model.Flower
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.flowerApi() {
    route("api/flowers", Route::getFlowers)
    route("api/flowers/{flower}", Route::getFlowerById)
    route("api/add-flower", Route::postFlower)
}

private fun Route.getFlowers() {
    get {
        call.respond(
            HttpStatusCode.OK,
            Output.flowerList
        )
    }
}

private fun Route.getFlowerById() {
    get {
        val id = call.parameters["flower"]
        id?.let { flower ->
            Output.flowerList.find { it.id == flower.toInt() }?.let {
                call.respond(HttpStatusCode.OK, it)
            }
        } ?: call.respond(HttpStatusCode.NoContent)
    }
}

private fun Route.postFlower() {
    post {
        val payload = call.receive<Flower>()
        Output.flowerList.add(payload)

        call.respond(HttpStatusCode.OK, payload)
    }
}