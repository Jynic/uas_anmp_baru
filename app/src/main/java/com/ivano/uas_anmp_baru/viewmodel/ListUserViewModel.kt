package com.ivano.uas_anmp_baru.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ivano.uas_anmp_baru.model.User
import com.ivano.uas_anmp_baru.model.UserDao
import com.ivano.uas_anmp_baru.model.UserDatabase
import com.ivano.uas_anmp_baru.model.UserDatabase_Impl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListUserViewModel(application: Application):AndroidViewModel(application),CoroutineScope {
    val todoLD = MutableLiveData<List<User>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
    fun refresh() {
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
            val db = UserDatabase.buildDatabase(
                getApplication()
            )
            val todoList = db.userDao().selectAllUsers()

            todoLD.postValue(db.userDao().selectAllUsers())
            loadingLD.postValue(false)

            Log.d("TodoList", todoList.toString())
        }
    }
    fun clearTask(user: User) {
        launch {
            val db = UserDatabase.buildDatabase(
                getApplication()
            )
            db.userDao().deleteUser(user)

            todoLD.postValue(db.userDao().selectAllUsers())
        }
    }
}