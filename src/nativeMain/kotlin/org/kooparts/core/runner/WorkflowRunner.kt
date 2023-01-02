package org.kooparts.core.runner

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.curl.Curl
import org.kooparts.core.Workflow
import org.kooparts.core.WorkflowResult

/**
 * This function execute workflow
 *
 * @param workflow target workflow
 * @param httpClientEngine http client engine. Default engine is Curl.
 * @return workflow result
 */
fun run(
    workflow: Workflow,
    httpClientEngine: HttpClientEngine = Curl.create()
): WorkflowResult {
    val testJobResults = workflow.testJobs.map {
        run(testJob = it, httpClientEngine = httpClientEngine)
    }
    return WorkflowResult(
        name = workflow.name,
        testResults = testJobResults
    )
}
