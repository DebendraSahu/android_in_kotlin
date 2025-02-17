package com.debend.coroutines_example

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: Int, val name: String, val age: Int
)

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)
}
