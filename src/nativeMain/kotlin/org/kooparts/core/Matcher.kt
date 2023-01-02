package org.kooparts.core

/**
 * This function to check if the step passed.
 */
fun checkStepPassed(httpStepResult: HTTPStepResult): Boolean {
    return httpStepResult.status.passed
}

/**
 * This function to check if the test job passed.
 */
fun checkTestJobPassed(stepResults: List<StepResult>): Boolean {
    val failedStep = stepResults.filterNot {
        it.passed
    }

    return failedStep.isEmpty()
}
