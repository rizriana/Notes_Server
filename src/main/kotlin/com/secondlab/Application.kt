package com.secondlab

import com.secondlab.plugins.configureRouting
import com.secondlab.plugins.configureSecurity
import com.secondlab.plugins.configureSerialization
import com.secondlab.repository.DatabaseFactory
import io.ktor.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureRouting()
    DatabaseFactory.init()
}
