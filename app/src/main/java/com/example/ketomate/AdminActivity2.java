package com.example.ketomate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity2 extends AppCompatActivity {
    TextView e2, e3, e4, e5, e6;
    Button b3, b4,b5;
    StoreAdmin st;
    DatabaseReference dbref;

    private void clearController() {

        e2.setText("");
        e3.setText("");
        e4.setText("");
        e5.setText("");
        e6.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2);

        e2 = findViewById(R.id.KetoName);
        e3 = findViewById(R.id.KetoIn);
        e4 = findViewById(R.id.KetoWeight);
        e5 = findViewById(R.id.Ketocal);
        e6 = findViewById(R.id.KetoCost);


        b3 = findViewById(R.id.btnUpdate);
        b4 = findViewById(R.id.btnDlt);
        b5 = findViewById(R.id.showstore);

        st = new StoreAdmin();

        final String value = getIntent().getStringExtra("key");
        dbref = FirebaseDatabase.getInstance().getReference().child("Store New").child(value);


        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    e2.setText(dataSnapshot.child("name").getValue().toString());
                    e3.setText(dataSnapshot.child("ingredients").getValue().toString());
                    e4.setText(dataSnapshot.child("weight").getValue().toString());
                    e5.setText(dataSnapshot.child("calories").getValue().toString());
                    e6.setText(dataSnapshot.child("cost").getValue().toString());


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemId = dbref.push().getKey();
                dbref.child(itemId).setValue(st);

                Intent intent=new Intent(AdminActivity2.this,AdminUpdateActivity.class);
                intent.putExtra("key",itemId);
                Toast.makeText(AdminActivity2.this, "id"+itemId, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity2.this, AdminUpdateActivity.class);
                startActivity(intent);
            }

        });

//        b5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent=new Intent(AdminActivity2.this,StoreActivity.class);
//                intent.putExtra("key",value);
//                Toast.makeText(AdminActivity2.this, "id"+value, Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//            }
//        });



    }
}
