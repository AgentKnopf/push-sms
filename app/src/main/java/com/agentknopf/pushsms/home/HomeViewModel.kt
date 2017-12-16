package com.agentknopf.pushsms.home

import android.arch.lifecycle.ViewModel
import android.support.annotation.WorkerThread
import com.agentknopf.pushsms.store.Persister

/**
 * The view model for the home screen.
 *
 * Created by agentknopf on 10.12.17.
 */
class HomeViewModel : ViewModel() {
    //TODO use live data to monitor the store, otherwise we won't know that data changed

    @WorkerThread
    fun getRules() = Persister.loadStore().getRules()
}