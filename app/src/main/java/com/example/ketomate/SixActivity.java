package com.example.ketomate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SixActivity extends AppCompatActivity {

    Button btn1,btn2;
    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);

        btn1 = (Button) findViewById(R.id.deleteOrder);
        btn2 = (Button) findViewById(R.id.donotdeleteOrder);

        final String sessionId = getIntent().getStringExtra("ItemID");
        //Toast.makeText(SixActivity.this, " delete id is"+sessionId, Toast.LENGTH_SHORT).show();

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                openActivity2();
//            }
//        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SixActivity.this, SecondActivity.class);
                intent.putExtra("ItemID", sessionId);
               // Toast.makeText(SixActivity.this, sessionId, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


        btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Customized_Foods");
                        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                  @Override
                                                                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                      if(dataSnapshot.hasChild(sessionId))
                                                                      {


                                                                          dbRef = FirebaseDatabase.getInstance().getReference().child("Customized_Foods").child(sessionId);
                                                                          dbRef.removeValue();
                                                                          // clearControls();

                                                                          //Feedback to the user via a toast..
                                                                          Toast.makeText(getApplicationContext(),"Data Deleted Sucessfully", Toast.LENGTH_SHORT).show();

                                                                      }
                                                                      else
                                                                      {

                                                                          Toast.makeText(getApplicationContext(),"No Source to Delete", Toast.LENGTH_SHORT).show();
                                                                      }
                                                                  }

                                                                  public void onCancelled(DatabaseError databaseError)
                                                                  {

                                                                  }
                                                              }
                        );


                    }
                }

        );
    }

//    public void openActivity2() {
//        Intent intent = new Intent(SixActivity.this, SixActivity.class);
//        startActivity(intent);
//    }
//
//    public void openActivity3() {
//        Intent intent = new Intent(SixActivity.this, SecondActivity.class);
//        startActivity(intent);
//    }
}