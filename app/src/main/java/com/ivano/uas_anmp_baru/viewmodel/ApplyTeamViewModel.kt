package com.ivano.uas_anmp_baru.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ivano.uas_anmp_baru.model.AppliedTeam
import com.ivano.uas_anmp_baru.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ApplyTeamViewModel(application: Application):AndroidViewModel(application) , CoroutineScope{
    private val job = Job()
    private val _teams = MutableLiveData<List<AppliedTeam>>()
    val teams: MutableLiveData<List<AppliedTeam>> = _teams

    private val _selectedGame = MutableLiveData<String>()
    val selectedGame: MutableLiveData<String> = _selectedGame

    private val _selectedTeam = MutableLiveData<String>()
    val selectedTeam: MutableLiveData<String> = _selectedTeam

    fun addTeam(list:List<AppliedTeam>) {
        launch {
            val db = UserDatabase.buildDatabase(
                getApplication()
            )
            db.appliedTeamDao().insertAll(*list.toTypedArray())
        }
    }

    fun updateSelectedItem(item: String) {
        // Logika update item yang dipilih
        when {
            item.contains("Game") -> _selectedGame.value = item
            item.contains("Team") -> _selectedTeam.value = item
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO


}