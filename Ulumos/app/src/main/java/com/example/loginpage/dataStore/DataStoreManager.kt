package com.example.loginpage.dataStore

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

object DataStoreKeys{
    val MAIL_ID = stringPreferencesKey("mail_id")
    val PASSWORD = stringPreferencesKey("password")
    val HAS_LOGGED_IN = booleanPreferencesKey("has_logged_in")
}

class DataStoreManager(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val mailId
        get() = runBlocking {
            context.dataStore.data.map { preferences ->
                preferences[DataStoreKeys.MAIL_ID]?:""
            }.first()
        }

    val hasLoggedIn
        get() = runBlocking {
            context.dataStore.data.map { preferences ->
                preferences[DataStoreKeys.HAS_LOGGED_IN] ?: false
            }.first()
        }

    val password
        get() = runBlocking {
            context.dataStore.data.map { preferences ->
                preferences[DataStoreKeys.PASSWORD] ?: ""
            }.first()
        }

    suspend fun saveMailId(mailId: String) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.MAIL_ID] = mailId
        }
    }

    suspend fun savePassword(password: String) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.PASSWORD] = password
        }
    }

    suspend fun saveHasLoggedIn(hasLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.HAS_LOGGED_IN] = hasLoggedIn
        }
    }

    suspend fun deleteMailId() {
        context.dataStore.edit { preferences ->
            preferences.remove(DataStoreKeys.MAIL_ID)
        }
    }

    suspend fun deletePassword() {
        context.dataStore.edit { preferences ->
            preferences.remove(DataStoreKeys.PASSWORD)
        }
    }

    suspend fun deleteHasLoggedIn() {
        context.dataStore.edit { preferences ->
            preferences.remove(DataStoreKeys.HAS_LOGGED_IN)
        }
    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        @Volatile
        var instance : DataStoreManager? = null

        fun getInstance(context: Context) : DataStoreManager{
            return instance ?: synchronized(this){
                instance ?: DataStoreManager(context).also { instance = it }
            }
        }
    }
}
