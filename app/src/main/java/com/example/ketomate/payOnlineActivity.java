package com.example.ketomate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class payOnlineActivity extends AppCompatActivity {

    ImageButton googlepaybtn;
    ImageButton payherebtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_online);

        googlepaybtn = (ImageButton)findViewById(R.id.googlepaybtn);
        payherebtn = (ImageButton)findViewById(R.id.payherebtn);

    }

    public void googlePayClick(View view){
        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pay.google.com/gp/w/u/0/home/signup"));
        startActivity(intent1);
    }

    public void payHereClick(View view){
        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.payhere.lk/account/"));
        startActivity(intent2);
    }
}
