package com.example.ketomate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SecondActivity extends AppCompatActivity {

    /*TextView textView,textView2;
    String s1,s2;*/
    Button btn,btn1,bt;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbref;
    private String userId;
    private ListView mListview;
    TextView medium;
    TextView prawns;
    TextView fish,chicken,egg,cuttlefish,total;
    User user;
    DatabaseReference readref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        medium = findViewById(R.id.op1Value);
        prawns = findViewById(R.id.addedPrawns);
        fish = findViewById(R.id.addedfish);
        chicken = findViewById(R.id.addedChicken);
        egg = findViewById(R.id.addedEgg);
        cuttlefish = findViewById(R.id.addedCuttlefish);
        total = findViewById(R.id.totalPrice);
        user = new User();

        btn = (Button) findViewById(R.id.updateValues);
        btn1 = (Button) findViewById(R.id.deleteOrder);


        final String sessionId = getIntent().getStringExtra("ItemID");
        Toast.makeText(SecondActivity.this, "id is"+sessionId, Toast.LENGTH_SHORT).show();


        readref = FirebaseDatabase.getInstance().getReference().child("Customized_Foods").child(sessionId);

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

        bt = findViewById(R.id.addCart);


        readref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds:dataSnapshot.getChildren()) {

                    medium.setText(dataSnapshot.child("option").getValue().toString());

                    if ("chicken".equalsIgnoreCase(dataSnapshot.child("chicken").getValue().toString()))
                        chicken.setText(dataSnapshot.child("chicken").getValue().toString());
                    if ("prawns".equalsIgnoreCase(dataSnapshot.child("prawns").getValue().toString()))
                        prawns.setText(dataSnapshot.child("prawns").getValue().toString());
                    if ("fish".equalsIgnoreCase(dataSnapshot.child("fish").getValue().toString()))
                        fish.setText(dataSnapshot.child("fish").getValue().toString());
                    if ("cuttlefish".equalsIgnoreCase(dataSnapshot.child("cuttlefish").getValue().toString()))
                        cuttlefish.setText(dataSnapshot.child("cuttlefish").getValue().toString());
                    if ("egg".equalsIgnoreCase(dataSnapshot.child("egg").getValue().toString()))
                        egg.setText(dataSnapshot.child("egg").getValue().toString());

                    total.setText(dataSnapshot.child("total").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



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