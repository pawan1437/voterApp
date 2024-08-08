package com.example.voterapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface VoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertVote(Vote vote);
    @Query("SELECT COUNT(*) FROM votes WHERE candidateId = :candidateId")
    int getVoteCount(int candidateId);
    @Query("SELECT COUNT(*) FROM votes WHERE id = :userId")
    int countVotesForUser(int userId);

}

