package com.agentknopf.pushsms

import android.app.Application
import android.content.Context
import android.support.annotation.StringRes

/**
 * Output stream pointing to a file of the given name. Use this to write data to the given file.
 */
fun getOutputStream(fileName: String) = App.Companion.getInstance().openFileOutput(fileName, Context.MODE_PRIVATE)

/**
 * Input stream pointing to a file of the given name. Use this to read data from the given file.
 */
fun getInputStream(fileName: String) = App.Companion.getInstance().openFileInput(fileName)

/**
 * Locale-based translation for given string resource id.
 */
fun getText(@StringRes stringId: Int) = getAppContext().getString(stringId)

/**
 * @return an instance of the Android application class.
 */
private fun getAppContext() = App.Companion.getInstance()

/**
 * Custom extension of the Android Application class.
 *
 * Created by agentknopf on 16.12.17.
 */
class App : Application() {

    companion object {
        private lateinit var self: App

        fun getInstance() = self
    }

    override fun onCreate() {
        super.onCreate()
        self = this
    }
}