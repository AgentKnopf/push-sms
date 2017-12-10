package com.agentknopf.pushsms.persistence

import android.arch.persistence.room.TypeConverter
import com.agentknopf.pushsms.model.ForwardOnMatchingText
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi

/**
 * Offers type converters for ForwardOnMatchingText when used with Room.
 *
 * Created by agentknopf on 10.12.17.
 */
internal class ForwardOnMatchingTextTypeConverter {
    /**
     * Converts a JSON string to a payload instance.
     */
    @TypeConverter
    internal fun payloadfromString(payload: String): ForwardOnMatchingText.Payload = createAdapter().fromJson(payload)!!

    /**
     * Converts a Payload instance to a JSON string.
     */
    @TypeConverter
    internal fun payloadToString(payload: ForwardOnMatchingText.Payload): String = createAdapter().toJson(payload)

    private fun createAdapter(): JsonAdapter<ForwardOnMatchingText.Payload> {
        val moshiBuilder = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        return moshiBuilder.adapter(ForwardOnMatchingText.Payload::class.java)
    }
}