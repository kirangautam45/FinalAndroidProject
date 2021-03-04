package com.example.finalproject

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

}