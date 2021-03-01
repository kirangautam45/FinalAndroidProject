package com.example.finalproject.dao

import androidx.room.*
import com.example.finalproject.entity.User

@Dao
interface UserDAO {
    @Insert fun registerUser(user: User)

    @Update fun UpdateUser(user: User)
    @Delete fun DeleteUser(user:User)
    @Query("select * from user where phone=(:phone) and password=(:password)")
    suspend fun checkUser(phone:String,password:String):User
}