package org.kooparts.core.runner

import org.kooparts.helper.mockHttpClientEngine
import org.kooparts.helper.workflowFactory
import kotlin.test.Test
import kotlin.test.assertEquals

class WorkflowRunnerTest {

    @Test
    fun testRun() {
        val mockEngine = mockHttpClientEngine()
        val workflow = workflowFactory()

        val result = run(workflow = workflow, httpClientEngine = mockEngine)

        assertEquals(workflow.name, result.name)
        assertEquals(workflow.testJobs.size, result.testResults.size)
    }
}