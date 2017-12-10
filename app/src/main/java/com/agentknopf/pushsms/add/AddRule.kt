package com.agentknopf.pushsms.add

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.agentknopf.pushsms.R

/**
 * Let's the user add a new rule.
 */
class AddRule : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rule)
    }
}
