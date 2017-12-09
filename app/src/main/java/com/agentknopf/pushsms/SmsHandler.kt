package com.agentknopf.pushsms

import android.telephony.SmsMessage
import android.util.Log
import com.agentknopf.pushsms.extensions.print

private const val TAG = "SmsHandler"

/**
 * Processes SMS.
 *
 * Created by agentknopf on 09.12.17.
 */
internal data class SmsHandler(val sms: SmsMessage) {
    /**
     * Processes the passed SMS.
     */
    fun process() {
        Log.i(TAG, "Received sms ${sms.print()}")
    }
}

