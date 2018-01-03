package com.agentknopf.pushsms.add

import android.arch.lifecycle.ViewModel
import com.agentknopf.pushsms.model.ForwardOnMatchingText
import com.agentknopf.pushsms.store.ActionType
import com.agentknopf.pushsms.store.Persister
import com.agentknopf.pushsms.store.createAction
import org.jetbrains.anko.doAsync

/**
 * The view model for adding rules.
 *
 * Created by agentknopf on 03.01.18.
 */
class AddRuleViewModel : ViewModel() {

    fun saveRule(text: String, recipient: String) {
        doAsync {
            val action = createAction(ActionType.CREATE,
                                      ForwardOnMatchingText(ForwardOnMatchingText.Payload(text, recipient)))
            Persister.loadStore().dispatch(action)
            Persister.persistStore()
        }
    }
}