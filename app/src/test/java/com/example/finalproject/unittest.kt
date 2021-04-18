package com.example.finalproject

import com.example.finalproject.entity.Bedroom
import com.example.finalproject.entity.User
import com.example.finalproject.repository.BedroomRepository
import com.example.finalproject.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class unittest {
    private lateinit var userRepository: UserRepository

    //...........................USER login and Register Testing...........................//
    @Test
    fun checkLogin() = runBlocking {
        userRepository = UserRepository()
        val response = userRepository.loginUser("123", "123")
        val ExpectedResult = true
        val ActualResult = response.success
        Assert.assertEquals(ExpectedResult, ActualResult)
    }

    @Test
    fun checkRegister() = runBlocking {
        val user = User(fname = "kishan", lname = "sha", phone = "998811", address = "ktm", password = "Jaipur")
        userRepository = UserRepository()
        val response = userRepository.registerUser(user)
        val ExpectedResult = true
        val ActualResult = response.success
        Assert.assertEquals(ExpectedResult, ActualResult)
    }
}
    //------------------------Adding product----------------------
//    private lateinit var bedroomrepository: BedroomRepository
//    @Test
//    fun productadd() = runBlocking {
//        val bedroom = Bedroom(bedname = "goldbed",size = "medium",material = "gold",describe = "It is made up of pure gold",cost = "10000")
//        bedroomrepository = BedroomRepository()
//        val response = bedroomrepository.insertBedroom(bedroom)
//        val ExpectedResult = true
//        val ActualResult = response.success
//        Assert.assertEquals(ExpectedResult,ActualResult)
//    }
//
//
//}
