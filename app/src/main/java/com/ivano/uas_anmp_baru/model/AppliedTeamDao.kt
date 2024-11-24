package com.ivano.uas_anmp_baru.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface AppliedTeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg appliedTeam: AppliedTeam)

    @Query("SELECT * FROM appliedTeam")
    fun selectAllAppliedTeam(): List<AppliedTeam>

    @Query("select * from appliedTeam where game = :game and team = :team")
    fun selectAppliedTeam(game: String, team: String): AppliedTeam

    @Delete
    fun deleteAppliedTeam(appliedTeam: AppliedTeam)

    @Query("update appliedTeam set game = :game, team = :team where game = :game and team = :team")
    fun updateAppliedTeam(game: String, team: String)
}