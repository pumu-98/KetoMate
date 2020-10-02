package com.example.ketomate;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import android.app.DirectAction;
import android.content.Intent;
import android.os.Bundle;

import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class StoreActivity extends AppCompatActivity {


    DatabaseReference dbref;
    ArrayList<StoreAdmin> list;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
//
//
//
//        final String value = getIntent().getStringExtra("key");
        dbref = FirebaseDatabase.getInstance().getReference().child("Store");
//       recyclerView=findViewById(R.id.rv);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (dbref != null) {
            dbref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                        list = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            list.add(ds.getValue(StoreAdmin.class));
                        }
                        AdapterClass adapterClass = new AdapterClass(list);
                        recyclerView.setAdapter(adapterClass);
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(StoreActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
    //
//        dbref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    e2.setText(dataSnapshot.child("name").getValue().toString());
//                    e3.setText(dataSnapshot.child("ingredients").getValue().toString());
//                    e4.setText(dataSnapshot.child("weight").getValue().toString());
//                    e5.setText(dataSnapshot.child("calories").getValue().toString());
//                    e6.setText(dataSnapshot.child("cost").getValue().toString());
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//         }
//        }
//        b3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String itemId = dbref.push().getKey();
//                dbref.child(itemId).setValue(st);
//
//                Intent intent=new Intent(AdminActivity2.this,AdminUpdateActivity.class);
//                intent.putExtra("key",itemId);
//                Toast.makeText(AdminActivity2.this, "id"+itemId, Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//            }
//
//        });
//        b4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminActivity2.this, AdminUpdateActivity.class);
//                startActivity(intent);
//            }
//
//        });


//    }
//}