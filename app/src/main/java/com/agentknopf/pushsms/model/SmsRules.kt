package com.agentknopf.pushsms.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.telephony.SmsManager
import com.agentknopf.pushsms.extensions.sendMessage

/**
 * An sms rule can have various criteria that determine when a given rules applies.
 *
 * Created by agentknopf on 09.12.17.
 */
internal abstract class SmsRule : IRule<Sms>

/**
 * Stored in the database as a rule's payload which contains all the relevant data for a given rule.
 */
internal abstract class RulePayload

/**
 * This rule forwards an sms to a given number if the sms text contains the given text.
 */
@Entity
internal class ForwardOnMatchingText(private val payload: Payload) : SmsRule() {
    @PrimaryKey
    private var id = 0

    override fun doesRuleApply(input: Sms): Boolean = input.text.contains(payload.expectedText)

    override fun executeRule(input: Sms) = SmsManager.getDefault().sendMessage(input.text, payload.recipient)

    data class Payload(val expectedText: String, val recipient: String) : RulePayload()
}
