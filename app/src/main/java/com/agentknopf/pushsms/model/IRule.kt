package com.agentknopf.pushsms.model

/**
 * Interface representing a rule.
 *
 * Created by agentknopf on 10.12.17.
 */
internal interface IRule<in T> {
    /**
     * @return true if the rule applies, false otherwise.
     */
    fun doesRuleApply(input: T): Boolean

    /**
     * Executes the rule.
     */
    fun executeRule(input: T)
}