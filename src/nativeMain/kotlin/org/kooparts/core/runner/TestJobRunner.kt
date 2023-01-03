package org.kooparts.core.runner

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.curl.Curl
import io.ktor.utils.io.core.use
import kotlinx.coroutines.runBlocking
import org.kooparts.core.TestJob
import org.kooparts.core.TestJobResult
import org.kooparts.core.checkTestJobPassed

/**
 * This function execute test job.
 *
 * @param testJob target test job
 * @param httpClientEngine http client engine. Default engine is Curl.
 * @return test job result
 */
fun run(
    testJob: TestJob,
    httpClientEngine: HttpClientEngine = Curl.create()
): TestJobResult {
    val stepResults = runBlocking {
        val client = HttpClient(httpClientEngine)
        client.use {
            return@runBlocking testJob.steps.map { step ->
                run(step = step, client = client)
            }
        }
    }

    return TestJobResult(
        name = testJob.name,
        passed = checkTestJobPassed(stepResults),
        stepResults = stepResults
    )
}
