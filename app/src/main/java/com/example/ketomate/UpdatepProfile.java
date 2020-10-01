package com.example.ketomate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class UpdatepProfile extends AppCompatActivity {

    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    DatabaseReference ref;

    Details details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatep_profile);

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password3);
        mPhone = findViewById(R.id.PhoneNo);

        mRegisterBtn = findViewById(R.id.btn);


       final String e1=getIntent().getStringExtra("id");

       mFullName.setText(getIntent().getStringExtra("val"));
        mEmail.setText(getIntent().getStringExtra("val1"));
        mPassword.setText(getIntent().getStringExtra("val2"));
        mPhone.setText(getIntent().getStringExtra("val3"));


        details = new Details();

        mRegisterBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Customer");
                        updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                  @Override
                                                                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                      if (dataSnapshot.hasChild(e1)) {
                                                                          try {
                                                                              details.setmFullName(mFullName.getText().toString().trim());
                                                                              details.setmEmail(mEmail.getText().toString().trim());
                                                                              details.setmPassword(mPassword.getText().toString().trim());
                                                                              details.setmPhone((mPhone.getText().toString().trim()));

                                                                              ref = FirebaseDatabase.getInstance().getReference().child("Customer").child(e1);
                                                                              ref.setValue(details);
                                                                              //clearControls();

                                                                              //Feedback to the user via a toast..
                                                                              Toast.makeText(getApplicationContext(), "Data Updated Sucessfully", Toast.LENGTH_SHORT).show();
                                                                          } catch (NumberFormatException e) {
                                                                              Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                                                                          }
                                                                      }
                                                                      else

                                                                      {
                                                                          Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                                                                      }
                                                                  }


                                                                  @Override
                                                                  public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                  }
                                                              }
                        );


                    }
                }

        );
    }
}