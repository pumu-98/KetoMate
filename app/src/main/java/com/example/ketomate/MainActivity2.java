package com.example.ketomate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {
    User user;
    RadioButton medium,large;
    FirebaseDatabase database;
    DatabaseReference dbref;
    CheckBox fish,chicken,egg,prawns,cuttlefish;
    int i=0;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        user=new User();


        medium=findViewById(R.id.radioButton2);
        large=findViewById(R.id.radioButton3);
        fish=findViewById(R.id.cBoxFish);
        chicken=findViewById(R.id.cBoxChicken);
        prawns=findViewById(R.id.cBoxPrawns);
        egg=findViewById(R.id.cBoxEgg);
        cuttlefish=findViewById(R.id.cBoxCuttlefish);

        btn=(Button)findViewById(R.id.totalPrice);

        final String e="Fish";
        final String e2="Chicken";
        final String e3="Egg";
        final String e4="Prawns";
        final String e5="Cuttlefish";

        dbref=database.getInstance().getReference().child("Customized_Foods");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    i=(int)dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String itemId=dbref.push().getKey();


                int i=0;

                if (fish.isChecked()){
                    i=i+1;
                }
                if (chicken.isChecked()){
                    i=i+1;
                }
                if (egg.isChecked()){
                    i=i+1;
                }
                if (prawns.isChecked()){
                    i=i+1;
                }
                if (cuttlefish.isChecked()){
                    i=i+1;
                }

                if(i<=3) {

//                    String itemId=dbref.push().getKey();
//                    Intent intent = new Intent(MainActivity2.this, SecondActivity.class);
//                    intent.putExtra("ItemID", "Testing...");
//                    Toast.makeText(MainActivity2.this, itemId, Toast.LENGTH_SHORT).show();
//                    startActivity(intent);

                    String m1 = medium.getText().toString();
                    String m2 = large.getText().toString();

                    user.setCusomizedId(itemId);
                    dbref.child(itemId).setValue(user);

                    /////////////////////////////////////////////////////////////////////////////////////////
//                    String val=user.getCusomizedId();
//                    if (user.getCusomizedId() != null) {




//                    }else if ( == null) {
//                        Toast.makeText(MainActivity2.this, "item id null", Toast.LENGTH_SHORT).show();
//                        //////////////////////////////////////////////////////////////////////////////////////
//                    }


                    dbref.child(itemId).setValue(user);

                    if (medium.isChecked()) {
                        user.setOption(m1);
                        dbref.child(itemId).setValue(user);
                    } else {
                        user.setOption(m2);
                        dbref.child(itemId).setValue(user);
                    }

                    if (fish.isChecked()) {
                        user.setFish(e);
                        dbref.child(itemId).setValue(user);
                        //i = i + 1;


                    } else {
                        user.setFish("notset");
                        dbref.child(itemId).setValue(user);
                    }
                    if (chicken.isChecked()) {
                        user.setChicken(e2);
                        dbref.child(itemId).setValue(user);
                        // i = i + 1;

                    } else {
                        user.setChicken("notset");
                        dbref.child(itemId).setValue(user);
                    }
                    if (egg.isChecked()) {
                        user.setEgg(e3);
                        dbref.child(itemId).setValue(user);
                        //i = i + 1;

                    } else {
                        user.setEgg("notset");
                        dbref.child(itemId).setValue(user);
                    }
                    if (prawns.isChecked()) {
                        user.setPrawns(e4);
                        dbref.child(itemId).setValue(user);
                        // i = i + 1;

                    } else {
                        user.setPrawns("notset");
                        dbref.child(itemId).setValue(user);
                    }
                    if (cuttlefish.isChecked()) {
                        user.setCuttlefish(e5);
                        dbref.child(itemId).setValue(user);
                        // i = i + 1;

                    }
                    else {
                        user.setCuttlefish("notset");
                        dbref.child(itemId).setValue(user);
                    }

                    Intent intent = new Intent(MainActivity2.this, SecondActivity.class);
                    intent.putExtra("ItemID", itemId);
                    //Toast.makeText(MainActivity2.this, itemId, Toast.LENGTH_SHORT).show();
                    startActivity(intent);




                    user.setTotal(calcAmount());
                    dbref.child(itemId).setValue(user);

//                    openActivity2();

                }
                else{
                    Toast.makeText(MainActivity2.this, "You can add maximum three extra thigs ", Toast.LENGTH_SHORT).show();
                    //clearControl();
                }
            }

        });
    }
    public void openActivity2(){
        Intent intent=new Intent(MainActivity2.this,SecondActivity.class);
        startActivity(intent);
    }

    public float calcAmount(){
              float totalamount = 0;
        StringBuilder result = new StringBuilder();
        //result.append("Selected Items:");
        float medium_price=300;
        float large_price=400;
        float fish_price=100;
        float chicken_price=200;
        float prawns_price=150;
        float cutlefish_price=190;
        float egg_price=50;

        if (medium.isChecked()) {

            totalamount += medium_price;
        }
        if (large.isChecked()) {

            totalamount += large_price;
        }
        if (fish.isChecked()) {

            totalamount += fish_price;
        }
        if (chicken.isChecked()) {

            totalamount += chicken_price;
        }
        if (prawns.isChecked()) {

            totalamount += prawns_price;
        }
        if (egg.isChecked()) {

            totalamount += egg_price;
        }
        if (cuttlefish.isChecked()) {

            totalamount += cutlefish_price;
        }
        result.append("Total amount is: " + totalamount + "Rs");
        //Displaying the message on the toast
        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();

        float amont = totalamount;

        return amont;

//        user.setTotal(amont);
//        dbref.child(itemId).setValue(user);

    }


}