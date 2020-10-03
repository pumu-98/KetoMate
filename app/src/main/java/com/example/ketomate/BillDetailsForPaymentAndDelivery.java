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

public class BillDetailsForPaymentAndDelivery extends AppCompatActivity {

    private Button confPaybtn;
    private RadioButton payOnlineRadio, payDelRadio;
    private String payMethod;

    EditText ordCha;
    EditText deliCha;
    TextView totCha;
    TextView dist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details_for_payment_and_delivery);

        payDelRadio = (RadioButton)findViewById(R.id.payOnDel);
        payOnlineRadio = (RadioButton)findViewById(R.id.payOnline);

        confPaybtn=(Button)findViewById(R.id.confPay);
        confPaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPay();
            }
        });

        ordCha=findViewById(R.id.viewordCharges);
        deliCha=findViewById(R.id.viewdeliCharges);
        totCha=findViewById(R.id.viewtotal);
        dist=findViewById(R.id.text_view);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



                if(!ordCha.getText().toString().equals("") && !deliCha.getText().toString().equals("")){

                    double temp1 = Integer.parseInt(ordCha.getText().toString());
                    double temp2 = Integer.parseInt(deliCha.getText().toString());
                    totCha.setText(String.valueOf("Rs."+(temp1+temp2)));;

//                    double orderCharge = Integer.parseInt(ordCha.getText().toString());
//                    double distance=Integer.parseInt(dist.getText().toString());
//                    deliCha.setText(String.valueOf(distance*100));
//                    double deliveryCharge = Integer.parseInt(deliCha.getText().toString());
//                    totCha.setText(String.valueOf("Rs."+(orderCharge+deliveryCharge)));
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

            Intent intent = new Intent(BillDetailsForPaymentAndDelivery.this, payOnDeliveryActivity.class);
            startActivity(intent);

        }
        else if(payOnlineRadio.isChecked()){
            payMethod = "payOnline";

            Intent intent = new Intent(BillDetailsForPaymentAndDelivery.this, payOnlineActivity.class);
            startActivity(intent);
        }
    }
}