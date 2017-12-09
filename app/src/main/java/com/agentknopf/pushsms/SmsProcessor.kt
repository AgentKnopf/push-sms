package com.agentknopf.pushsms

import android.util.Log
import com.agentknopf.pushsms.model.Sms
import com.agentknopf.pushsms.model.SmsRule

private const val TAG = "SmsHandler"

/**
 * Processes SMS.
 *
 * Created by agentknopf on 09.12.17.
 */
internal data class SmsProcessor(val sms: Sms) {
    /**
     * Processes the passed SMS.
     */
    fun process() {
        Log.i(TAG, "Received sms $sms")
        applyRules(findMatchingRules(), sms)
    }

    private fun findMatchingRules(): List<SmsRule> = TODO("not implemented - will need database access to rules")

    private fun applyRules(rules: List<SmsRule>, sms: Sms) {
        for (rule in rules) rule.executeRule(sms)
    }
}

