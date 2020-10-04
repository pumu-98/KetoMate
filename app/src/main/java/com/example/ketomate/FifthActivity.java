package com.example.ketomate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FifthActivity extends AppCompatActivity {

    Button btn;
    RadioButton medium,large;
    FirebaseDatabase database;
    DatabaseReference dbref;
    CheckBox fish,chicken,egg,prawns,cuttlefish;
    TextView txt1,txt2,txt3,txt4,txt5,txt6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        medium=findViewById(R.id.radioButton2);
        large=findViewById(R.id.radioButton3);
        fish=findViewById(R.id.cBoxFish);
        chicken=findViewById(R.id.cBoxChicken);
        prawns=findViewById(R.id.cBoxPrawns);
        egg=findViewById(R.id.cBoxEgg);
        cuttlefish=findViewById(R.id.cBoxCuttlefish);
        txt1=findViewById(R.id.txtportion);
        txt2=findViewById(R.id.txtfish);
        txt3=findViewById(R.id.txtchicken);
        txt4=findViewById(R.id.txtcuttlefish);
        txt5=findViewById(R.id.txtprawns);
        txt6=findViewById(R.id.txtegg);

        final User user=new User();

        final String sessionId = getIntent().getStringExtra("ItemID");
        txt1.setText(getIntent().getStringExtra("portion"));
        txt2.setText(getIntent().getStringExtra("chicken"));
        txt3.setText(getIntent().getStringExtra("fish"));
        txt4.setText(getIntent().getStringExtra("egg"));
        txt5.setText(getIntent().getStringExtra("cuttlefish"));
        txt6.setText(getIntent().getStringExtra("prawns"));
        //Toast.makeText(FifthActivity.this, " updte id is"+sessionId, Toast.LENGTH_SHORT).show();


        btn = (Button) findViewById(R.id.toUpdateValues);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                openActivity2();
//            }
//        });


        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Customized_Foods");
                        updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                  @Override
                                                                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                      if (dataSnapshot.hasChild(sessionId)) {
                                                                          try {

                                                                              int i = 0;

                                                                              if (fish.isChecked()) {
                                                                                  i = i + 1;
                                                                              }
                                                                              if (chicken.isChecked()) {
                                                                                  i = i + 1;
                                                                              }
                                                                              if (egg.isChecked()) {
                                                                                  i = i + 1;
                                                                              }
                                                                              if (prawns.isChecked()) {
                                                                                  i = i + 1;
                                                                              }
                                                                              if (cuttlefish.isChecked()) {
                                                                                  i = i + 1;
                                                                              }

                                                                              if (i <= 3) {


                                                                                  if (medium.isChecked()) {
                                                                                      user.setOption(medium.getText().toString().trim());
                                                                                  } else {
                                                                                      user.setOption(large.getText().toString().trim());
                                                                                  }
                                                                                  user.setCusomizedId(sessionId);

                                                                                  if (fish.isChecked()) {
                                                                                      user.setFish(fish.getText().toString().trim());
                                                                                  } else {
                                                                                      user.setFish("notset");
                                                                                      //dbref.child("us").setValue(user);
                                                                                  }

                                                                                  if (chicken.isChecked()) {
                                                                                      user.setChicken(chicken.getText().toString().trim());

                                                                                  } else {
                                                                                      user.setChicken("notset");
                                                                                      //dbref.child("us").setValue(user);
                                                                                  }
                                                                                  if (egg.isChecked()) {
                                                                                      user.setEgg(egg.getText().toString().trim());

                                                                                  } else {
                                                                                      user.setEgg("notset");
                                                                                      //dbref.child("us").setValue(user);
                                                                                  }
                                                                                  if (prawns.isChecked()) {
                                                                                      user.setPrawns(prawns.getText().toString().trim());

                                                                                  } else {
                                                                                      user.setPrawns("notset");
                                                                                      //dbref.child("us").setValue(user);
                                                                                  }
                                                                                  if (cuttlefish.isChecked()) {
                                                                                      user.setCuttlefish(cuttlefish.getText().toString().trim());
                                                                                  } else {
                                                                                      user.setCuttlefish("notset");
                                                                                      //dbref.child("us").setValue(user);
                                                                                  }


                                                                                  dbref = FirebaseDatabase.getInstance().getReference().child("Customized_Foods").child(sessionId);
                                                                                  dbref.setValue(user);

//
                                                                                  user.setTotal(calcAmount(medium.isChecked(), large.isChecked(), fish.isChecked(), chicken.isChecked(), prawns.isChecked(), egg.isChecked(),
                                                                                          cuttlefish.isChecked()));
                                                                                  dbref.setValue(user);

                                                                                  float tot=user.getTotal();
                                                                                  Toast.makeText(getApplicationContext(),"Total amount is "+tot+"Rs", Toast.LENGTH_LONG).show();

                                                                                  Intent intent = new Intent(FifthActivity.this, SecondActivity.class);
                                                                                  intent.putExtra("ItemID", sessionId);
                                                                                 // Toast.makeText(FifthActivity.this, sessionId, Toast.LENGTH_SHORT).show();
                                                                                  startActivity(intent);


                                                                              }

                                                                              else {
                                                                                  Toast.makeText(FifthActivity.this, "You can add maximum three extra thigs ", Toast.LENGTH_SHORT).show();


                                                                              }


                                                                              //Feedback to the user via a toast..
                                                                              // Toast.makeText(getApplicationContext(), "Data Updated Sucessfully", Toast.LENGTH_SHORT).show();
                                                                          } catch
                                                                          (NumberFormatException
                                                                                          e){
                                                                              Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                                                                          }
                                                                      }
                                                                      else

                                                                      {
                                                                          Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                                                                      }



                                                                  }



                                                                  @Override
                                                                  public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                  }
                                                              }
                        );


                    }
                }

        );

    }
//    public void openActivity2() {
//        Intent intent = new Intent(FifthActivity.this, SecondActivity.class);
//        startActivity(intent);
//    }

    public float calcAmount(boolean medium,boolean large,boolean fish,boolean chicken,boolean prawns,boolean egg,boolean cuttlefish){
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

        if (medium)
            totalamount += medium_price;
        if (large)
            totalamount += large_price;
        if (fish)
            totalamount += fish_price;
        if (chicken)
            totalamount += chicken_price;
        if (prawns)
            totalamount += prawns_price;
        if (egg)
            totalamount += egg_price;
        if (cuttlefish)
            totalamount += cutlefish_price;
//        result.append("Total amount is: " + totalamount + "Rs");
        //Displaying the message on the toast
        // Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();

        float amont = totalamount;


        return amont;

//        user.setTotal(amont);
//        dbref.child(itemId).setValue(user);

    }

}