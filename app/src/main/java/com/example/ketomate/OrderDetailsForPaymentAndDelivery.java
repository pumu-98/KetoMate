package com.example.ketomate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderDetailsForPaymentAndDelivery extends AppCompatActivity {

    private Button  contToBill,editOrd;
    private DatabaseReference rootRef;
    String appId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_for_payment_and_delivery);

        rootRef = FirebaseDatabase.getInstance().getReference().child("Confirmed Orders");

        TextView id =  (TextView)findViewById(R.id.showeditordId);
        TextView fName = (TextView)findViewById(R.id.showeditfName);
        TextView lName = (TextView) findViewById(R.id.showeditlName);
        TextView mobile = (TextView)findViewById(R.id.showeditMobile);
        TextView address = (TextView)findViewById(R.id.showeditAddress);
        TextView ordDate = (TextView)findViewById(R.id.showeditordDate);
        TextView time = (TextView)findViewById(R.id.showeditTime);
        Button editOrd = (Button) findViewById(R.id.editOrd);

        Intent intent = getIntent();
        appId = intent.getStringExtra("appId");

        getData(id,fName,lName,mobile,address,ordDate,time);

        editOrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailsForPaymentAndDelivery.this, CustomerDetailsForPaymentAndDelivery.class);
                intent.putExtra("appId",appId);
                startActivity(intent);
            }
        });

        contToBill = (Button) findViewById(R.id.showbtnContinue);
        contToBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(OrderDetailsForPaymentAndDelivery.this,BillDetailsForPaymentAndDelivery.class);
                startActivity(intent5);

            }

        });

    }

    private void getData(final TextView id, final TextView fName, final TextView lName, final TextView mobile, final TextView address, final TextView ordDate, final TextView time) {
        rootRef.child(appId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                id.setText(dataSnapshot.child("id").getValue().toString());
                fName.setText(dataSnapshot.child("FirstName").getValue().toString());
                lName.setText(dataSnapshot.child("LastName").getValue().toString());
                mobile.setText(dataSnapshot.child("Mobile").getValue().toString());
                address.setText(dataSnapshot.child("Address").getValue().toString());
                ordDate.setText(dataSnapshot.child("OrderDate").getValue().toString());
                time.setText(dataSnapshot.child("Time").getValue().toString());

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}