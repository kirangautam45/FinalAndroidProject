package com.example.finalproject

import com.example.finalproject.entity.User
import com.example.finalproject.repository.UserRepository
import com.example.finalproject.respone.LoginResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test


class ProjectUnitTest {
    private lateinit var UserRepository:UserRepository
    @Test
    fun checkLogin() = runBlocking {
       UserRepository= UserRepository()
        val response = UserRepository.loginUser("123", "123")
        val expectedResult=true
        val actualResult=response.success
        Assert.assertEquals(expectedResult, actualResult)

    }
    @Test
    fun registerUser() = runBlocking {
        val user = User(fname = "test", lname = "test",phone = "984563",address = "ktm", password = "testpassword")
        UserRepository = UserRepository()
        val response = UserRepository.registerUser(user)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

}