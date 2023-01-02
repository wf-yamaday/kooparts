package org.kooparts.core.runner

import org.kooparts.helper.mockHttpClientEngine
import org.kooparts.helper.testJobFactory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestJobRunnerTest {

    @Test
    fun testRun() {
        val mockEngine = mockHttpClientEngine()
        val testJob = testJobFactory()

        val result = run(testJob=testJob, httpClientEngine = mockEngine)

        assertEquals(testJob.name, result.name)
        assertEquals(testJob.steps.size, result.stepResults.size)
        assertTrue(result.passed)
    }
}