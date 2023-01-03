package org.kooparts

import org.kooparts.core.CheckResult
import org.kooparts.core.HTTPStepResult
import org.kooparts.core.StepResult
import org.kooparts.core.checkStepPassed
import org.kooparts.core.checkTestJobPassed
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MatcherTest {

    @Test
    fun testCheckStepPassedReturnTrueWhenPassedHttpStepResult() {
        val passedHttpStepResult = HTTPStepResult(
            status = CheckResult(
                passed = true
            )
        )

        val actual = checkStepPassed(httpStepResult = passedHttpStepResult)

        assertTrue(actual)
    }

    @Test
    fun testCheckStepPassedReturnFalseWhenNotPassedHttpStepResult() {
        val passedHttpStepResult = HTTPStepResult(
            status = CheckResult(
                passed = false
            )
        )

        val actual = checkStepPassed(httpStepResult = passedHttpStepResult)

        assertFalse(actual)
    }

    @Test
    fun testCheckTestJobPassedReturnTrueWhenAllStepIsPassed() {
        val passedStepResult = StepResult(
            name = "Step Result",
            passed = true
        )
        val allPassedSteps = listOf(passedStepResult, passedStepResult.copy())

        val actual = checkTestJobPassed(allPassedSteps)

        assertTrue(actual)
    }

    @Test
    fun testCheckTestJobPassedReturnFalseWhenIncludeNotPassedStep() {
        val passedStepResult = StepResult(
            name = "Step Result",
            passed = true
        )
        val notPassedStepResult = StepResult(
            name = "Step Result",
            passed = false
        )

        val actual = checkTestJobPassed(listOf(passedStepResult, notPassedStepResult))

        assertFalse(actual)
    }
}
