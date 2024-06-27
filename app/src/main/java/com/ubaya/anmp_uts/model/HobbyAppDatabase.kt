package com.ubaya.anmp_uts.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.anmp_uts.util.DB_NAME
import com.ubaya.anmp_uts.util.MIGRATION_1_2_Berita
import com.ubaya.anmp_uts.util.MIGRATION_1_2_Paragraf
import com.ubaya.anmp_uts.util.MIGRATION_1_2_User

@Database(entities = arrayOf(Berita::class), version =  1)
abstract class BeritaDatabase:RoomDatabase() {
    abstract fun beritaDAO() : HobbyAppDao

    companion object {
        @Volatile private var instance: BeritaDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context:Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BeritaDatabase::class.java,
                DB_NAME).addMigrations(MIGRATION_1_2_Berita).build()
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

@Database(entities = arrayOf(User::class), version =  1)
abstract class UserDatabase:RoomDatabase() {
    abstract fun userDAO() : HobbyAppDao

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

@Database(entities = arrayOf(User::class), version =  1)
abstract class ParagrafDatabase:RoomDatabase() {
    abstract fun userDAO() : HobbyAppDao

    companion object {
        @Volatile private var instance: ParagrafDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context:Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ParagrafDatabase::class.java,
                DB_NAME).addMigrations(MIGRATION_1_2_Paragraf).build()
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