package com.example.voterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;



public class VotingActivity extends AppCompatActivity {
    private AppDatabase db;
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);


        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button voteButton = findViewById(R.id.voteButton);

        userId = getIntent().getIntExtra("USER_ID", -1);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user-db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int selectedCandidateId = radioGroup.getCheckedRadioButtonId();
                if (selectedCandidateId == -1) {
                    Toast.makeText(VotingActivity.this, "Please select a candidate", Toast.LENGTH_SHORT).show();
                    return;
                }

                final int candidateId = getCandidateId(selectedCandidateId);

                if (candidateId == 0) {
                    Toast.makeText(VotingActivity.this, "Invalid candidate selection", Toast.LENGTH_SHORT).show();
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean hasVoted = db.voteDao().countVotesForUser(userId) > 0;
                        if (!hasVoted) {
                            db.voteDao().insertVote(new Vote(candidateId));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(VotingActivity.this, "Vote submitted successfully", Toast.LENGTH_SHORT).show();
                                    finishActivity();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(VotingActivity.this, "You have already voted for a candidate", Toast.LENGTH_SHORT).show();
                                    finishActivity();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }

    public void finishActivity() {
        Intent votingIntent = new Intent(VotingActivity.this, MainActivity.class);
        startActivity(votingIntent);
        finish();
    }

    private static final int CANDIDATE_1_ID = R.id.candidate1;
    private static final int CANDIDATE_2_ID = R.id.candidate2;
    private static final int CANDIDATE_3_ID = R.id.candidate3;
    private static final int CANDIDATE_4_ID = R.id.candidate4;

    private int getCandidateId(int selectedCandidateId) {
        if (selectedCandidateId == CANDIDATE_1_ID) {
            return 1;
        } else if (selectedCandidateId == CANDIDATE_2_ID) {
            return 2;
        } else if (selectedCandidateId == CANDIDATE_3_ID) {
            return 3;
        } else if (selectedCandidateId == CANDIDATE_4_ID) {
            return 4;
        } else {
            return 0;
        }
    }
}
