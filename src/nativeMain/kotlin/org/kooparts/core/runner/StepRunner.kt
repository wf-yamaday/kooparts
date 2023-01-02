package org.kooparts.core.runner

import io.ktor.client.HttpClient
import org.kooparts.core.Step
import org.kooparts.core.StepResult
import org.kooparts.core.checkStepPassed

/**
 * This function execute step.
 *
 * @param step target step
 * @param client Http Client managed by the caller
 * @return step result
 */
suspend fun run(step: Step, client: HttpClient): StepResult {
        step.http?.let {
            val result = run(httpStep = step.http, client = client)
            return StepResult(
                name = step.name,
                passed = checkStepPassed(httpStepResult = result)
            )
        }
    return StepResult(
        name = step.name,
        passed = true
    )
}
