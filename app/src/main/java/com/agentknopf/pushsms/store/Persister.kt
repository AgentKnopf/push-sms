package com.agentknopf.pushsms.store

import android.support.annotation.WorkerThread
import com.agentknopf.pushsms.getInputStream
import com.agentknopf.pushsms.getOutputStream
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import java.nio.charset.Charset

/** Name of the file to which the persister writes */
private const val FILE_NAME = "push-sms-store"

/**
 * Responsible for saving the current state to disk.
 *
 * Created by agentknopf on 10.12.17.
 */
object Persister {
    //Keeps a reference to the store
    private lateinit var store: Store

    @WorkerThread
    fun persistStore() {
        store?.let {
            val output = getOutputStream(FILE_NAME)
            val value = serializeStore(store)
            output.write(value.toByteArray(Charset.defaultCharset()))
            output.close()
        }
    }

    @WorkerThread
    fun loadStore(): Store {
        if (store == null) {
            synchronized(this) {
                val input = getInputStream(FILE_NAME)
                val result = input.bufferedReader().use { it.readText() }
                store = if (result.isBlank()) Store(null) else deserializeStore(result)
            }
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