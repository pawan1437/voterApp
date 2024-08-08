package com.example.voterapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    long insert(User user);

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    User getUserByUsernameAndPassword(String username, String password);


}
