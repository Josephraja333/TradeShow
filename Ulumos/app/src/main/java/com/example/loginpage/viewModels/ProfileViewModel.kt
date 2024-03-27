package com.example.loginpage.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.loginpage.API.LoginInstance
import com.example.loginpage.API.User
import androidx.lifecycle.viewModelScope
import com.example.loginpage.API.AssignData
import com.example.loginpage.dataStore.DataStoreManager
import com.example.loginpage.API.LoginRequest
import com.example.loginpage.API.DashData
import com.example.loginpage.API.SubjectsData
import com.example.loginpage.API.TimeData
import com.example.loginpage.API.VideosData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _userResponse = MutableStateFlow<List<User>?>(emptyList())
    val userResponse: StateFlow<List<User>?>
        get() = _userResponse.asStateFlow()

    private val _dashResponse = MutableStateFlow<DashData?>(null)
    val dashResponse: StateFlow<DashData?>
        get() = _dashResponse.asStateFlow()

    private val _timeResponse = MutableStateFlow<TimeData?>(null)
    val timeResponse: StateFlow<TimeData?>
        get() = _timeResponse.asStateFlow()

    private val _videosResponse = MutableStateFlow<VideosData?>(null)
    val videosResponse: StateFlow<VideosData?>
        get() = _videosResponse.asStateFlow()

    private val _assignResponse = MutableStateFlow<AssignData?>(null)
    val assignResponse: StateFlow<AssignData?>
        get() = _assignResponse.asStateFlow()

    private val _subjectsResponse = MutableStateFlow<SubjectsData?>(null)
    val subjectsResponse: StateFlow<SubjectsData?>
        get() = _subjectsResponse.asStateFlow()

    private var _dataStoreManager : DataStoreManager? = null

    fun fetchDataFromAPI(email : String,password : String,dataStoreManager: DataStoreManager? = null) {
        viewModelScope.launch {
            try {
                val apiResponse = LoginInstance.api.postData(LoginRequest(email, password))
                _userResponse.value = apiResponse.body()?.response
                LoginInstance.authToken = apiResponse.headers()["Authorization"].toString().substring(7)
                fetchDataFromDashAPI()
                fetchDataFromTimeAPI()
                fetchDataFromVideosAPI()
                fetchDataFromSubjectsAPI()
                if(dataStoreManager!=null) _dataStoreManager = dataStoreManager

                if(dataStoreManager!=null) saveCredentialsToDataStore(email, password, dataStoreManager)
             } catch (e: Exception) {
                 Log.e("ProfileViewModel", "Error fetching data: ${e.message}", e)
             }
        }
    }

    suspend fun fetchDataFromDashAPI() {
            try {
                val apiResponse =  LoginInstance.api.getDash()
                _dashResponse.value  = apiResponse.body()!!
            } catch (e: Exception) {
                Log.e("ProfileViewModelDash", "Error fetching data: ${e.message}", e)
            }
    }

    private suspend fun fetchDataFromTimeAPI() {
        try {
            val apiResponse =  LoginInstance.api.getTime()
            _timeResponse.value  = apiResponse.body()!!
        } catch (e: Exception) {
            Log.e("ProfileViewModelDash", "Error fetching data: ${e.message}", e)
        }
    }

    suspend fun fetchDataFromVideosAPI() {
        try {
            val apiResponse =  LoginInstance.api.getVideos()
            _videosResponse.value  = apiResponse.body()!!
        } catch (e: Exception) {
            Log.e("ProfileViewModelDash", "Error fetching data: ${e.message}", e)
        }
    }

    suspend fun fetchDataFromAssignAPI(subjectId : String) {
        try {
            val apiResponse =  LoginInstance.api.getAssign(subjectId)
            _assignResponse.value  = apiResponse.body()!!
        } catch (e: Exception) {
            Log.e("ProfileViewModelDash", "Error fetching data: ${e.message}", e)
        }
    }

    suspend fun fetchDataFromSubjectsAPI() {
        try {
            val apiResponse =  LoginInstance.api.getSubjects()
            _subjectsResponse.value  = apiResponse.body()!!
        } catch (e: Exception) {
            Log.e("ProfileViewModelDash", "Error fetching data: ${e.message}", e)
        }
    }

    suspend fun postDataToStatusAPI(status: String,assignmentId : String,subjectId : String) {
        try {
            val apiResponse =  LoginInstance.api.postStatus(status,assignmentId, subjectId)
        } catch (e: Exception) {
            Log.e("ProfileViewModelDash", "Error fetching data: ${e.message}", e)
        }
    }

    suspend fun postDataToCompleteAPI(assignmentId : String,subjectId: String,description : String) {
        try {
            val apiResponse =  LoginInstance.api.postComplete(assignmentId,subjectId,description)
        } catch (e: Exception) {
            Log.e("ProfileViewModelDash", "Error fetching data: ${e.message}", e)
        }
    }

    private suspend fun saveCredentialsToDataStore(email: String, password: String, dataStoreManager: DataStoreManager) {
        dataStoreManager.saveMailId(email)
        dataStoreManager.savePassword(password)
        dataStoreManager.saveHasLoggedIn(true)
    }

    suspend fun deleteCredentialsFromDataStore() {
        _dataStoreManager?.deleteMailId()
        _dataStoreManager?.deletePassword()
        _dataStoreManager?.deleteHasLoggedIn()
    }
}