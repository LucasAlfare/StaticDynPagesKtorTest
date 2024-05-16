package com.lucasalfare

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
  embeddedServer(Netty, port = 80) {
    install(CORS) {
      anyHost()
      allowHeader(HttpHeaders.ContentType)
    }

    routing {
      get("/") {
        call.respondText("This text is not from an HTML file. Go to the endpoint '/home' to see a static HTML file served by this backend.")
      }

      staticResources(
        remotePath = "/home",
        basePackage = "assets",
        index = "pages/my_home_page.html"
      )
    }
  }.start(true)
}