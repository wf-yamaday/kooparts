package org.kooparts.parser

import org.kooparts.core.parser.parser
import org.kooparts.helper.workflowFactory
import kotlin.test.Test
import kotlin.test.assertEquals

class WorkflowParserTest {

    @Test
    fun testParserWithSimpleExample() {
        val workflowVersion = "1.0"
        val workflowName = "Sample"
        val yamlText = """
            version: $workflowVersion
            name: $workflowName
        """.trimIndent()

        val workflow = parser(yamlText = yamlText)

        assertEquals(workflowVersion, workflow.version)
        assertEquals(workflowName, workflow.name)
        assertEquals(emptyList(), workflow.testJobs)
    }

    @Test
    fun testParserWithSimpleNestedExample() {
        val expectedWorkflow = workflowFactory()
        val yamlText = """
            version: ${expectedWorkflow.version}
            name: ${expectedWorkflow.name}
            
            tests:
              - name: ${expectedWorkflow.testJobs[0].name}
                steps:
                - name: ${expectedWorkflow.testJobs[0].steps[0].name}
                  http:
                    url: "${expectedWorkflow.testJobs[0].steps[0].http?.url}"
                    method: ${expectedWorkflow.testJobs[0].steps[0].http?.method}
                    check:
                      status: ${expectedWorkflow.testJobs[0].steps[0].http?.check?.status}
        """.trimIndent()

        val actualWorkflow = parser(yamlText = yamlText)

        assertEquals(expectedWorkflow, actualWorkflow)
   }
}
