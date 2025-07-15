package com.example

import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.modelcontextprotocol.kotlin.sdk.CallToolResult
import io.modelcontextprotocol.kotlin.sdk.Implementation
import io.modelcontextprotocol.kotlin.sdk.ServerCapabilities
import io.modelcontextprotocol.kotlin.sdk.TextContent
import io.modelcontextprotocol.kotlin.sdk.Tool
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
  server.addTool(
    name = "kotlin-sdk-tool",
    description = "A test tool",
    inputSchema = Tool.Input()
  ) { request ->
    CallToolResult(
      content = listOf(TextContent("Hello, world!"))
    )
  }


//  server.addPrompt(
//    name = "Kotlin Developer",
//    description = "Develop small kotlin applications",
//    arguments = listOf(
//      PromptArgument(
//        name = "Project Name",
//        description = "Project name for the new project",
//        required = true
//      )
//    )
//  ) { request ->
//    GetPromptResult(
//      "Description for ${request.name}",
//      messages = listOf(
//        PromptMessage(
//          role = Role.user,
//          content = TextContent("Develop a kotlin project named <name>${request.arguments?.get("Project Name")}</name>")
//        )
//      )
//    )
//  }

//  // Add a resource
//  server.addResource(
//    uri = "https://search.com/",
//    name = "Web Search",
//    description = "Web search engine",
//    mimeType = "text/html"
//  ) { request ->
//    ReadResourceResult(
//      contents = listOf(
//        TextResourceContents("Placeholder content for ${request.uri}", request.uri, "text/html")
//      )
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