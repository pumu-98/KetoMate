package com.example.ketomate;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;

public class AdapterClass extends FirebaseRecyclerAdapter<StoreAdmin,AdapterClass.myviewholder>
{

   public AdapterClass(@NonNull FirebaseRecyclerOptions<StoreAdmin> options) {
      super(options);
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
                    String itemName = StoreAdmin.getName();
                    String itemCost = StoreAdmin.getCost();
                    Intent intent = new Intent();
                    intent.putExtra("itemName", itemName);
                    intent.putExtra("itemCost", itemCost);
                    view.getContext().startActivity(intent);
                }
            });

            holder.addCustomize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String itemName = StoreAdmin.getName();
                    String itemCost = StoreAdmin.getCost();
                    Intent intent = new Intent();
                    intent.putExtra("itemName", itemName);
                    intent.putExtra("itemCost", itemCost);
                    view.getContext().startActivity(intent);
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
//      Context context;
      public myviewholder(@NonNull View itemView){
         super(itemView);
         name=(TextView)itemView.findViewById(R.id.name);
         ingredients=(TextView)itemView.findViewById(R.id.ingrediants);
          weight=(TextView) itemView.findViewById(R.id.weight);
         calories=(TextView)itemView.findViewById(R.id.calories);
         cost=(TextView)itemView.findViewById(R.id.cost);

         addCustomize=(Button) itemView.findViewById(R.id.addCustomize);
         addCart=(ImageButton)itemView.findViewById(R.id.addCart);



            }



      }
   }

