package org.kooparts.core

import kotlin.test.Test
import kotlin.test.assertEquals

class TestJobResultTest {

    @Test
    fun testCountFailedStep() {
        val testJobResult = TestJobResult(
            name = "Test Job Result",
            passed = false,
            stepResults = listOf(
                StepResult(
                    name = "Step Result 1",
                    passed = true
                ),
                StepResult(
                    name = "Step Result 2",
                    passed = false
                ),
            )
        )

        val actual = testJobResult.countFailedStep()

        assertEquals(1, actual)
    }
}