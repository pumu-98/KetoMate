package com.example.ketomate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetails extends AppCompatActivity {

    private Button addToCartButton;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView productName,productCost;
    private String userName = "";
    private String itemId = "";
    private String itemName = "";
    private String itemCost = "";
    private String itemQuantity = "";

    private Activity productDetailsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Bundle extras = getIntent().getExtras();

        addToCartButton = (Button) findViewById(R.id.pd_add_to_cart_button);
        numberButton = (ElegantNumberButton) findViewById(R.id.number_btn);
        productImage = (ImageView) findViewById(R.id.product_image_details);
        productName = (TextView) findViewById(R.id.product_name_details);
        productCost = (TextView) findViewById(R.id.product_cost_details);

        itemId = extras.getString("itemId");
        itemName = extras.getString("itemName");
        itemCost = extras.getString("itemCost");
        userName = "HardCodedUser";
        itemQuantity="1";

        productImage.setImageResource(R.drawable.roll);
        productName.setText(itemName);
        productCost.setText(itemCost);
        numberButton.setNumber(itemQuantity);
        //getProductDetails(productID);

        productDetailsActivity=this;
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemQuantity=numberButton.getNumber();

                Intent newInt = new Intent(getApplicationContext(),mycartshopping.class);
                newInt.putExtra("itemId",itemId);
                newInt.putExtra("itemName", itemName);
                newInt.putExtra("itemCost", itemCost);
                newInt.putExtra("userName", userName);
                newInt.putExtra("itemQuantity", itemQuantity);
                productDetailsActivity.startActivity(newInt);
            }
        });
    }

    /*
    private void addingToCartList()
    {
        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("itemId", productID);
        cartMap.put("itemName", productName.getText().toString());
        cartMap.put("itemCost", productCost.getText().toString());
        cartMap.put("date", saveCurrentDate);
        cartMap.put("time", saveCurrentTime);
        cartMap.put("quantity", numberButton.getNumber());
        cartMap.put("discount", "");

        cartListRef.child("User View").child(Prevalent.currentOnlineUser.getmPhone())
                .child("Products").child(productID)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            cartListRef.child("Admin View").child(Prevalent.currentOnlineUser.getmPhone())
                                    .child("Products").child(productID)
                                    .updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task)
                                        {
                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(ProductDetails.this, "Added to cart list", Toast.LENGTH_SHORT).show();

                                                Intent intent = new Intent(ProductDetails.this,StoreActivity.class);
                                                startActivity(intent);
                                            }

                                        }
                                    });
                        }
                    }
                });




    }

    private void getProductDetails(String productID)
    {
//        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference().child("Products");
//
//        productRef.child(productID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists())
//                {
//                    StoreAdmin products = snapshot.getValue(StoreAdmin.class);
//
//                    productName.setText(products.getName());
//                    productCost.setText(products.getCost());
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
   }*/


}