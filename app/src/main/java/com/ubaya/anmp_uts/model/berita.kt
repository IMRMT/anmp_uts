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
    @ColumnInfo(name="paragraf")
    var para:List<String>?,
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