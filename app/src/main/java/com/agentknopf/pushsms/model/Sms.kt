package com.agentknopf.pushsms.model

import android.telephony.SmsMessage

/**
 * Represents an Sms and wraps the android SmsMessage API.
 *
 * Created by agentknopf on 10.12.17.
 */
internal data class Sms(val text: String, val recipient: String) {
    constructor(smsMessage: SmsMessage) : this(smsMessage.messageBody, smsMessage.originatingAddress)
}