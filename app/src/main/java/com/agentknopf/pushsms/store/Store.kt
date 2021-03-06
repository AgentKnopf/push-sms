package com.agentknopf.pushsms.store

import com.agentknopf.pushsms.model.SmsRule

/**
 * A redux-style "Store" - more on this here: https://redux.js.org/docs/api/createStore.html
 *
 * Created by agentknopf on 10.12.17.
 */
class Store {
    private var rules: List<SmsRule>

    constructor(store: Store?) {
        rules = store?.rules ?: listOf()
    }

    /**
     * A list of all the currently available rules.
     */
    fun getRules() = rules

    /**
     * Dispatches a new action to the store.
     */
    fun dispatch(action: Action) = when (action) {
        is AddSmsRuleAction -> rules = rules.plus(action.rule)
        is RemoveSmsRuleAction -> {
            //First remove the original entry
            rules = rules.minus(action.rule)
        }
    }
}