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
        teamsLD.value = arrayListOf(Team(1, "Phoenix",  1, listOf(
            Member("BlazeX", "Duelist", "A fiery character with red and orange armor, wielding dual pistols."),
            Member("Ghost", "Initiator", "A shadowy figure with a dark cloak, carrying a high-tech bow."),
            Member("Vanguard", "Controller", "A tactical soldier in black and green, equipped with smoke grenades."),
            Member("Siren", "Sentinel", "A tech specialist in sleek blue armor, holding a surveillance drone."),
            Member("Ember", "Duelist", "A character with fiery red hair and a glowing energy blade.")
        )),
            Team(2, "Thunderstrike", 1, listOf(
                Member("Static", "Duelist", "A character with electric-themed armor, wielding a charged blade."),
                Member("Pulse", "Initiator", "A tech-focused agent with EMP grenades."),
                Member("Smoke", "Controller", "A masked figure, releasing smokescreens."),
                Member("Shield", "Sentinel", "A bulky agent with a deployable barrier."),
                Member("Flash", "Duelist", "A speedster character with flashbangs.")
            )),
            Team(3, "Hydra", 2, listOf(
                Member("Thunderstrike", "Fighter", "A powerful brawler with electrified gauntlets."),
                Member("Whisper", "Assassin", "A ninja-like character with dual blades and a dark hood."),
                Member("Stormcaller", "Mage", "A sorcerer with a staff that crackles with lightning."),
                Member("Shieldmaiden", "Tank", "A stalwart knight with a large, glowing shield."),
                Member("Lifebloom", "Support", "A nature-themed healer with green robes and a staff of vines.")
            )),
            Team(4, "Inferno", 2, listOf(
                Member("Blazeheart", "Marksman", "A character with a crossbow that shoots fire-tipped arrows."),
                Member("Nightshade", "Assassin", "A stealthy character with shadowy blades."),
                Member("Frostwing", "Mage", "A mage with ice-themed abilities, wearing a frosty cloak."),
                Member("Stonewall", "Tank", "A massive warrior with rock-themed armor."),
                Member("Serenity", "Support", "A calm healer with water-based healing powers.")
            )),
            Team(5, "Eclipse", 3, listOf(
                Member("Solara", "Mid Lane", "A mystical mage with glowing golden runes and a staff."),
                Member("Ironclad", "Top Lane", "A heavily armored warrior with a massive shield."),
                Member("Swiftwind", "ADC", "An archer with a sleek hood and green energy bow."),
                Member("Moonshade", "Support", "A healer with silver hair, a crescent staff, and flowing robes."),
                Member("Stonefist", "Jungle", "A rugged fighter with gauntlets and earth-based powers.")
            )),
            Team(6, "Dragonscale", 3, listOf(
                Member("Emberdrake", "Mid Lane", "A mage with dragon-themed fire powers."),
                Member("Granite", "Top Lane", "A stone-skinned warrior with a giant mace."),
                Member("Hawkstrike", "ADC", "A precise archer with hawk-like vision."),
                Member("Luna", "Support", "A character with moon-based healing abilities."),
                Member("Viper", "Jungle", "A sneaky assassin with poison-themed attacks.")
            )),
            Team(7, "Vortex", 4, listOf(
                Member("Havoc", "Scout", "A sleek, camo-wearing figure with high-tech binoculars and a sniper rifle."),
                Member("Reaper", "Assault", "A masked character in all black, carrying an assault rifle."),
                Member("Gadgeteer", "Builder", "A tech-savvy engineer with a tool belt and construction equipment."),
                Member("Stealth", "Sniper", "A camouflaged sniper with a ghillie suit."),
                Member("Blitz", "Close Quarters Specialist", "A fast-moving character with a shotgun and a tactical helmet.")
            )),
            Team(8, "Tempest", 4, listOf(
                Member("Cyclone", "Scout", "A character with wind-themed gear and a drone."),
                Member("Onyx", "Assault", "A sleek black-armored fighter with a heavy rifle."),
                Member("Builder Bob", "Builder", "A character with a construction helmet and blueprints."),
                Member("Ghost", "Sniper", "A stealthy character in a white cloak."),
                Member("Thunder", "Close Quarters Specialist", "A fighter with electrified gloves and a shotgun.")
            )),
            Team(9, "Seraph", 5, listOf(
                Member("Lightbringer", "Support", "A radiant character with angelic wings and a glowing staff."),
                Member("Ironforge", "Offlane", "A dwarf-like character with a massive hammer and armor."),
                Member("Shadowblade", "Mid", "A stealthy rogue with dark robes and a cursed blade."),
                Member("Titan", "Carry", "A colossal warrior wielding a flaming sword."),
                Member("Windcaller", "Roamer", "A nimble character with a bow and wind-themed abilities.")
            )),
            Team(10, "Leviathan", 5, listOf(
                Member("Kraken", "Offlane", "A sea monster-themed warrior with a trident."),
                Member("Wraith", "Mid", "A ghostly mage with shadow powers."),
                Member("Maelstrom", "Carry", "A storm-themed character with a spear."),
                Member("Guardian", "Support", "A protector with a giant shield and healing aura."),
                Member("Tracker", "Roamer", "A scout with a crossbow and animal companion.")
            )))
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
}