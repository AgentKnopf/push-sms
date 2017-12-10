package com.agentknopf.pushsms.persistence

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

/**
 * The room database instance.
 *
 * Created by agentknopf on 10.12.17.
 */
@TypeConverters(ForwardOnMatchingTextTypeConverter::class)
internal abstract class Database : RoomDatabase() {
    //Add the DAOs here
}