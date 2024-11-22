package com.ivano.uas_anmp_baru.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    // Menambahkan atau memperbarui user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user: User)

    // Mengambil semua user
    @Query("SELECT * FROM user")
    fun selectAllUsers(): List<User>

    // Mengambil user berdasarkan username
    @Query("SELECT * FROM user WHERE username = :username")
    fun selectUserByUsername(username: String): User?

    // Menghapus user
    @Delete
    fun deleteUser(user: User)
}
