package org.kooparts.core

data class CheckResult(
    val passed: Boolean
)

data class HTTPStepResult(
    val status: CheckResult
)

data class StepResult(
    val name: String,
    val passed: Boolean
)

data class TestJobResult(
    val name: String,
    val passed: Boolean,
    val stepResults: List<StepResult>
)

data class WorkflowResult(
    val name: String,
    val testResults: List<TestJobResult>
)
