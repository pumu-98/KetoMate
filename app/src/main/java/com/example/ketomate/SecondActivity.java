package com.example.ketomate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    /*TextView textView,textView2;
    String s1,s2;*/
    Button btn,btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn = (Button) findViewById(R.id.updateValues);
        btn1=(Button)findViewById(R.id.deleteOrder);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity2();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity3();
            }
        });

        /*textView=findViewById(R.id.textView11);
        Intent intent=getIntent();
        s1=intent.getStringExtra("value");
        textView.setText(s1);

        textView2=findViewById(R.id.textView18);
        //Intent intent1=getIntent();
        s2=intent.getStringExtra("Value2");
        textView2.setText(s2);*/


    }

    public void openActivity2() {
        Intent intent1 = new Intent(SecondActivity.this, FifthActivity.class);
        startActivity(intent1);
    }

    public void openActivity3() {
        Intent intent = new Intent(SecondActivity.this, SixActivity.class);
        startActivity(intent);
    }
}