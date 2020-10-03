package com.example.ketomate;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class payOnDeliveryActivity extends AppCompatActivity {

    private TextView orderMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_on_delivery);

        orderMsg = (TextView)findViewById(R.id.orderMsgID);

    }
}
