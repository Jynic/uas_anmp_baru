package com.ivano.uas_anmp_baru.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Game(
    var id:String,
    var name:String?,
    var description:String?,
    var image_url:String?
)

data class Achievement(
    var id:Int,
    var game_id:Int,
    var year: String?,
    var achievement: String?,
    var team_id: Int?,
    var name: String?
)

data class Schedule(
    var id:Int,
    var tgl: String,
    var event: String,
    var waktu_mulai: String,
    var waktu_selesai: String,
    var description: String,
    var lokasi: String,
    var teams_id: Int?,
    var games_id: Int?,
    var teams_name: String,
    var games_name: String,
    var image_url: String
)

data class Team(
    var id:Int,
    var name: String,
    var games_id: Int?,
    val members: List<Member>
)

data class Member(
    var name:String,
    var role:String,
    var avatar:String
)

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name = "username") // Primary key
    var username: String,

    @ColumnInfo(name = "firstName")
    var firstName: String,

    @ColumnInfo(name = "lastName")
    var lastName: String,

    @ColumnInfo(name = "password")
    var password: String
)

@Entity
data class AppliedTeam(
    @ColumnInfo(name = "game")
    var game: String,
    @ColumnInfo(name = "team")
    var team: String,
    @ColumnInfo(name = "keterangan")
    var keterangan: String
){
    @PrimaryKey(autoGenerate = true)
    var id_applied_team: Int = 0
}