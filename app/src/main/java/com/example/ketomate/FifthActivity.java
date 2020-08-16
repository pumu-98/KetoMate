package com.example.ketomate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FifthActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        btn = (Button) findViewById(R.id.toUpdateValues);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity2();
            }
        });
    }
    public void openActivity2() {
        Intent intent = new Intent(FifthActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}