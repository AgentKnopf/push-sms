package com.agentknopf.pushsms.add

import android.arch.lifecycle.ViewModel
import android.support.annotation.WorkerThread
import com.agentknopf.pushsms.model.ForwardOnMatchingText
import com.agentknopf.pushsms.store.ActionType
import com.agentknopf.pushsms.store.Persister
import com.agentknopf.pushsms.store.createAction

/**
 * The view model for adding rules.
 *
 * Created by agentknopf on 03.01.18.
 */
class AddRuleViewModel : ViewModel() {

    @WorkerThread
    fun saveRule(text: String, recipient: String) {
        val action = createAction(ActionType.CREATE,
                                  ForwardOnMatchingText(ForwardOnMatchingText.Payload(text, recipient)))
        Persister.loadStore().dispatch(action)
        Persister.persistStore()
    }
}