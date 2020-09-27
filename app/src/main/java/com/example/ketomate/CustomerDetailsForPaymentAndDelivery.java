package com.example.ketomate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class CustomerDetailsForPaymentAndDelivery extends AppCompatActivity implements TimePickerFragment.TimePickerListener{

    private TextView mDisplayDate,mDisplayTime;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button confirm;
    private EditText fName,lName,mobile,address;
    private TextView ordDate,time;
    int temp;
    String updateId,appId;
    //private long count;

    private DatabaseReference rootRef;

//    public static final String ORDID = "com.example.ketomate.ORDID";
//    public static final String FNAME = "com.example.ketomate.FNAME";
//    public static final String LNAME = "com.exaple.ketomate.LNAME";
//    public static final String MOBIlE = "com.exaple.ketomate.MOBILE";
//    public static final String ADDRESS = "com.exaple.ketomate.ADDRESS";
    public static final String ORDDATE = "com.exaple.ketomate.ORDDATE";
//    public static final String TIME = "com.exaple.ketomate.TIME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details_for_payment_and_delivery);

        rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Confirmed Orders");
        Query query = db.orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    temp = Integer.parseInt(child.getKey());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        fName = findViewById(R.id.editfName);
        lName =  findViewById(R.id.editlName);
        mobile = findViewById(R.id.editMobile);
        address = findViewById(R.id.editAddress);
        ordDate =  findViewById(R.id.editordDate);
        time = findViewById(R.id.editTime);
        confirm = (Button) findViewById(R.id.btnContinue);

        updateId = getIntent().getStringExtra("appId");

        if (updateId != null){
            confirm.setText("Update");
            retrieveData(fName,lName,mobile,address,ordDate,time);

        }


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v7) {
                validateData();
            }
        });

        mDisplayTime = findViewById(R.id.editTime);
        mDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setCancelable(false);
                timePickerFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

        mDisplayDate =  findViewById(R.id.editordDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar cal = Calendar.getInstance();
                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH);
                        int day = cal.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(
                                CustomerDetailsForPaymentAndDelivery.this,
                                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                                mDateSetListener,
                                year, month, day);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                    }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        Log.d(ORDDATE, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                        String date = month + "/" + day + "/" + year;
                        mDisplayDate.setText(date);
                    }
        };
    }

    private void retrieveData(final EditText fName, final EditText lName, final EditText mobile, final EditText address, final TextView ordDate, final TextView time) {
        rootRef.child("Confirmed Orders").child(updateId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fName.setText(dataSnapshot.child("FirstName").getValue().toString());
                lName.setText(dataSnapshot.child("LastName").getValue().toString());
                mobile.setText(dataSnapshot.child("Mobile").getValue().toString());
                address.setText(dataSnapshot.child("Address").getValue().toString());
                ordDate.setText(dataSnapshot.child("OrderDate").getValue().toString());
                time.setText(dataSnapshot.child("Time").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void validateData() {

        String txtfName = fName.getText().toString();
        String txtlName = lName.getText().toString();
        String txtmobile = mobile.getText().toString();
        String txtaddress = address.getText().toString();
        String txtordDate = ordDate.getText().toString();
        String txttime = time.getText().toString();

        if(TextUtils.isEmpty(txtfName)){
            Toast.makeText(CustomerDetailsForPaymentAndDelivery.this, "Some Fields are empty", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(txtlName)){
            Toast.makeText(CustomerDetailsForPaymentAndDelivery.this, "Some Fields are empty", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(txtmobile)){
            Toast.makeText(CustomerDetailsForPaymentAndDelivery.this, "Some Fields are empty", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(txtaddress)){
            Toast.makeText(CustomerDetailsForPaymentAndDelivery.this, "Some Fields are empty", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(txtordDate)){
            Toast.makeText(CustomerDetailsForPaymentAndDelivery.this, "Please select a date", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(txttime)){
            Toast.makeText(CustomerDetailsForPaymentAndDelivery.this, "Please select a time", Toast.LENGTH_SHORT).show();
        }
        else {
            sendData(txtfName,txtlName,txtmobile,txtaddress,txtordDate,txttime);
        }
    }

    private void sendData(String txtfName, String txtlName, String txtmobile, String txtaddress, String txtordDate, String txttime) {

        appId = String.valueOf(temp+1);

        if(updateId == null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", appId);
            map.put("FirstName", txtfName);
            map.put("LastName", txtlName);
            map.put("Mobile", txtmobile);
            map.put("Address", txtaddress);
            map.put("OrderDate", txtordDate);
            map.put("Time", txttime);

            rootRef.child("Confirmed Orders").child(String.valueOf(appId)).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(CustomerDetailsForPaymentAndDelivery.this, "successfully added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(CustomerDetailsForPaymentAndDelivery.this, OrderDetailsForPaymentAndDelivery.class);
                        intent.putExtra("appId", String.valueOf(appId));
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
        else if (updateId != null) {
            HashMap<String, Object> map = new HashMap<>();
            //map.put("id", appId);
            map.put("FirstName", txtfName);
            map.put("LastName", txtlName);
            map.put("Mobile", txtmobile);
            map.put("Address", txtaddress);
            map.put("OrderDate", txtordDate);
            map.put("Time", txttime);

            rootRef.child("Confirmed Orders").child(String.valueOf(updateId)).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(CustomerDetailsForPaymentAndDelivery.this, "Successfully Updated", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(CustomerDetailsForPaymentAndDelivery.this, OrderDetailsForPaymentAndDelivery.class);
                        intent.putExtra("appId", String.valueOf(updateId));
                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        mDisplayTime.setText(hour + " : " + minute);
    }

    
}