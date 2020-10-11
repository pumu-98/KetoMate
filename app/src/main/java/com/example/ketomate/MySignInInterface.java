package com.example.ketomate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MySignInInterface extends AppCompatActivity {

    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    DatabaseReference ref;
    Details details;

    AwesomeValidation awesomeValidation;

    private void clear() {
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
        mLoginBtn = findViewById(R.id.textView2login);

        details = new Details();

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref = FirebaseDatabase.getInstance().getReference().child("Customer");

                if (mPassword.getText().length() < 7)
                    Toast.makeText(getApplicationContext(), "Password is not valid", Toast.LENGTH_SHORT).show();
                else if (mEmail.getText().length() < 1)
                    Toast.makeText(getApplicationContext(), "Email is required", Toast.LENGTH_SHORT).show();
                else if (mPhone.getText().length() < 10)
                    Toast.makeText(getApplicationContext(), "Phone number is not valid", Toast.LENGTH_SHORT).show();
                else if (mFullName.getText().length() < 1)
                    Toast.makeText(getApplicationContext(), "Full Name is required", Toast.LENGTH_SHORT).show();
                else {
//                        Intent newInt = new Intent(MySignInInterface.this, MyUserAccountProfile.class);
//
//                        startActivity(newInt);
//                    }

                    String UserId = ref.push().getKey();

//                        Intent intent = new Intent(MySignInInterface.this, MyUserAccountProfile.class);
//                        intent.putExtra("id", UserId);

                    details.setmFullName(mFullName.getText().toString().trim());
                    details.setmEmail(mEmail.getText().toString().trim());
                    details.setmPhone(mPhone.getText().toString().trim());
                    details.setmPassword(mPassword.getText().toString().trim());

                    //ref.push().setValue(details);
                    ref.child(UserId).setValue(details);

                    Toast.makeText(getApplicationContext(), "Data inserted successfully!", Toast.LENGTH_SHORT).show();
                    Intent newInt = new Intent(MySignInInterface.this, MyUserAccountProfile.class);
                    newInt.putExtra("id", UserId);
                    startActivity(newInt);
                }

            }

        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyLoginInterface.class));
            }
        });

    }

//    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
//            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
//
//    public static boolean checkEmailForValidity(String email) {
//
//        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
//        return matcher.find();
//    }
}
