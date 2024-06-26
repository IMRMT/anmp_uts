package com.ubaya.anmp_uts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Berita(
    @ColumnInfo(name="author")
    val author:String?,
    @ColumnInfo(name="title")
    val title:String?,
    @ColumnInfo(name="descript")
    val descript:String?,
    @ColumnInfo(name="paragraf")
    val para:List<String>?,
    @ColumnInfo(name="genre")
    val genre:String?,
    @ColumnInfo(name="date")
    val date:String?,
    @ColumnInfo(name="images")
    val images:String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}