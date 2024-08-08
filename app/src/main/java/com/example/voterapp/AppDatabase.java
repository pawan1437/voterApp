package com.example.voterapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {User.class,Vote.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract VoteDao voteDao();
}
