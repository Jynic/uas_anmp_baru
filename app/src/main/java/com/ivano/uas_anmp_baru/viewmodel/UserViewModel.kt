package com.ivano.uas_anmp_baru.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ivano.uas_anmp_baru.model.User
import com.ivano.uas_anmp_baru.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    var userLoginLD = MutableLiveData<User>()
    val isAgreed = MutableLiveData<Boolean>(false)

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun addUser(list:List<User>) {
        launch {
            val db = UserDatabase.buildDatabase(
                getApplication()
            )
            db.userDao().insertAll(*list.toTypedArray())
        }
    }

    fun fetchLogin(username: String, password: String){
        launch {
            try {
                val db = UserDatabase.buildDatabase(
                    getApplication()
                )
                userLoginLD.postValue(db.userDao().loginUsers(username, password))

                Log.d("showUserLogin", userLoginLD.value.toString())
            } catch (e: Exception) {
                Log.e("checkerror", e.toString())
            }
        }
    }

    fun isUserLoggedIn(): Boolean{
        return userLoginLD.value != null
    }

}