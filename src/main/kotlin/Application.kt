package com.example

import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.modelcontextprotocol.kotlin.sdk.Implementation
import io.modelcontextprotocol.kotlin.sdk.ServerCapabilities
import io.modelcontextprotocol.kotlin.sdk.server.Server
import io.modelcontextprotocol.kotlin.sdk.server.ServerOptions
import io.modelcontextprotocol.kotlin.sdk.server.mcp

fun configureServer(): Server {
  val server = Server(
    Implementation(
      name = "mcp-kotlin test server",
      version = "0.1.0"
    ),
    ServerOptions(
      capabilities = ServerCapabilities(
        prompts = ServerCapabilities.Prompts(listChanged = true),
        resources = ServerCapabilities.Resources(subscribe = true, listChanged = true),
        tools = ServerCapabilities.Tools(listChanged = true),
      )
    )
  )

  // Add a tool
//  server.addTool(
//    name = "kotlin-sdk-tool",
//    description = "A test tool",
//    inputSchema = Tool.Input()
//  ) { request ->
//    CallToolResult(
//      content = listOf(TextContent("Hello, world!"))
//    )
//  }

  return server
}

fun main(): Unit {
  val port = 8080
  println("Starting sse server on port $port")
  embeddedServer(CIO, host = "0.0.0.0", port = port) {
    mcp {
      return@mcp configureServer()
    }
  }.start(wait = true)
}