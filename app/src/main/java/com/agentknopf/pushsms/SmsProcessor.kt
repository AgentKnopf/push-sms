package com.agentknopf.pushsms

import android.support.annotation.WorkerThread
import android.util.Log
import com.agentknopf.pushsms.model.Sms
import com.agentknopf.pushsms.model.SmsRule
import com.agentknopf.pushsms.store.Persister

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

    @WorkerThread
    private fun findMatchingRules(): List<SmsRule> {
        val store = Persister.loadStore()
        var result = mutableListOf<SmsRule>()
        result.addAll(store.getRules())
        result.filter { !it.doesRuleApply(sms) }
        Log.i(TAG, "Found ${result.size} matching rules")
        return result
    }

    private fun applyRules(rules: List<SmsRule>, sms: Sms) {
        Log.i(TAG, "Executing ${rules.size} on $sms")
        for (rule in rules) rule.executeRule(sms)
    }
}

