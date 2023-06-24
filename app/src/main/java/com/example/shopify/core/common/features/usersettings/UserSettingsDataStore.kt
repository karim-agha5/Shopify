package com.example.shopify.core.common.features.usersettings

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.shopify.core.util.Constants
import kotlinx.coroutines.flow.first

class UserSettingsDataStore private constructor(application: Application) {

    private val Context.dataStore: DataStore<Preferences>
            by preferencesDataStore(name = Constants.USER_SETTINGS_DATA_STORE_NAME)

    private val applicationContext = application.applicationContext

    private val IS_FIRST_TIME_USER_KEY  = "isFirstTimeUser"
    private val DID_LOG_BEFORE_KEY = "didLogBefore"
    private val USER_EMAIL_KEY = "userEmail"
    private val USER_ID_KEY = "userId"
    private val USER_SHOPPING_CART_ID_KEY = "userShoppingCartId"
    private val USER_WISHLIST_ID_KEY = "userWishListId"
    private val USER_COUPON_KEY = "userCoupon"
    private val USER_BUILDING_NUMBER_KEY = "userBuildingNumber"
    private val USER_STREET_NAME_KEY = "userStreetName"
    private val USER_CITY_KEY = "userCity"
    private val USER_COUNTRY_KEY = "userCountry"
    private val USER_PHONE_NUMBER = "userPhoneNumber"
    private val USER_CURRENCY = "userCurrency"

      companion object{

          @Volatile
          private var instance: UserSettingsDataStore? = null

          fun getInstance(application: Application) : UserSettingsDataStore{
              return instance ?: synchronized(this){
                  val temp = UserSettingsDataStore(application)
                  instance = temp
                  temp
              }
          }
      }

    suspend fun writeIsFirstTimeUser(value: Boolean) {
        val dataStoreKey = booleanPreferencesKey(IS_FIRST_TIME_USER_KEY)
        applicationContext.applicationContext.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun readIsFirstTimeUser() : Boolean? {
        val dataStoreKey = booleanPreferencesKey(IS_FIRST_TIME_USER_KEY)
        val preferences = applicationContext.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun writeDidLogBefore(value: Boolean) {
        val dataStoreKey = booleanPreferencesKey(DID_LOG_BEFORE_KEY)
        applicationContext.applicationContext.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun readDidLogBefore() : Boolean? {
        val dataStoreKey = booleanPreferencesKey(DID_LOG_BEFORE_KEY)
        val preferences = applicationContext.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun writeUserEmail(value: String) {
        val dataStoreKey = stringPreferencesKey(USER_EMAIL_KEY)
        applicationContext.applicationContext.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun readUserEmail() : String? {
        val dataStoreKey = stringPreferencesKey(USER_EMAIL_KEY)
        val preferences = applicationContext.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun writeUserId(value: Long) {
        val dataStoreKey = longPreferencesKey(USER_ID_KEY)
        applicationContext.applicationContext.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun readUserId() : Long? {
        val dataStoreKey = longPreferencesKey(USER_ID_KEY)
        val preferences = applicationContext.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun writeUserShoppingCartId(value: Long) {
        val dataStoreKey = longPreferencesKey(USER_SHOPPING_CART_ID_KEY)
        applicationContext.applicationContext.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun readUserShoppingCartId() : Long? {
        val dataStoreKey = longPreferencesKey(USER_SHOPPING_CART_ID_KEY)
        val preferences = applicationContext.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun writeUserWishListId(value: Long) {
        val dataStoreKey = longPreferencesKey(USER_WISHLIST_ID_KEY)
        applicationContext.applicationContext.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun readUserWishListId() : Long? {
        val dataStoreKey = longPreferencesKey(USER_WISHLIST_ID_KEY)
        val preferences = applicationContext.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun writeUserCoupon(value: String) {
        val dataStoreKey = stringPreferencesKey(USER_COUPON_KEY)
        applicationContext.applicationContext.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun readUserCoupon() : String? {
        val dataStoreKey = stringPreferencesKey(USER_COUPON_KEY)
        val preferences = applicationContext.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun writeUserBuildingNumber(value: String) {
        val dataStoreKey = stringPreferencesKey(USER_BUILDING_NUMBER_KEY)
        applicationContext.applicationContext.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun readUserBuildingNumber() : String? {
        val dataStoreKey = stringPreferencesKey(USER_BUILDING_NUMBER_KEY)
        val preferences = applicationContext.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun writeUserStreetName(value: String) {
        val dataStoreKey = stringPreferencesKey(USER_STREET_NAME_KEY)
        applicationContext.applicationContext.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun readUserStreetName() : String? {
        val dataStoreKey = stringPreferencesKey(USER_STREET_NAME_KEY)
        val preferences = applicationContext.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun writeUserCity(value: String) {
        val dataStoreKey = stringPreferencesKey(USER_CITY_KEY)
        applicationContext.applicationContext.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun readUserCity() : String? {
        val dataStoreKey = stringPreferencesKey(USER_CITY_KEY)
        val preferences = applicationContext.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun writeUserCountry(value: String) {
        val dataStoreKey = stringPreferencesKey(USER_COUNTRY_KEY)
        applicationContext.applicationContext.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun readUserCountry() : String? {
        val dataStoreKey = stringPreferencesKey(USER_COUNTRY_KEY)
        val preferences = applicationContext.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun writeUserPhoneNumber(value: String) {
        val dataStoreKey = stringPreferencesKey(USER_PHONE_NUMBER)
        applicationContext.applicationContext.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun readUserPhoneNumber() : String? {
        val dataStoreKey = stringPreferencesKey(USER_PHONE_NUMBER)
        val preferences = applicationContext.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun writeUserCurrency(value: String) {
        val dataStoreKey = stringPreferencesKey(USER_CURRENCY)
        applicationContext.applicationContext.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun readUserCurrency() : String? {
        val dataStoreKey = stringPreferencesKey(USER_CURRENCY)
        val preferences = applicationContext.dataStore.data.first()
        return preferences[dataStoreKey]
    }
}