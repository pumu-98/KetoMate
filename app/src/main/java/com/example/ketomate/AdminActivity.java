package com.example.ketomate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends AppCompatActivity{
    EditText e2,e3,e4,e5,e6;
    Button b1;
    StoreAdmin st;
    DatabaseReference dbref;

    private void clearController(){

        e2.setText("");
        e3.setText("");
        e4.setText("");
        e5.setText("");
        e6.setText("");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_store);


        e2=findViewById(R.id.KetoName);
        e3=findViewById(R.id.KetoIn);
        e4=findViewById(R.id.KetoWeight);
        e5=findViewById(R.id.Ketocal);
        e6=findViewById(R.id.KetoCost);


        b1=findViewById(R.id.btnInsert);

        st=new StoreAdmin();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dbref = FirebaseDatabase.getInstance().getReference().child("Store New");
                try{


                     if (TextUtils.isEmpty(e2.getText().toString()))
                        Toast.makeText(getApplicationContext(), "please enter Food name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(e3.getText().toString()))
                        Toast.makeText(getApplicationContext(), "please enter Ingredients", Toast.LENGTH_SHORT).show();

                    else {

                        String itemId = dbref.push().getKey();
                        st.setName(e2.getText().toString().trim());
                        st.setIngredients(e3.getText().toString().trim());
//                        st.setWeight (Integer.parseInt (e4.getText().toString().trim()));
//                        st.setCalories(Integer.parseInt(e5.getText().toString().trim()));
//                        st.setCost(Integer.parseInt(e6.getText().toString().trim()));
                         st.setWeight(e4.getText().toString().trim());
                         st.setCalories(e5.getText().toString().trim());
                         st.setCost(e6.getText().toString().trim());




                         //dbref.push().setValue(st);
                        dbref.child(itemId).setValue(st);

                         Intent intent=new Intent(AdminActivity.this,AdminActivity2.class);
                         intent.putExtra("key",itemId);
                         Toast.makeText(AdminActivity.this, "id"+itemId, Toast.LENGTH_SHORT).show();
                         startActivity(intent);

                        Toast.makeText(getApplicationContext(), "data saved successfully", Toast.LENGTH_SHORT).show();
                        clearController();

                    }
                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "invalid data..Re Try", Toast.LENGTH_SHORT).show();

                }

            }

        });

    }
}

