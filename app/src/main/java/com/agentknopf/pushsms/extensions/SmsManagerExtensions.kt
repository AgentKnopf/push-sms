package com.agentknopf.pushsms.extensions

import android.telephony.SmsManager

/**
 * A little shortcut to send sms. Because usually we need to act differently depending on whether the message is
 * longer than 160 characters. However this method handles both cases.
 */
fun SmsManager.sendMessage(text: String, recipient: String) {
    if (text.length > 160) {
        //This will be a multipart text message
        sendMultipartTextMessage(recipient, null, divideMessage(text), null, null)
    } else {
        //This can be sent in one go
        sendTextMessage(recipient, null, text, null, null)
    }
}