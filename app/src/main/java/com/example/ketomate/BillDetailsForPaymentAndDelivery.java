package com.example.ketomate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
//    private TextView total;
//    private RadioButton payOnDel,payOnline;
//    private String payMethod;
//    int temp;
//    String appId,updateId;

    EditText ordCha;
    EditText deliCha;
    TextView totCha;

    private DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details_for_payment_and_delivery);

//        rootRef = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Confirmed Orders");
//        Query query = db.orderByKey().limitToLast(1);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot child: dataSnapshot.getChildren()) {
//                    temp = Integer.parseInt(child.getKey());
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//        payOnDel = findViewById(R.id.payOnDel);
//        payOnline = findViewById(R.id.payOnline);
//        total = findViewById(R.id.viewtotal);
        confPaybtn=(Button)findViewById(R.id.confPay);
//
//        updateId = getIntent().getStringExtra("appId");

        confPaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast toast2;
//                Context context2 = getApplicationContext();
//                CharSequence text = "Payment Confirmed";
//                int duration = Toast.LENGTH_SHORT;
//
//                toast2 = Toast.makeText(context2, text, duration);
//                toast2.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//                toast2.show();
//
//                validatePayData();

            }
        });

        ordCha=findViewById(R.id.viewordCharges);
        deliCha=findViewById(R.id.viewdeliCharges);
        totCha=findViewById(R.id.viewtotal);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!ordCha.getText().toString().equals("") && !deliCha.getText().toString().equals("")){
                    double temp1 = Integer.parseInt(ordCha.getText().toString());
                    double temp2 = Integer.parseInt(deliCha.getText().toString());
                    totCha.setText(String.valueOf("Rs."+(temp1+temp2)));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };




        ordCha.addTextChangedListener(textWatcher);
        deliCha.addTextChangedListener(textWatcher);

    }

//    private void validatePayData() {
//
//        //String txtpayOnDel = payOnDel.getText().toString();
//        //String txtpayOnline = payOnline.getText().toString();
//
//
//        if (payOnDel.isChecked()){
//            payMethod = "payOnDelivery";
//        }
//        else if (payOnline.isChecked()){
//            payMethod = "payOnline";
//        }
//
//        else if (!payOnDel.isChecked() && !payOnline.isChecked()){
//            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
//        }
//
//        else {
//            sendPayData(payMethod);
//        }
//    }

//    private void sendPayData(String payMethod) {
//        appId = String.valueOf(temp+1);
//
//        if(updateId == null) {
//            HashMap<String, Object> map = new HashMap<>();
//            //map.put("id", appId);
//            map.put("Total", total);
//            map.put("Method",payMethod);
//
//            rootRef.child("Confirmed Orders").child(String.valueOf(appId)).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(BillDetailsForPaymentAndDelivery.this, "successfully added", Toast.LENGTH_SHORT).show();
//
//                        Intent intent = new Intent(CustomerDetailsForPaymentAndDelivery.this, OrderDetailsForPaymentAndDelivery.class);
//                        intent.putExtra("appId", String.valueOf(appId));
//                        startActivity(intent);
//                        finish();
//                    }
//                }
//            });
//        }
//    }

}