package com.example

import kotlin.test.Test
import kotlin.test.assertNotNull

class ApplicationTest {

  @Test
  fun testServerConfiguration() {
    val server = configureServer()
    assertNotNull(server)
  }

}
