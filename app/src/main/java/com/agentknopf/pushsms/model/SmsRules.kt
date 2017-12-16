package com.agentknopf.pushsms.model

import android.telephony.SmsManager
import com.agentknopf.pushsms.R
import com.agentknopf.pushsms.extensions.sendMessage
import com.agentknopf.pushsms.getText
import java.util.*

/**
 * An sms rule can have various criteria that determine when a given rules applies.
 *
 * Created by agentknopf on 09.12.17.
 */
abstract class SmsRule : IRule<Sms> {
    val id = UUID.randomUUID().toString()
}

/**
 * Stored in the database as a rule's payload which contains all the relevant data for a given rule.
 */
abstract class RulePayload

/**
 * This rule forwards an sms to a given number if the sms text contains the given text.
 */
class ForwardOnMatchingText(private val payload: Payload) : SmsRule() {
    override fun getTitle(): String = getText(R.string.ForwardOnMatchingText_Title)

    override fun getDescription(): String = String.format(getText(R.string.ForwardOnMatchingText_Details), payload
            .recipient, payload.expectedText)

    override fun doesRuleApply(input: Sms): Boolean = input.text.contains(payload.expectedText)

    override fun executeRule(input: Sms) = SmsManager.getDefault().sendMessage(input.text, payload.recipient)

    data class Payload(val expectedText: String, val recipient: String) : RulePayload()
}
