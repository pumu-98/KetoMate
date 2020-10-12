package com.example.ketomate;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class BillDetailsForPaymentAndDelivery extends AppCompatActivity {

    private Button confPaybtn;
    private RadioButton payOnlineRadio, payDelRadio;
    private String payMethod;
    private TextView ordCha,deliCha,totCha;

    private DatabaseReference rootRef;
    String appId;
    int temp;
    String updateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details_for_payment_and_delivery);

        rootRef = FirebaseDatabase.getInstance().getReference().child("Confirmed Payments");

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Confirmed Payments");
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

        ordCha = (TextView)findViewById(R.id.viewordCharges);
        deliCha = (TextView)findViewById(R.id.viewdeliCharges);
        totCha = (TextView)findViewById(R.id.viewtotal);
        confPaybtn=(Button)findViewById(R.id.confPay);
        payDelRadio = (RadioButton)findViewById(R.id.payOnDel);
        payOnlineRadio = (RadioButton)findViewById(R.id.payOnline);

        Intent intent = getIntent();
        appId = intent.getStringExtra("appId");
        getData(deliCha);

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

        confPaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPay();
            }
        });

    }

    private void getData(final TextView deliCha) {
        rootRef.child(appId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String distance = (String) dataSnapshot.child("Distance").getValue();
                String[] separate = distance.split("Kilometers");
                double d = Double.parseDouble(separate[0]);
                double charge = d*30.0;
                deliCha.setText(String.valueOf(charge));

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void confirmPay() {

        String txtordCha = ordCha.getText().toString();
        String txtdeliCha = deliCha.getText().toString();
        String txttotCha = totCha.getText().toString();

        if(payDelRadio.isChecked()){
            payMethod = "pay_On_Delivery";
            payDelRadio.setChecked(true);
            payOnlineRadio.setChecked(false);

            Intent intent = new Intent(BillDetailsForPaymentAndDelivery.this, payOnDeliveryActivity.class);
            startActivity(intent);
        }
        else if(payOnlineRadio.isChecked()){
            payMethod = "pay_Online";
            payDelRadio.setChecked(false);
            payOnlineRadio.setChecked(true);

            Intent intent = new Intent(BillDetailsForPaymentAndDelivery.this, payOnlineActivity.class);
            startActivity(intent);
        }

        if(!payDelRadio.isChecked() && !payOnlineRadio.isChecked()) {
            Toast.makeText(BillDetailsForPaymentAndDelivery.this, "Please select a Payment method", Toast.LENGTH_SHORT).show();
        }
        else{
            sendData(txtordCha,txtdeliCha,txttotCha,payMethod);
        }

    }

    private void sendData(final String txtordCha,final String txtdeliCha,final String txttotCha,final String payMethod) {

       appId = String.valueOf(temp);

        if(updateId == null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("ordCha", txtordCha);
            map.put("deliCha", txtdeliCha);
            map.put("totCha", txttotCha);
            map.put("payMethod", payMethod);


            rootRef.child(String.valueOf(appId)).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(BillDetailsForPaymentAndDelivery.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    }
               }
            });
        }
    }
}