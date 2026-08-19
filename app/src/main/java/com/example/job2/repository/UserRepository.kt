package com.example.job2.repository

import androidx.lifecycle.LiveData
import com.example.job2.data.User
import com.example.job2.data.UserDao

class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()
    val userCount: LiveData<Int> = userDao.getUserCount()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun update(user: User) {
        userDao.update(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }

    suspend fun getUserById(id: Int): User? {
        return userDao.getUserById(id)
    }
}
