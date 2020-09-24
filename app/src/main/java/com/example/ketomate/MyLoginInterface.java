package com.example.ketomate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyLoginInterface extends AppCompatActivity {

    EditText mPassword, mEmail;
    Button mLoginBtn;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login_interface);

        mEmail = (EditText) findViewById(R.id.Email);
        mPassword = (EditText) findViewById(R.id.Password3);

        mLoginBtn = (Button) findViewById(R.id.loginbtn);

        Details details=new Details();
        /*mLoginBtn.setOnClickListener(new View.OnClickListener() {

            Details details=new Details();












        });
        */
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dbref = FirebaseDatabase.getInstance().getReference().child("Customer");
                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Details details1 = dataSnapshot1.getValue(Details.class);

                            if (mEmail.getText().toString().equalsIgnoreCase(details1.getmEmail().toString()) &&
                                    mPassword.getText().toString().equalsIgnoreCase(details1.getmPassword().toString())) {

                                String data = mEmail.getText().toString().trim();

                                Intent intent = new Intent(MyLoginInterface.this, MyUserAccountProfile.class);

                                intent.putExtra("mail",data);

                                startActivity(intent);
                                break;

                            }
                            else if (!mEmail.getText().toString().equalsIgnoreCase(details1.getmEmail().toString()) &&
                                    mPassword.getText().toString().equalsIgnoreCase(details1.getmPassword().toString())){
                                //Longin failed feedback
                                Toast.makeText(MyLoginInterface.this, "password and email mismatch", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }


        });
    }
}