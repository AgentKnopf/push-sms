package com.agentknopf.pushsms.store

import com.agentknopf.pushsms.model.SmsRule

/**
 * A redux-style "Store" - more on this here: https://redux.js.org/docs/api/createStore.html
 *
 * Created by agentknopf on 10.12.17.
 */
class Store(store: Store?) {

    private var rules: List<SmsRule> = listOf()

    init {
        rules = store?.rules ?: listOf()
    }

    /**
     * A list of all the currently available rules.
     */
    fun getRules() = rules
}