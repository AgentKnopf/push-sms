package com.agentknopf.pushsms.model

import android.telephony.SmsManager
import com.agentknopf.pushsms.extensions.sendMessage

/**
 * An sms rule can have various criteria that determine when a given rules applies.
 *
 * Created by agentknopf on 09.12.17.
 */
internal abstract class SmsRule : IRule<Sms>

/**
 * This rule forwards an sms to a given number if the sms text contains the given text.
 */
internal data class ForwardOnMatchingText(private val expectedText: String, private val recipient: String) : SmsRule() {

    override fun doesRuleApply(input: Sms): Boolean = input.text.contains(expectedText)

    override fun executeRule(input: Sms) = SmsManager.getDefault().sendMessage(input.text, recipient)
}