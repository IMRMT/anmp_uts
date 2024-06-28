package com.ubaya.anmp_uts.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HobbyAppDao {

    //BUAT BERITA
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBerita(vararg berita: Berita)

    @Query("SELECT * FROM berita ORDER BY date DESC")
    fun selectAllBeritaTodo(): List<Berita>

    @Query("SELECT * FROM berita WHERE uuid= :id")
    fun selectBeritaTodo(id:Int): Berita

    @Delete
    fun deleteTodoBerita(berita: Berita)

    @Query("UPDATE berita SET author=:author,title =:title,descript=:descript," +
            "genre=:genre,date=:date,images=:images  WHERE uuid=:id")
    fun updateBerita(author:String,title:String,descript:String,
                     genre:String,date:String,images:String ,id:Int) //id wajib

    @Update
    fun UpdateTodoBerita(berita: Berita)


    //BUAT USER
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUser(vararg user: User)

    @Insert
    fun newUser(user: User)

    @Query("SELECT * FROM user ORDER BY uuid")
    fun selectAllUser(): List<User>

    @Query("SELECT * FROM user WHERE uuid= :id")
    fun selectUser(id:Int): User

    @Delete
    fun deleteUser(user: User)

    @Update()
    fun updateUser(user: User)

    @Update
    fun UpdateTodoUser(user: User)


    //BUAT PARAGRAF
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllParagraf(vararg paragraf: Paragraf)

    @Query("SELECT * FROM paragraf WHERE uuid= :id")
    fun selectParagrafTodo(id:Int): Paragraf

    @Delete
    fun deleteTodoParagraf(paragraf: Paragraf)

    @Query("UPDATE paragraf SET idBerita=:idBerita, konten=:konten  WHERE uuid=:id")
    fun updateParagraf(idBerita:Int,konten:String,id:Int) //id wajib

    @Update
    fun UpdateTodoParagraf(paragraf: Paragraf)
}

