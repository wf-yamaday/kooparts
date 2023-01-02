package org.kooparts.core.runner

import kotlinx.coroutines.runBlocking
import org.kooparts.core.StepResult
import org.kooparts.helper.mockHttpClient
import org.kooparts.helper.stepFactory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StepRunnerTest {

    @Test
    fun testRun() = runBlocking {
        val client = mockHttpClient()
        val step = stepFactory()

        val result: StepResult = run(step = step, client = client)

        assertTrue(result.passed)
        assertEquals(step.name, result.name)
    }
}
