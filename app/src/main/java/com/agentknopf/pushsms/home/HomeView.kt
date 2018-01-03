package com.agentknopf.pushsms.home

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.agentknopf.pushsms.R
import com.agentknopf.pushsms.add.AddRuleActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Shows an overview over all created rules and let's the user create new rules.
 */
class HomeView : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewRuleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        recyclerView = findViewById(R.id.rules_listview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { _ ->
            startActivity(Intent(this, AddRuleActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        initializeData()
    }

    /**
     * Fetches the rules to display and binds them to the adapter and recyclerview.
     */
    private fun initializeData() {
        doAsync {
            //Fetch the data on a background thread
            val rules = viewModel.getRules()
            uiThread {
                //On the ui thread, bind the rules we retrieved
                adapter = RecyclerViewRuleAdapter(rules)
                recyclerView.adapter = adapter
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
