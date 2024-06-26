package com.ubaya.anmp_uts.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface userDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUser(vararg user: User)

    @Query("SELECT * FROM user WHERE uuid= :id")
    fun selectUserTodo(id:Int): User

    @Delete
    fun deleteTodoUser(user: User)

    @Query("UPDATE user SET username=:username,password =:password  WHERE uuid=:id")
    fun updateUser(username:String,password:String,id:Int) //id wajib

    @Update
    fun UpdateTodoUser(user: User)

}