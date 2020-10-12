package com.example.ketomate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.print.PageRange;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterClass extends FirebaseRecyclerAdapter<StoreAdmin,AdapterClass.myviewholder>
{

   private  Context context;
   private Activity storeActivity;
   public AdapterClass(@NonNull FirebaseRecyclerOptions<StoreAdmin> options, Context context, Activity storeActivity) {
      super(options);
      this.context=context;
      this.storeActivity=storeActivity;
   }

   @Override
   protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final StoreAdmin StoreAdmin) {

           holder.name.setText(StoreAdmin.getName());
           holder.ingredients.setText(StoreAdmin.getIngredients());
           holder.weight.setText(StoreAdmin.getWeight());
           holder.calories.setText(StoreAdmin.getCalories());
           holder.cost.setText(StoreAdmin.getCost());


           holder.addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String itemId = StoreAdmin.getItem_id();
                    String itemName = StoreAdmin.getName();
                    String itemCost = StoreAdmin.getCost();

                    Intent newInt = new Intent(context, ProductDetails.class);
                    newInt.putExtra("itemId",itemId);
                    newInt.putExtra("itemName", itemName);
                    newInt.putExtra("itemCost", itemCost);
                  
                    Intent intent = new Intent();
                    intent.putExtra("itemId",itemId);
                    intent.putExtra("itemName", itemName);
                    intent.putExtra("itemCost", itemCost);
                    
                    storeActivity.startActivity(newInt);
                   // intent.putExtra("itemId", model.getPid());

                }
            });

            holder.addCustomize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String itemId = StoreAdmin.getItem_id();
                    String itemName = StoreAdmin.getName();
                    String itemCost = StoreAdmin.getCost();
                    Intent intent = new Intent();
                    intent.putExtra("itemId",itemId);
                    intent.putExtra("itemName", itemName);
                    intent.putExtra("itemCost", itemCost);
                    storeActivity.startActivity(newInt);
                    //Anduni
//                    getProductDetails(itemId);


                   
                }
            });

            // Cart item class
//            Intent intentFromAdapter = new Intent();
//            String name = intentFromAdapter.getBundleExtra("itemName").toString();
//            String cost = intentFromAdapter.getStringExtra("itemCost");
//            TextView itemName;
//            itemName.setText(name);

   }

   @NonNull
   @Override
   public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder,parent,false);
       return new AdapterClass.myviewholder(view);



   }



   class myviewholder extends RecyclerView.ViewHolder
   {
      public TextView name,ingredients,weight,calories,cost;
      public Button addCustomize;
      public ImageButton addCart;
      //public String itemId;

//      Context context;
      public myviewholder(@NonNull View itemView){
         super(itemView);

         //itemId = getIntent().getStringExtra("itemId");


         name=(TextView)itemView.findViewById(R.id.name);
         ingredients=(TextView)itemView.findViewById(R.id.ingrediants);
          weight=(TextView) itemView.findViewById(R.id.weight);
         calories=(TextView)itemView.findViewById(R.id.calories);
         cost=(TextView)itemView.findViewById(R.id.cost);

         addCustomize=(Button) itemView.findViewById(R.id.addCustomize);
         addCart=(ImageButton)itemView.findViewById(R.id.addCart);



            }


      }

           // getProductDetails(itemId);


//   private void getProductDetails(String itemId){
//       DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Store New");
//       ref.child(itemId).addValueEventListener(new ValueEventListener() {
//           @Override
//           public void onDataChange(@NonNull DataSnapshot snapshot) {
//               if (snapshot.exists()){
//                   StoreAdmin storeAdmin=snapshot.getValue(StoreAdmin.class);
//               }
//           }
//
//           @Override
//           public void onCancelled(@NonNull DatabaseError error) {
//
//           }
//       });
//
//   }
}



