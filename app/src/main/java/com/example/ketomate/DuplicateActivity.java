package com.example.ketomate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class DuplicateActivity extends AppCompatActivity {

    ImageButton helloUser,helloAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duplicate);

        helloUser=(ImageButton)findViewById(R.id.hellouser);
        helloAdmin =(ImageButton)findViewById(R.id.helloadmin);

        helloUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(DuplicateActivity.this, "Hello", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);

            }
        });

        helloAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(DuplicateActivity.this, "Admin Access", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
                startActivity(intent);

            }
        });

    }
}