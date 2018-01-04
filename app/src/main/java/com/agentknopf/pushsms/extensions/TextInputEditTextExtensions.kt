package com.agentknopf.pushsms.extensions

import android.support.design.widget.TextInputEditText

/**
 * Extension methods for TextInputEditText
 *
 * Created by agentknopf on 04.01.18.
 */

/**
 * @return the current TextInputEditText field value as a String.
 */
fun TextInputEditText.getFieldValue(): String = text.toString()