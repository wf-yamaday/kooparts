package org.kooparts.helper

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respondOk

fun mockHttpClientEngine() = MockEngine{
    respondOk()
}

fun mockHttpClient(
    mockEngine: MockEngine = mockHttpClientEngine()
): HttpClient = HttpClient(mockEngine)
