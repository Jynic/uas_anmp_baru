package com.ivano.uas_anmp_baru.model

data class Game(
    var id:String,
    var name:String?,
    var description:String?,
    var image_url:String?
)

data class Achievement(
    var id:Int?,
    var game_id:Int,
    var year: String?,
    var achievement: String?,
    var team_id: Int?,
    var name: String?
)