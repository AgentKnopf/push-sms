package com.agentknopf.pushsms.extensions

import android.telephony.SmsMessage

/**
 * Extensions for the SmsMessage class.
 *
 * Created by agentknopf on 09.12.17.
 */
fun SmsMessage.print(): String = "message: [$messageBody] sender: [$originatingAddress]"