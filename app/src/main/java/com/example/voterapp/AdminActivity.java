package com.example.voterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginAdminButton;

    private final String ADMIN_USERNAME = "adminadmin";
    private final String ADMIN_PASSWORD = "admin@2806";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginAdminButton = findViewById(R.id.loginBtn2);

        loginAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                    Intent intent = new Intent(AdminActivity.this, ResultActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AdminActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}