package com.rangga.seanar.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rangga.seanar.R
import com.rangga.seanar.data.parcel.DetailUserParcel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class TokenDatastore(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_ROLE = "user_role"
        const val USER_ID = "user_id"
        const val USER_USERNAME = "user_username"
        const val USER_EMAIL = "user_email"
        const val USER_PHONE = "user_phone"
    }

    fun saveToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun saveRole(role: String) {
        val editor = prefs.edit()
        editor.putString(USER_ROLE, role)
        editor.apply()
    }

    fun saveUserId(id: String) {
        val editor = prefs.edit()
        editor.putString(USER_ID, id)
        editor.apply()
    }

    fun saveDetailUser(data: DetailUserParcel) {
        val editor = prefs.edit()
        editor.putString(USER_USERNAME, data.username)
        editor.putString(USER_PHONE, data.phoneNumber)
        editor.putString(USER_EMAIL, data.email)
        editor.apply()
    }


    fun clear() {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, "")
        editor.putString(USER_ROLE, "")
        editor.putString(USER_ID, "")
        editor.putString(USER_USERNAME, "")
        editor.putString(USER_PHONE, "")
        editor.putString(USER_EMAIL, "")
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun getRole(): String? {
        return prefs.getString(USER_ROLE, null)
    }

    fun getUserId(): String? {
        return prefs.getString(USER_ID, null)
    }

    fun getDetail(): DetailUserParcel? {
        return DetailUserParcel(
            email = prefs.getString(USER_EMAIL, "").toString(),
            phoneNumber = prefs.getString(
                USER_PHONE, ""
            ).toString(),
            username = prefs.getString(USER_USERNAME, "").toString()
        )
    }


}