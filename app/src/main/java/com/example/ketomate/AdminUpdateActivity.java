package com.example.ketomate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminUpdateActivity extends AppCompatActivity {
    TextView e2, e3, e4, e5, e6;
    Button b3;
    ImageButton but1;
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
        setContentView(R.layout.activity_admin_update);

        e2 = findViewById(R.id.KetoName);
        e3 = findViewById(R.id.KetoIn);
        e4 = findViewById(R.id.KetoWeight);
        e5 = findViewById(R.id.Ketocal);
        e6 = findViewById(R.id.KetoCost);


        b3 = findViewById(R.id.btnup);
        but1 =(ImageButton)findViewById(R.id.btnBack);

        st = new StoreAdmin();

        final String value = getIntent().getStringExtra("key");
        Toast.makeText(this, "update id"+value, Toast.LENGTH_SHORT).show();
        dbref = FirebaseDatabase.getInstance().getReference().child("Store").child(value);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(value)) {
                            try {
                                st.setName(e2.getText().toString().trim());
                                st.setIngredients(e3.getText().toString().trim());
                                st.setWeight(Integer.parseInt(e4.getText().toString().trim()));
                                st.setCalories(Integer.parseInt(e5.getText().toString().trim()));
                                st.setCost(Float.parseFloat(e6.getText().toString().trim()));

                                String itemId = dbref.push().getKey();
                                dbref.child("Store").setValue(value);

                                Intent intent = new Intent(AdminUpdateActivity.this, AdminUpdateActivity.class);
                                intent.putExtra("key",itemId);

                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                clearController();

                                Toast.makeText(AdminUpdateActivity.this, "id"+itemId, Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_SHORT).show();
                            }

                        } else
                            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminUpdateActivity.this, AdminActivity2.class);
                startActivity(intent);
            }

        });
    }
}