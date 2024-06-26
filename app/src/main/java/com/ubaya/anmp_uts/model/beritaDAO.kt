package com.ubaya.anmp_uts.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface beritaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBerita(vararg berita: Berita)

    @Query("SELECT * FROM berita ORDER BY date DESC")
    fun selectAllBeritaTodo(): List<Berita>

    @Query("SELECT * FROM berita WHERE uuid= :id")
    fun selectBeritaTodo(id:Int): Berita

    @Delete
    fun deleteTodoBerita(berita: Berita)

    @Query("UPDATE berita SET author=:author,title =:title,descript=:descript," +
            "paragraf=:paragraf,genre=:genre,date=:date,images=:images  WHERE uuid=:id")
    fun updateBerita(author:String,title:String,descript:String,paragraf:String,
                     genre:String,date:String,images:String ,id:Int) //id wajib

    @Update
    fun UpdateTodoBerita(berita: Berita)

}