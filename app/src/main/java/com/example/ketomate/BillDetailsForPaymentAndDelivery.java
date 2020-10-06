package com.example.ketomate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BillDetailsForPaymentAndDelivery extends AppCompatActivity {

    //private DatabaseReference rootRef;

    private Button confPaybtn;
    private RadioButton payOnlineRadio, payDelRadio;
    private String payMethod;
    //private String distance;

    TextView ordCha;
    TextView deliCha;
    TextView totCha;

    //Double distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details_for_payment_and_delivery);
//
//        rootRef = FirebaseDatabase.getInstance().getReference();
//
//        Intent intent = getIntent();
//        distance = intent.getStringExtra("");


        ordCha=findViewById(R.id.viewordCharges);
        deliCha=findViewById(R.id.viewdeliCharges);
        totCha=findViewById(R.id.viewtotal);

        payDelRadio = (RadioButton)findViewById(R.id.payOnDel);
        payOnlineRadio = (RadioButton)findViewById(R.id.payOnline);

        confPaybtn=(Button)findViewById(R.id.confPay);
        confPaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPay();
            }
        });

//        TextWatcher textWatcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                //Intent intent5 = getIntent();
//                //distance = intent5.getStringExtra("Distance");
//
//                if(!ordCha.getText().toString().equals("") && !deliCha.getText().toString().equals("")){
//
//                    double temp1 = Integer.parseInt(ordCha.getText().toString());
//                    double temp2 = Integer.parseInt(deliCha.getText().toString());
//
//                    totCha.setText(String.valueOf("Rs."+(temp1+temp2)));;
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        };

//        ordCha.addTextChangedListener(textWatcher);
//        deliCha.addTextChangedListener(textWatcher);

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
    }
}