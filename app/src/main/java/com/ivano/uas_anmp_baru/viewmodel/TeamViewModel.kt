package com.ivano.uas_anmp_baru.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.ivano.uas_anmp_baru.model.Member
import com.ivano.uas_anmp_baru.model.Team

class TeamViewModel(application: Application): AndroidViewModel(application) {
    val teamsLD = MutableLiveData<ArrayList<Team>>()

    val teamsData = MutableLiveData<ArrayList<Team>>()
    val membersLD =MutableLiveData<ArrayList<Member>>()

    val gameLoadErrorLD = MutableLiveData<Boolean>()
    val teamLoadErrorLD = MutableLiveData<Boolean>()

    fun fetchTeams() {
        // Valorant Teams
        teamsLD.value = arrayListOf(
            Team(1, "Phoenix", 1, listOf(
                Member("BlazeX", "Duelist", "https://avatar.iran.liara.run/public/1"),
                Member("Ghost", "Initiator", "https://avatar.iran.liara.run/public/7"),
                Member("Vanguard", "Controller", "https://avatar.iran.liara.run/public/5"),
                Member("Siren", "Sentinel", "https://avatar.iran.liara.run/public/2"),
                Member("Ember", "Duelist", "https://avatar.iran.liara.run/public/9")
            )),
            Team(2, "Thunderstrike", 1, listOf(
                Member("Static", "Duelist", "https://avatar.iran.liara.run/public/4"),
                Member("Pulse", "Initiator", "https://avatar.iran.liara.run/public/6"),
                Member("Smoke", "Controller", "https://avatar.iran.liara.run/public/8"),
                Member("Shield", "Sentinel", "https://avatar.iran.liara.run/public/10"),
                Member("Flash", "Duelist", "https://avatar.iran.liara.run/public/3")
            )),
            Team(3, "Hydra", 2, listOf(
                Member("Thunderstrike", "Fighter", "https://avatar.iran.liara.run/public/11"),
                Member("Whisper", "Assassin", "https://avatar.iran.liara.run/public/12"),
                Member("Stormcaller", "Mage", "https://avatar.iran.liara.run/public/13"),
                Member("Shieldmaiden", "Tank", "https://avatar.iran.liara.run/public/14"),
                Member("Lifebloom", "Support", "https://avatar.iran.liara.run/public/15")
            )),
            Team(4, "Inferno", 2, listOf(
                Member("Blazeheart", "Marksman", "https://avatar.iran.liara.run/public/16"),
                Member("Nightshade", "Assassin", "https://avatar.iran.liara.run/public/17"),
                Member("Frostwing", "Mage", "https://avatar.iran.liara.run/public/18"),
                Member("Stonewall", "Tank", "https://avatar.iran.liara.run/public/19"),
                Member("Serenity", "Support", "https://avatar.iran.liara.run/public/20")
            )),
            Team(5, "Eclipse", 3, listOf(
                Member("Solara", "Mid Lane", "https://avatar.iran.liara.run/public/21"),
                Member("Ironclad", "Top Lane", "https://avatar.iran.liara.run/public/22"),
                Member("Swiftwind", "ADC", "https://avatar.iran.liara.run/public/23"),
                Member("Moonshade", "Support", "https://avatar.iran.liara.run/public/24"),
                Member("Stonefist", "Jungle", "https://avatar.iran.liara.run/public/25")
            )),
            Team(6, "Dragonscale", 3, listOf(
                Member("Emberdrake", "Mid Lane", "https://avatar.iran.liara.run/public/26"),
                Member("Granite", "Top Lane", "https://avatar.iran.liara.run/public/27"),
                Member("Hawkstrike", "ADC", "https://avatar.iran.liara.run/public/28"),
                Member("Luna", "Support", "https://avatar.iran.liara.run/public/29"),
                Member("Viper", "Jungle", "https://avatar.iran.liara.run/public/30")
            )),
            Team(7, "Vortex", 4, listOf(
                Member("Havoc", "Scout", "https://avatar.iran.liara.run/public/31"),
                Member("Reaper", "Assault", "https://avatar.iran.liara.run/public/32"),
                Member("Gadgeteer", "Builder", "https://avatar.iran.liara.run/public/33"),
                Member("Stealth", "Sniper", "https://avatar.iran.liara.run/public/34"),
                Member("Blitz", "Close Quarters Specialist", "https://avatar.iran.liara.run/public/35")
            )),
            Team(8, "Tempest", 4, listOf(
                Member("Cyclone", "Scout", "https://avatar.iran.liara.run/public/36"),
                Member("Onyx", "Assault", "https://avatar.iran.liara.run/public/37"),
                Member("Builder Bob", "Builder", "https://avatar.iran.liara.run/public/38"),
                Member("Ghost", "Sniper", "https://avatar.iran.liara.run/public/39"),
                Member("Thunder", "Close Quarters Specialist", "https://avatar.iran.liara.run/public/40")
            )),
            Team(9, "Seraph", 5, listOf(
                Member("Lightbringer", "Support", "https://avatar.iran.liara.run/public/41"),
                Member("Ironforge", "Offlane", "https://avatar.iran.liara.run/public/42"),
                Member("Shadowblade", "Mid", "https://avatar.iran.liara.run/public/43"),
                Member("Titan", "Carry", "https://avatar.iran.liara.run/public/44"),
                Member("Windcaller", "Roamer", "https://avatar.iran.liara.run/public/45")
            )),
            Team(10, "Leviathan", 5, listOf(
                Member("Kraken", "Offlane", "https://avatar.iran.liara.run/public/46"),
                Member("Wraith", "Mid", "https://avatar.iran.liara.run/public/47"),
                Member("Maelstrom", "Carry", "https://avatar.iran.liara.run/public/48"),
                Member("Guardian", "Support", "https://avatar.iran.liara.run/public/49"),
                Member("Tracker", "Roamer", "https://avatar.iran.liara.run/public/50")
            ))
        )

    }

    fun getTeamById(gameId: Int) {
        val teamsList = teamsLD.value
        if (teamsList != null) {
            val matchingTeams = arrayListOf<Team>()
            for (team in teamsList) {
                if (team.games_id == gameId) {
                    Log.d("team", team.toString())
                    matchingTeams.add(team)
                }
            }
            teamsData.value = matchingTeams
        }
    }

    fun getMembersByTeamId(teamId: Int) {
        val teamsList = teamsLD.value
        if (teamsList != null) {
            for (team in teamsList) {
                if (team.id == teamId) {
                    Log.d("members", team.members.toString())
                    membersLD.value = ArrayList(team.members)
                }
            }

            val matchingTeams = arrayListOf<Team>()
            for (team in teamsList) {
                if (team.id == teamId) {
                    Log.d("team", team.toString())
                    matchingTeams.add(team)
                }
            }
            teamsData.value = matchingTeams
        }
    }


}