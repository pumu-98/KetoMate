package com.example.ketomate;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;

import android.widget.Toast;



public class HomeActivity extends AppCompatActivity {
      ImageButton BtnHome,BtnStore,BtnCart,BtnContact,BtnAbout;
      Button BtnLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        BtnHome =(ImageButton)findViewById(R.id.BtnHome);
        BtnStore =(ImageButton)findViewById(R.id.BtnStore);
        BtnCart =(ImageButton)findViewById(R.id.BtnCart);
        BtnContact =(ImageButton)findViewById(R.id.BtnContact);
        BtnAbout =(ImageButton)findViewById(R.id.BtnAbout);
        BtnLog=(Button)findViewById(R.id.BtnLog);

        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(HomeActivity.this, "Home Page", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);

            }
        });
        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(HomeActivity.this, "Store Page", Toast.LENGTH_LONG).show();

//                Intent intent1 = new Intent(getApplicationContext(),StoreActivity.class);
//                startActivity(intent1);

            }
        });

//        BtnCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(HomeActivity.this, "Cart", Toast.LENGTH_LONG).show();
//
//                Intent intent2 = new Intent(getApplicationContext(),StoreActivity.class);
//                startActivity(intent2);
//
//            }
//        });
//        BtnContact.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(HomeActivity.this, "Contact", Toast.LENGTH_LONG).show();
//
//                Intent intent3 = new Intent(getApplicationContext(),StoreActivity.class);
//                startActivity(intent3);
//
//            }
//        });
//        BtnAbout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(HomeActivity.this, "Contact", Toast.LENGTH_LONG).show();
//
//                Intent intent3 = new Intent(getApplicationContext(),StoreActivity.class);
//                startActivity(intent3);
//
//            }
//        });
//        BtnLog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent4 = new Intent(getApplicationContext(),AdminActivity.class);
//                startActivity(intent4);
//
//            }
//        });

        }

    }
