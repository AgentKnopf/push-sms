package com.agentknopf.pushsms.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.agentknopf.pushsms.R
import com.agentknopf.pushsms.model.SmsRule

/**
 * Creates a visual representation of a list of rules.
 *
 * Created by agentknopf on 16.12.17.
 */
internal class RecyclerViewRuleAdapter(private val data: List<SmsRule>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.rules_listview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.let {
            //Retrieve the rule
            val rule = data[position]
            holder.getTitle().text = rule.getTitle()
            holder.getDescription().text = rule.getDescription()
        }
    }

    override fun getItemCount(): Int = data.count()
}

internal class ViewHolder : RecyclerView.ViewHolder {
    private val title: TextView
    private val description: TextView

    constructor(itemView: View) : super(itemView) {
        title = itemView.findViewById(R.id.rule_title)
        description = itemView.findViewById(R.id.rule_description)
    }

    fun getTitle() = title
    fun getDescription() = description
}
