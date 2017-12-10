package com.agentknopf.pushsms.persistence

import com.agentknopf.pushsms.model.ForwardOnMatchingText
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests converting SMS payload to/from JSON.
 *
 * Created by agentknopf on 10.12.17.
 */
internal class ForwardOnMatchingTextTypeConverterTest {

    @Test
    fun toJSON() {
        val payload = ForwardOnMatchingText.Payload("Hello", "1234567")
        val json = ForwardOnMatchingTextTypeConverter().payloadToString(payload)
        assertTrue(json.contains(payload.expectedText))
        assertTrue(json.contains(payload.recipient))
    }

    @Test
    fun fromJSON() {
        val payload = ForwardOnMatchingText.Payload("How goes it", "7654321")
        val json = ForwardOnMatchingTextTypeConverter().payloadToString(payload)
        val deserializedPayload = ForwardOnMatchingTextTypeConverter().payloadfromString(json)
        assertEquals(payload.expectedText, deserializedPayload.expectedText)
        assertEquals(payload.recipient, deserializedPayload.recipient)
    }
}