package com.cj.bunnywallet.datasource

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("TooManyFunctions")
@Singleton
class BunnyDataStore @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private fun <T> getValue(
        key: Preferences.Key<T>,
        defaultValue: T,
        observe: Boolean,
    ): Flow<T> = dataStore.data
        .map { it[key] ?: defaultValue }
        .take(if (observe) Int.MAX_VALUE else 1)
        .catch { Log.d("BunnyDataStore", "get value failed: $it") }

    suspend fun putString(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        dataStore.edit { it[preferencesKey] = value }
    }

    fun getString(key: String, defaultValue: String = "", observe: Boolean = false) =
        getValue(stringPreferencesKey(key), defaultValue, observe)

    suspend fun putInt(key: String, value: Int) {
        val preferencesKey = intPreferencesKey(key)
        dataStore.edit { it[preferencesKey] = value }
    }

    fun getInt(key: String, defaultValue: Int = 0, observe: Boolean = false): Flow<Int> =
        getValue(intPreferencesKey(key), defaultValue, observe)

    suspend fun putLong(key: String, value: Long) {
        val preferencesKey = longPreferencesKey(key)
        dataStore.edit { it[preferencesKey] = value }
    }

    fun getLong(key: String, defaultValue: Long = 0L, observe: Boolean = false): Flow<Long> =
        getValue(longPreferencesKey(key), defaultValue, observe)

    suspend fun putFloat(key: String, value: Float) {
        val preferencesKey = floatPreferencesKey(key)
        dataStore.edit { it[preferencesKey] = value }
    }

    fun getFloat(key: String, defaultValue: Float = 0f, observe: Boolean = false): Flow<Float> =
        getValue(floatPreferencesKey(key), defaultValue, observe)

    suspend fun putDouble(key: String, value: Double) {
        val preferencesKey = doublePreferencesKey(key)
        dataStore.edit { it[preferencesKey] = value }
    }

    fun getDouble(key: String, defaultValue: Double = 0.0, observe: Boolean = false): Flow<Double> =
        getValue(doublePreferencesKey(key), defaultValue, observe)

    suspend fun putBoolean(key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key)
        dataStore.edit { it[preferencesKey] = value }
    }

    fun getBoolean(key: String, defaultValue: Boolean, observe: Boolean = false): Flow<Boolean> =
        getValue(booleanPreferencesKey(key), defaultValue, observe)

    suspend fun <T> clearPreference(key: Preferences.Key<T>) {
        dataStore.edit { it.remove(key) }
    }

    suspend fun clearDataStore() {
        dataStore.edit { it.clear() }
    }
}