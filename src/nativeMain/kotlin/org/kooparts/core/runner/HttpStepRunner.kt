package org.kooparts.core.runner

import io.ktor.client.HttpClient
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import org.kooparts.core.CheckResult
import org.kooparts.core.HTTPStep
import org.kooparts.core.HTTPStepResult

/**
 * This function execute http step.
 *
 * @param httpStep target http step
 * @param client Http Client managed by the caller
 * @return http step result
 */
suspend fun run(httpStep: HTTPStep, client: HttpClient): HTTPStepResult {
    val response: HttpResponse = client.request(httpStep.url) {
        method = when(httpStep.method) {
            in "get", "GET", "Get" -> HttpMethod.Get
            in "post", "POST" -> HttpMethod.Post
            else -> HttpMethod.Get
        }
    }

    return HTTPStepResult(
        status = CheckResult(
            passed = httpStep.check.status.toRegex().matches(response.status.value.toString())
        )
    )
}
