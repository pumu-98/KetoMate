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


        btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("user");
                        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                  @Override
                                                                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                      if(dataSnapshot.hasChild("us"))
                                                                      {


                                                                          dbRef = FirebaseDatabase.getInstance().getReference().child("user").child("us");
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

    public void openActivity2() {
        Intent intent = new Intent(SixActivity.this, SixActivity.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(SixActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}