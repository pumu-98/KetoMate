package com.example.ketomate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

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
        final String e5="Cuttle fish";

        dbref=database.getInstance().getReference().child("user");

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

                String m1=medium.getText().toString();
                String m2=large.getText().toString();

                dbref.child(String.valueOf(i+1)).setValue(user);

                if (medium.isChecked()){
                    user.setOption(m1);
                    dbref.child(String.valueOf(i+1)).setValue(user);
                }else {
                    user.setOption(m2);
                    dbref.child(String.valueOf(i+1)).setValue(user);
                }

                if (fish.isChecked()){
                    user.setAddExtra1(e);
                    dbref.child(String.valueOf(i+1)).setValue(user);


                }else {
                }
                if (chicken.isChecked()){
                    user.setAddExtra2(e2);
                    dbref.child(String.valueOf(i+1)).setValue(user);

                }else {
                }
                if (egg.isChecked()){
                    user.setAddExtra3(e3);
                    dbref.child(String.valueOf(i+1)).setValue(user);

                }else {
                }
                if (prawns.isChecked()){
                    user.setAddExtra4(e4);
                    dbref.child(String.valueOf(i+1)).setValue(user);

                }else {
                }
                if (cuttlefish.isChecked()){
                    user.setAddExtra5(e5);
                    dbref.child(String.valueOf(i+1)).setValue(user);

                }

                openActivity2();
            }
        });
    }
    public void openActivity2(){
        Intent intent=new Intent(MainActivity2.this,SecondActivity.class);
        startActivity(intent);
    }

}