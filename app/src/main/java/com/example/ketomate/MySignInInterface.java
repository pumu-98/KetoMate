package com.example.ketomate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MySignInInterface extends AppCompatActivity {

    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    DatabaseReference ref;
    Details details;

    AwesomeValidation awesomeValidation;

    private void clear(){
        mFullName.setText("");
        mEmail.setText("");
        mPassword.setText("");
        mPhone.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sign_in_interface);


        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password3);
        mPhone = findViewById(R.id.PhoneNo);

        mRegisterBtn = findViewById(R.id.btn);

        details = new Details();


        //Initialize Validation Style
        awesomeValidation = new AwesomeValidation((ValidationStyle.BASIC));

        //Add validation for Name
        //awesomeValidation.addValidation(this,R.id.fullName, RegexTemplate.NOT_EMPTY,"p");
        awesomeValidation.addValidation(this,R.id.fullName, RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        //Add validation for Email
        awesomeValidation.addValidation(this,R.id.Email, Patterns.EMAIL_ADDRESS,R.string.invalid_email);

        //For Mobile Number
        awesomeValidation.addValidation(this,R.id.PhoneNo, "[0-9]{10}$",R.string.invalid_mobile);

        //For Password
        awesomeValidation.addValidation(this,R.id.Password3, ".{6,}",R.string.invalid_password);



        // mLoginBtn = findViewById(R.id.loginbtn);

        //fAuth = FirebaseAuth.getInstance();

        /*if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }*/


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref = FirebaseDatabase.getInstance().getReference().child("Customer");

                String UserId=ref.push().getKey();

                Intent intent=new Intent(MySignInInterface.this,MyUserAccountProfile.class);
                intent.putExtra("email",UserId);
                startActivity(intent);




                details.setmFullName(mFullName.getText().toString().trim());
                details.setmEmail(mEmail.getText().toString().trim());
                details.setmPhone(mPhone.getText().toString().trim());
                details.setmPassword(mPassword.getText().toString().trim());

                //ref.push().setValue(details);
                ref.child(UserId).setValue(details);

                Toast.makeText(getApplicationContext(),"Data inserted successfully!",Toast.LENGTH_SHORT).show();

                //check Validation
                if (awesomeValidation.validate()){
                    //on success
                    Toast.makeText(getApplicationContext(), "Your Details Validate Sucessfully... ", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Validation Failed", Toast.LENGTH_SHORT).show();
                }


                //Intent intent = new Intent(Register.this, UserAccount.class);
                //startActivity(intent);
                /*if(TextUtils.isEmpty((CharSequence) mEmail)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty((CharSequence) mPassword)){
                    mPassword.setError("Password is required");
                    return;
                }
                if(mPassword.length() < 6){
                    mPassword.setError("Paassword must be >= 6 characters");
                    return;
                }*/

                //startActivity(new Intent(getApplicationContext(),Login.class));


                //register the user in firebase

                /*fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User created", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(),Login.class));
                        }else {
                            Toast.makeText(Register.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
            }


        });



       /* mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });*/


    }
}