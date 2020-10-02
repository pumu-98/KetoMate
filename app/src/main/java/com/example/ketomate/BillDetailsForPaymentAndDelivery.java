package com.example.ketomate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BillDetailsForPaymentAndDelivery extends AppCompatActivity {

    private Button confPaybtn;

//    EditText ordCha;
//    EditText deliCha;
//    TextView totCha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details_for_payment_and_delivery);

//        ordCha=findViewById(R.id.viewordCharges);
//        deliCha=findViewById(R.id.viewdeliCharges);
//        totCha=findViewById(R.id.viewtotal);
//        TextWatcher textWatcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(!ordCha.getText().toString().equals("") && !deliCha.getText().toString().equals("")){
//                    double temp1 = Integer.parseInt(ordCha.getText().toString());
//                    double temp2 = Integer.parseInt(deliCha.getText().toString());
//                    totCha.setText(String.valueOf("Rs."+(temp1+temp2)));
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
        };


//        confPaybtn=(Button)findViewById(R.id.confPay);
//        confPaybtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast toast2;
//                Context context2 = getApplicationContext();
//                CharSequence text = "Payment Confirmed";
//                int duration = Toast.LENGTH_SHORT;
//
//                toast2 = Toast.makeText(context2, text, duration);
//                toast2.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//                toast2.show();
//
//            }
//        });
//
//        ordCha.addTextChangedListener(textWatcher);
//        deliCha.addTextChangedListener(textWatcher);
//
//    }

}