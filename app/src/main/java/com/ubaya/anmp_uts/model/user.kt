package com.ubaya.anmp_uts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name="username")
    val username:String?,
    @ColumnInfo(name="password")
    val password:String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}