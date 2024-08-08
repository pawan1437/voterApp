package com.example.voterapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "votes")
public class Vote {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int candidateId;
    public Vote(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }
}

