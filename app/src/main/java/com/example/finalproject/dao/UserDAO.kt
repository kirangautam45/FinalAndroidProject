package com.example.finalproject.dao

import androidx.room.*
import com.example.finalproject.entity.User

@Dao
interface UserDAO {
    @Insert
    suspend fun registerUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)


    @Query(value = "select * from User where phone =(:phone) and password=(:password)")
    suspend fun checkUser(phone:String,password:String):User
}