package kr.pe.paran.library_app.repository.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kr.pe.paran.library_app.model.LibrarianData
import kr.pe.paran.library_app.model.MemberData
import kr.pe.paran.library_app.repository.CacheDataStore


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "local_data_store")

class LocalCacheDataStore(context: Context) : CacheDataStore {

    private val dataStore = context.dataStore

    private object PreferenceKey {
        val memberDataKey = stringPreferencesKey("MemberData")
        val librarianDataKey = stringPreferencesKey("LibrarianData")
    }


    override suspend fun saveMemberData(memberData: MemberData) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.memberDataKey] = Json.encodeToString(memberData)
        }
    }

    override suspend fun loadMemberData(): Flow<MemberData> {
        return dataStore.data.map {
            val jsonData = it[PreferenceKey.memberDataKey]
            if (jsonData == null) {
                MemberData()
            } else {
                Json.decodeFromString(jsonData)
            }
        }
    }

    override suspend fun loadLibrarianData(): Flow<LibrarianData> {
        return dataStore.data.map {
            val jsonData = it[PreferenceKey.memberDataKey]
            if (jsonData == null) {
                LibrarianData()
            } else {
                Json.decodeFromString(jsonData)
            }
        }
    }

    override suspend fun saveLibrarianData(librarianData: LibrarianData) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.librarianDataKey] = Json.encodeToString(librarianData)
        }
    }

}