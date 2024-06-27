package com.ubaya.anmp_uts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Berita(
    @ColumnInfo(name="author")
    var author:String?,
    @ColumnInfo(name="title")
    var title:String?,
    @ColumnInfo(name="descript")
    var descript:String?,
    @ColumnInfo(name="genre")
    var genre:String?,
    @ColumnInfo(name="date")
    var date:String?,
    @ColumnInfo(name="images")
    var images:String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}

@Entity
data class User(
    @ColumnInfo(name="username")
    var username:String?,
    @ColumnInfo(name="password")
    var password:String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}

@Entity
data class Paragraf(
    @ColumnInfo(name="idBerita")
    var idBerita:Int?,
    @ColumnInfo(name="konten")
    var konten:String?,
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}