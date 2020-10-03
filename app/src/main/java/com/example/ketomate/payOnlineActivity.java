package com.example.ketomate;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class payOnlineActivity extends AppCompatActivity {

    ImageButton googlepaybtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_online);
        googlepaybtn =(ImageButton)findViewById(R.id.googlepaybtn);

    }
}
