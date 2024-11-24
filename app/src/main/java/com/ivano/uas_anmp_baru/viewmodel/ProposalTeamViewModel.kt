package com.ivano.uas_anmp_baru.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ivano.uas_anmp_baru.model.AppliedTeam
import com.ivano.uas_anmp_baru.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProposalTeamViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<AppliedTeam>>()
    val LoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        LoadErrorLD.value = false
        launch {
            val db = UserDatabase.buildDatabase(
                getApplication()
            )

            val appliedTeamList = db.appliedTeamDao().selectAllAppliedTeam()

            todoLD.postValue(db.appliedTeamDao().selectAllAppliedTeam())
            loadingLD.postValue(false)

            Log.d("appliedTeamList", appliedTeamList.toString())
        }
    }
    fun clearTask(appliedTeam: AppliedTeam) {
        launch {
            val db = UserDatabase.buildDatabase(
                getApplication()
            )
            db.appliedTeamDao().deleteAppliedTeam(appliedTeam)

            todoLD.postValue(db.appliedTeamDao().selectAllAppliedTeam())
        }
    }
}