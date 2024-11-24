package com.ivano.uas_anmp_baru.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class, AppliedTeam::class), version =  2)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun appliedTeamDao(): AppliedTeamDao

    companion object {
        @Volatile
        private var instance: UserDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "e-sport").fallbackToDestructiveMigration() // Gunakan jika versi berubah tanpa strategi migrasi
                .build()
        operator fun invoke(context: Context) {
            if(instance == null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}

