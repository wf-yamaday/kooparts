package org.kooparts.core

import kotlinx.serialization.Serializable

/**
 * The mapping class for Workflow file
 */
@Serializable
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

@Serializable
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

@Serializable
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

@Serializable
data class HTTPStep(
    /**
     * URL
     */
    val url: String,

    /**
     * The HTTP request method
     */
    val method: String,

    /**
     * The check is an object that checks the success of a step.
     */
    val check: HTTPStepCheck
)

@Serializable
data class HTTPStepCheck(
    /**
     * The HTTP Status Code
     */
    val status: String
)
