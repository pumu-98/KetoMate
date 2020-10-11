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

public class MyUserAccountProfile extends AppCompatActivity {

    TextView mFullName, mEmail, mPassword, mPhone;
    //ImageView profilepicture;

    DatabaseReference profileUserRef;

    private String email, password;
    Button btnUpdate;
    Button btnDelete;
    Details details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_user_account_profile);


        mFullName = (TextView) findViewById(R.id.textView29);
        mEmail = (TextView) findViewById(R.id.textView30);
        mPassword = (TextView) findViewById(R.id.textView31);
        mPhone = (TextView) findViewById(R.id.textView32);

        final String e1 = getIntent().getStringExtra("id");

        profileUserRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(e1);

        //mEmail.setText(el);


        // profilepicture = (ImageView) findViewById(R.id.imageView4);
        btnUpdate = (Button) findViewById(R.id.button6);
        btnDelete = (Button) findViewById(R.id.btn7);


        profileUserRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //if(dataSnapshot.hasChildren()){
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    mFullName.setText(dataSnapshot.child("mFullName").getValue().toString());
                    mEmail.setText(dataSnapshot.child("mEmail").getValue().toString());
                    mPassword.setText(dataSnapshot.child("mPassword").getValue().toString());
                    mPhone.setText(dataSnapshot.child("mPhone").getValue().toString());


                }
//                else {
//                    Toast.makeText(UserAccount.this, "You haven't an account. ", Toast.LENGTH_SHORT).show();
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyUserAccountProfile.this, UpdatepProfile.class);
                intent.putExtra("id", e1);
                intent.putExtra("val", mFullName.getText().toString());
                intent.putExtra("val1", mEmail.getText().toString());
                intent.putExtra("val2", mPassword.getText().toString());
                intent.putExtra("val3", mPhone.getText().toString());
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Customer");
                        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                  @Override
                                                                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                      if (dataSnapshot.hasChild(e1)) {
                                                                          profileUserRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(e1);
                                                                          profileUserRef.removeValue();
                                                                          //clearControls();

                                                                          //Feedback to the user via a toast..
                                                                          Toast.makeText(getApplicationContext(), "Data Deleted Sucessfully", Toast.LENGTH_SHORT).show();
                                                                          Intent intent = new Intent(MyUserAccountProfile.this, DeletedProfile.class);
                                                                          startActivity(intent);

                                                                      } else {
                                                                          Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
                                                                      }
                                                                  }

                                                                  public void onCancelled(DatabaseError databaseError) {

                                                                  }
                                                              }
                        );


                    }
                }

        );

    }

}
