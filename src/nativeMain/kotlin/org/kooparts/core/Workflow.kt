package org.kooparts.core

import io.ktor.http.HttpMethod

/**
 * The mapping class for Workflow file
 */
data class Workflow(
    /**
     * The workflow name
     */
    val name: String,

    /**
     * The workflow version
     */
    val version: String,

    /**
     * The test jobs included in the workflow
     */
    val testJobs: List<TestJob> = emptyList()
)

data class TestJob(
    /**
     * The test job name
     */
    val name: String,

    /**
     * The steps included in the job
     */
    val steps: List<Step> = emptyList()
)

data class Step(
    /**
     * The step name
     */
    val name: String,

    /**
     * The HTTP step configuration
     */
    val http: HTTPStep?
)

data class HTTPStep(
    /**
     * URL
     */
    val url: String,

    /**
     * The HTTP request method
     */
    val method: HttpMethod,

    /**
     * The check is an object that checks the success of a step.
     */
    val check: HTTPStepCheck
)

data class HTTPStepCheck(
    /**
     * The HTTP Status Code
     */
    val status: String
)
