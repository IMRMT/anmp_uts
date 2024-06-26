package com.ubaya.anmp_uts.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.anmp_uts.util.DB_NAME
import com.ubaya.anmp_uts.util.MIGRATION_1_2_User

@Database(entities = arrayOf(User::class), version =  1)
abstract class UserDatabase:RoomDatabase() {
    abstract fun userDAO() : userDAO

    companion object {
        @Volatile private var instance: UserDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context:Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                DB_NAME).addMigrations(MIGRATION_1_2_User).build()
    }

    operator fun invoke(context: Context){
        if(instance != null){
            synchronized(LOCK){
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }
    }

}
