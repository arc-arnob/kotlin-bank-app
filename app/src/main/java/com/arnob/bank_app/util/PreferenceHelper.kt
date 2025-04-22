package com.arnob.bank_app.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setLoggedInUserId(userId: Long) {
        prefs.edit().putLong(KEY_USER_ID, userId).apply()
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, true).apply()
    }

    fun getLoggedInUserId(): Long {
        return prefs.getLong(KEY_USER_ID, -1)
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }

    companion object {
        private const val PREF_NAME = "AppPreferences"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
        private const val KEY_USER_ID = "userId"
    }
}