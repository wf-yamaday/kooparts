package org.kooparts.helper

import io.ktor.http.HttpMethod
import org.kooparts.core.HTTPStep
import org.kooparts.core.HTTPStepCheck
import org.kooparts.core.Step
import org.kooparts.core.TestJob
import org.kooparts.core.Workflow

fun httpStepCheckFactory(
    status: String = "200"
): HTTPStepCheck = HTTPStepCheck(
    status = status
)

fun httpStepFactory(
    url: String = "https://example.com",
    method: String = "GET",
    check: HTTPStepCheck = httpStepCheckFactory()
): HTTPStep = HTTPStep(
    url = url,
    method = method,
    check = check
)

fun stepFactory(
    name: String = "Test Step",
    http: HTTPStep? = httpStepFactory()
): Step = Step(
    name = name,
    http = http
)

fun testJobFactory(
    name: String = "Test Job",
    step: Step = stepFactory(),
    vararg steps: Step
): TestJob = TestJob(
    name = name,
    steps = if (steps.isEmpty()) listOf(step) else listOf(*steps)
)

fun workflowFactory(
    name: String = "Test Workflow",
    version: String = "1.0",
    testJob: TestJob = testJobFactory(),
    vararg testJobs: TestJob
): Workflow = Workflow(
    name = name,
    version = version,
    testJobs = if (testJobs.isEmpty()) listOf(testJob) else listOf(*testJobs)
)
