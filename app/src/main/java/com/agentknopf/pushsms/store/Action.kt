package com.agentknopf.pushsms.store

import com.agentknopf.pushsms.model.SmsRule

/**
 * Redux actions.
 *
 * Created by agentknopf on 10.12.17.
 */
sealed class Action

enum class ActionType {
    CREATE, DELETE
}

/**
 * Redux action for adding a new sms rule
 */
data class AddSmsRuleAction(val rule: SmsRule) : Action()

/**
 * Redux action for removing an existing sms rule
 */
data class RemoveSmsRuleAction(val rule: SmsRule) : Action()

/**
 * Creates redux actions.
 */
fun createAction(actionType: ActionType, rule: SmsRule) = when (actionType) {
    ActionType.CREATE -> AddSmsRuleAction(rule)
    ActionType.DELETE -> RemoveSmsRuleAction(rule)
}

