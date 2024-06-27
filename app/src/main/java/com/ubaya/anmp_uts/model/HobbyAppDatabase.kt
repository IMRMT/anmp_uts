package com.ubaya.anmp_uts.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.anmp_uts.util.DB_NAME
import com.ubaya.anmp_uts.util.MIGRATION_1_2_Berita
import com.ubaya.anmp_uts.util.MIGRATION_1_2_Paragraf
import com.ubaya.anmp_uts.util.MIGRATION_1_2_User

@Database(entities = arrayOf(Paragraf::class,User::class,Berita::class), version =  1)
abstract class HobbyAppDatabase:RoomDatabase() {
    abstract fun hobbyAppDao() : HobbyAppDao

    companion object {
        @Volatile private var instance: HobbyAppDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context:Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HobbyAppDatabase::class.java,
                DB_NAME).addMigrations(MIGRATION_1_2_Paragraf,MIGRATION_1_2_Berita,MIGRATION_1_2_User).build()
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