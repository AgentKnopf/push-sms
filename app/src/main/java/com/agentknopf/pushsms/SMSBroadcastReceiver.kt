package com.agentknopf.pushsms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import com.agentknopf.pushsms.extensions.EMPTY_STRING
import java.util.*

private const val TAG = "SMSBroadcastReceiver"
private const val SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED"

/**
 * Listens to incoming SMS messages.
 *
 * Created by agentknopf on 09.12.17.
 */
internal class SMSBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "Intercepted SMS")
        val action = intent?.action ?: EMPTY_STRING
        when (action) {
            SMS_RECEIVED_ACTION -> intent?.extras?.let { SmsHandler(createSms(intent.extras)).process() }
        }
    }

    /**
     * Creates an SMS from a bundle.
     */
    private fun createSms(bundle: Bundle): SmsMessage {
        val data: Array<Any> = bundle.get("pdus") as Array<Any>
        //Take the last sms we got
        data[data.lastIndex].let {
            return SmsMessage.createFromPdu(data[data.lastIndex] as ByteArray, bundle.getString("format"))
        }
    }
}