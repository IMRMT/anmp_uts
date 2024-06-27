package com.ubaya.anmp_uts.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubaya.anmp_uts.model.HobbyAppDatabase

val DB_NAME= "newdb"

fun buildDb(context:Context):HobbyAppDatabase{
    val db =HobbyAppDatabase.buildDatabase(context)
    return db
}

val MIGRATION_1_2_Berita = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("")
    }
}

val MIGRATION_1_2_User = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("")
    }
}

val MIGRATION_1_2_Paragraf = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("")
    }
}

fun createNotificationChannel(context: Context, importance:Int,
                              showBadge: Boolean, name:String, description:String){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val channelId = "${context.packageName}-$name"
        val channel = NotificationChannel(channelId, name, importance)
        channel.description = description
        channel.setShowBadge(showBadge)

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }
}