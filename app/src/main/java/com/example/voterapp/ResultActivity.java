package com.example.voterapp;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.voterapp.Vote;
import com.example.voterapp.VoteDao;
import com.example.voterapp.AppDatabase;


public class ResultActivity extends AppCompatActivity {

    private TextView candidate1CountTextView, candidate2CountTextView,
            candidate3CountTextView, candidate4CountTextView;

    private VoteDao voteDao;
    private AppDatabase db;

    private Button logoutAdminButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        logoutAdminButton = findViewById(R.id.logoutBtn);

        logoutAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        candidate1CountTextView = findViewById(R.id.candidate1_count);
        candidate2CountTextView = findViewById(R.id.candidate2_count);
        candidate3CountTextView = findViewById(R.id.candidate3_count);
        candidate4CountTextView = findViewById(R.id.candidate4_count);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user-db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        voteDao = db.voteDao();

        displayVoteCounts();
    }

    private void displayVoteCounts() {
        int candidate1Count = voteDao.getVoteCount(1);
        int candidate2Count = voteDao.getVoteCount(2);
        int candidate3Count = voteDao.getVoteCount(3);
        int candidate4Count = voteDao.getVoteCount(4);

        candidate1CountTextView.setText(String.valueOf(candidate1Count));
        candidate2CountTextView.setText(String.valueOf(candidate2Count));
        candidate3CountTextView.setText(String.valueOf(candidate3Count));
        candidate4CountTextView.setText(String.valueOf(candidate4Count));

    }
}
