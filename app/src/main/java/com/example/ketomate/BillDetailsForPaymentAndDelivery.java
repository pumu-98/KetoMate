package com.example.ketomate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BillDetailsForPaymentAndDelivery extends AppCompatActivity {

    private Button confPaybtn;
    private RadioButton payOnlineRadio, payDelRadio;
    private String payMethod;

    private DatabaseReference rootRef;
    String appId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details_for_payment_and_delivery);

        rootRef = FirebaseDatabase.getInstance().getReference().child("Confirmed Payments");

        final TextView ordCha = (TextView)findViewById(R.id.viewordCharges);
        final TextView deliCha = (TextView)findViewById(R.id.viewdeliCharges);
        final TextView totCha = (TextView)findViewById(R.id.viewtotal);

        Intent intent = getIntent();
        appId = intent.getStringExtra("appId");

        getData(deliCha);

        payDelRadio = (RadioButton)findViewById(R.id.payOnDel);
        payOnlineRadio = (RadioButton)findViewById(R.id.payOnline);

        confPaybtn=(Button)findViewById(R.id.confPay);
        confPaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPay();
            }
        });

        ordCha.setText(String.valueOf(1000.00));

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!ordCha.getText().toString().equals("") && !deliCha.getText().toString().equals("")){
                    Double ord = Double.parseDouble(ordCha.getText().toString());
                    Double deli = Double.parseDouble(deliCha.getText().toString());
                    totCha.setText(String.valueOf("Rs."+(ord+deli)));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        ordCha.addTextChangedListener(textWatcher);
        deliCha.addTextChangedListener(textWatcher);

    }

    private void confirmPay() {
        if(payDelRadio.isChecked()){
            payMethod = "payOnDelivey";
            payDelRadio.setChecked(true);
            payOnlineRadio.setChecked(false);

            Intent intent = new Intent(BillDetailsForPaymentAndDelivery.this, payOnDeliveryActivity.class);
            startActivity(intent);

        }
        else if(payOnlineRadio.isChecked()){
            payMethod = "payOnline";
            payDelRadio.setChecked(false);
            payOnlineRadio.setChecked(true);

            Intent intent = new Intent(BillDetailsForPaymentAndDelivery.this, payOnlineActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(BillDetailsForPaymentAndDelivery.this, "Please select a Payment method", Toast.LENGTH_SHORT).show();
        }
    }

    private void getData(final TextView deliCha) {
        rootRef.child(appId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String distance = (String) dataSnapshot.child("Distance").getValue();
                String[] separate = distance.split("Kilometers");
                double d = Double.parseDouble(separate[0]);
                double charge = d*100.0;
                deliCha.setText(String.valueOf(charge));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}