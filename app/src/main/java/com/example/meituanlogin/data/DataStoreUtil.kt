package com.example.meituanlogin

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

//1. 给Context扩展一个属性dataStore
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_info")
object DataStoreUtil {
    //2. 声明存取的key
    private val USER_ID = stringPreferencesKey("user_id")
    private val PHONE_NUMBER = stringPreferencesKey("phone_number")
    private val PASSWORD = stringPreferencesKey("user_password")
    private val IS_LOGIN = booleanPreferencesKey("is_login")

    //3.保存数据
    fun saveUser(context: Context, user: User){
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit {
                it[USER_ID] = user.userID!!
                it[PHONE_NUMBER] = user.phoneNumber!!
                it[PASSWORD] = user.password!!
                it[IS_LOGIN] = user.isLogin!!
            }
        }
    }

    //4.读取数据
    fun loadUser(context: Context):User = runBlocking {
        val userDeffer = async {
            context.dataStore.data.map {
                val user = User(
                    it[USER_ID],
                    it[PHONE_NUMBER],
                    it[PASSWORD],
                    it[IS_LOGIN]
                )
                user
            }
        }
        userDeffer.await().first()
    }

    //更新状态
    fun updateLoginState(context: Context, isLogin: Boolean){
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit {
                it[IS_LOGIN] = isLogin
            }
        }
    }
}

//封装用户信息
data class User(
    val userID:String? = null,
    val phoneNumber:String? = null,
    val password:String? = null,
    val isLogin:Boolean? = null
)







