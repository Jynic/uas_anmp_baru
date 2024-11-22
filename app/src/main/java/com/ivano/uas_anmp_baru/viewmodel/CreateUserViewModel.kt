package com.ivano.uas_anmp_baru.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ivano.uas_anmp_baru.model.User
import com.ivano.uas_anmp_baru.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CreateUserViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    private val job = Job()

    fun addUser(list:List<User>) {
        launch {
            val db = UserDatabase.buildDatabase(
                getApplication()
            )
            db.userDao().insertAll(*list.toTypedArray())
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO


}