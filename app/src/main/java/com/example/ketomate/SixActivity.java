package com.example.ketomate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SixActivity extends AppCompatActivity {

    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);

        btn1 = (Button) findViewById(R.id.deleteOrder);
        btn2 = (Button) findViewById(R.id.donotdeleteOrder);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity2();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity3();
            }
        });
    }

    public void openActivity2() {
        Intent intent = new Intent(SixActivity.this, SixActivity.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(SixActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}