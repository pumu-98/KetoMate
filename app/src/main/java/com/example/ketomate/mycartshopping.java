package com.example.ketomate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class mycartshopping extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterCart adaptercart;
    DatabaseReference cartOrderRef;


   /** @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycartshopping);

        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CartOrder> menus =
                new FirebaseRecyclerOptions.Builder<CartOrder>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Cart Orders"),CartOrder.class)
                        .build();

        adaptercart=new AdapterCart(menus);
        recyclerView.setAdapter(adaptercart);

    }**/

    @Override
    protected void onStart(){
        super.onStart();
        adaptercart.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        adaptercart.stopListening();
    }

    TextView muserID,mitemCost,mitemName,mitemQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycartshopping);



        //ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        //final ConstraintLayout cartView = new ConstraintLayout(this);
        //this.addContentView(cartView, layoutParams);

        cartOrderRef = FirebaseDatabase.getInstance().getReference().child("Cart Orders");

        Bundle extras = getIntent().getExtras();

        CartOrder cartOrder = new CartOrder();
        cartOrder.setUserID(extras.getString("userName"));
        cartOrder.setItemId( extras.getString("itemId"));
        cartOrder.setItemCost( extras.getString("itemCost"));
        cartOrder.setItemName(extras.getString("itemName"));
        cartOrder.setItemQuantity(extras.getString("itemQuantity"));

       /* muserID = (TextView) findViewById(R.id.textView29);
        mitemName = (TextView) findViewById(R.id.item1);
        mitemCost = (TextView) findViewById(R.id.ItemPrice1);
        mitemQuantity = (TextView) findViewById(R.id.no1);*/

        String orderID = cartOrderRef.push().getKey();

        cartOrderRef.child(orderID).setValue(cartOrder);
        /*cartOrderRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        TextView itemText = new TextView(getApplicationContext());
                        itemText.setText(ds.child("itemName").getValue().toString());

                        cartView.addView(itemText);
                        System.out.println("View Added");
                        //muserID.setText(ds.child("userID").getValue().toString());
//                        mitemName.setText(ds.child("itemName").getValue().toString());
//                        mitemCost.setText(ds.child("itemCost").getValue().toString());
//                        mitemQuantity.setText(ds.child("itemQuantity").getValue().toString());
                    }


                } else {
                    System.out.println("No order items");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CartOrder> menus =
                new FirebaseRecyclerOptions.Builder<CartOrder>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Cart Orders"),CartOrder.class)
                        .build();

        adaptercart=new AdapterCart(menus);
        recyclerView.setAdapter(adaptercart);


    }
}