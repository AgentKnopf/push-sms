package com.agentknopf.pushsms.add

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.agentknopf.pushsms.R
import com.agentknopf.pushsms.extensions.getFieldValue
import kotlinx.android.synthetic.main.activity_add_rule.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Let's the user add a new rule.
 */
class AddRuleActivity : AppCompatActivity() {
    private val TAG = "AddRuleActivity"
    private lateinit var viewModel: AddRuleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rule)
        setSupportActionBar(findViewById(R.id.toolbar))
        viewModel = ViewModelProviders.of(this).get(AddRuleViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_add_rule, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_save -> {
                doAsync {
                    val recipient = recipient.getFieldValue()
                    val expectedText = expectedText.getFieldValue()
                    Log.i(TAG, "Saving new rule. Any new SMS with text $expectedText will be forward to $recipient")
                    viewModel.saveRule(expectedText, recipient)
                    uiThread {
                        finish()
                    }
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
