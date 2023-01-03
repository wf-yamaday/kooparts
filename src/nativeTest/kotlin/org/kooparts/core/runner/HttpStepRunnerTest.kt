package org.kooparts.core.runner

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.client.engine.mock.respondOk
import kotlinx.coroutines.runBlocking
import org.kooparts.core.HTTPStepResult
import org.kooparts.helper.httpStepCheckFactory
import org.kooparts.helper.httpStepFactory
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HttpStepRunnerTest {

    @Test
    fun testRunWithExpectedStatusIsOK() = runBlocking {
        val mockEngine = MockEngine { _ ->
            respondOk()
        }
        val client = HttpClient(mockEngine)
        val httpStep = httpStepFactory(check = httpStepCheckFactory(status = "200"))

        val result: HTTPStepResult = run(httpStep = httpStep, client = client)

        assertTrue(result.status.passed)
    }

    @Test
    fun testRunWithExpectedStatusIsBadRequest() = runBlocking {
        val mockEngine = MockEngine { _ ->
            respondBadRequest()
        }
        val client = HttpClient(mockEngine)
        val httpStep = httpStepFactory(check = httpStepCheckFactory(status = "400"), method = "POST")

        val result: HTTPStepResult = run(httpStep = httpStep, client = client)

        assertTrue(result.status.passed)
    }

    @Test
    fun testRunWithIncorrectStatus() = runBlocking {
        val mockEngine = MockEngine {
            respondBadRequest()
        }
        val client = HttpClient(mockEngine)
        val httpStep = httpStepFactory(check = httpStepCheckFactory(status = "200"), method = "POST")

        val result: HTTPStepResult = run(httpStep = httpStep, client = client)

        assertFalse(result.status.passed)
    }
}
