package com.ivano.uas_anmp_baru.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivano.uas_anmp_baru.model.Game

class ListViewModel:ViewModel() {
    val gameLD = MutableLiveData<ArrayList<Game>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh() {
        gameLD.value = arrayListOf(
            Game("1","League of Legends","A fast-paced, competitive online game that blends the speed and intensity of an RTS with RPG elements.","http://dummyimage.com/75x100"
                    + ".jpg/cc0000/ffffff"),
            Game("2","Dota 2","A complex, team-based game that combines strategy with real-time action.","http://dummyimage.com/75x100" +
                    ".jpg/5fa2dd/ffffff"),
            Game("3","Counter-Strike: Global Offensive","A first-person shooter that pits two teams against each other: Terrorists and Counter-Terrorists.","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
        )

        loadingErrorLD.value = false
        loadingLD.value = false
    }

}