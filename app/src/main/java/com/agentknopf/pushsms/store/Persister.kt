package com.agentknopf.pushsms.store

import android.support.annotation.WorkerThread
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import java.io.File

/**
 * Responsible for saving the current state to disk.
 *
 * Created by agentknopf on 10.12.17.
 */
internal class Persister(private val storage: File) {
    //Keeps a reference to the store
    private lateinit var store: Store

    @WorkerThread
    fun persistStore() {
        if (store != null) {
            storage.printWriter().use { out ->
                out.print(serializeStore(store))
            }
        }
    }

    @WorkerThread
    fun loadStore(): Store {
        //TODO Not thread safe
        if (store == null) {
            val input = storage.inputStream()
            val result = input.bufferedReader().use { it.readText() }
            store = if (result.isBlank()) Store(null) else deserializeStore(result)
        }
        return store
    }

    private fun deserializeStore(store: String): Store = createAdapter().fromJson(store)!!

    private fun serializeStore(store: Store): String = createAdapter().toJson(store)

    private fun createAdapter(): JsonAdapter<Store> {
        val moshiBuilder = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        return moshiBuilder.adapter(Store::class.java)
    }
}