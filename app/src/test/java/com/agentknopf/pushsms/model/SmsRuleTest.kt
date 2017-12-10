package com.agentknopf.pushsms.model

import com.agentknopf.pushsms.model.ForwardOnMatchingText
import com.agentknopf.pushsms.model.Sms
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

/**
 * Tests SMS rules.
 * Created by agentknopf on 10.12.17.
 */
class SmsRuleTest {

    @Test
    fun forwardOnMatchingText() {
        val message = Sms("Hello this is some text", UUID.randomUUID().toString())
        var rule = com.agentknopf.pushsms.model.ForwardOnMatchingText(
                createPayload("is some", UUID.randomUUID().toString()))
        assertTrue(rule.doesRuleApply(message))

        rule = com.agentknopf.pushsms.model.ForwardOnMatchingText(
                createPayload("this is some", UUID.randomUUID().toString()))
        assertTrue(rule.doesRuleApply(message))

        rule = com.agentknopf.pushsms.model.ForwardOnMatchingText(createPayload("Hello", UUID.randomUUID().toString()))
        assertTrue(rule.doesRuleApply(message))

        rule = com.agentknopf.pushsms.model.ForwardOnMatchingText(
                createPayload("Chicken!", UUID.randomUUID().toString()))
        assertFalse(rule.doesRuleApply(message))
    }

    private fun createPayload(text: String, recipient: String) = ForwardOnMatchingText.Payload(text, recipient)
}