package com.ivano.uas_anmp_baru.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ivano.uas_anmp_baru.model.AppliedTeam
import com.ivano.uas_anmp_baru.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ApplyTeamViewModel(application: Application):AndroidViewModel(application) , CoroutineScope{
    private val job = Job()

    fun addTeam(list:List<AppliedTeam>) {
        launch {
            val db = UserDatabase.buildDatabase(
                getApplication()
            )
            db.appliedTeamDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO


}