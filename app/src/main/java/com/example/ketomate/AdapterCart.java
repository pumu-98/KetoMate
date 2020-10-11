package com.example.ketomate;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class AdapterCart extends FirebaseRecyclerAdapter<CartOrder,AdapterCart.myviewholder>
{

    private ViewGroup parent;

    public AdapterCart(@NonNull FirebaseRecyclerOptions<CartOrder>menus) {
        super(menus);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterCart.myviewholder holder, int position, @NonNull final CartOrder CartOrder) {

        holder.name.setText(CartOrder.getItemName());

        holder.cost.setText(CartOrder.getItemCost());

        //holder.cost.setText(CartOrder.getItemQuantity());

//        holder.addCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) { String itemId = StoreAdmin.getItem_id();
//                String itemName = StoreAdmin.getName();
//                String itemCost = StoreAdmin.getCost();
//                Intent intent = new Intent();
//                intent.putExtra("itemId",itemId);
//                intent.putExtra("itemName", itemName);
//                intent.putExtra("itemCost", itemCost);
//                view.getContext().startActivity(intent);
//            }
//        });
//
//        holder.addCustomize.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String itemId = StoreAdmin.getItem_id();
//                String itemName = StoreAdmin.getName();
//                String itemCost = StoreAdmin.getCost();
//                Intent intent = new Intent();
//                intent.putExtra("itemId",itemId);
//                intent.putExtra("itemName", itemName);
//                intent.putExtra("itemCost", itemCost);
//                view.getContext().startActivity(intent);
//            }
//        });

        //Cart item class
           // Intent intentFromAdapter = new Intent();
            //String ItemName = intentFromAdapter.getBundleExtra("itemName").toString();
            //String ItemCost = intentFromAdapter.getStringExtra("itemCost").toString();
             //TextView name,cost;
//        name.setText(ItemName);
//        cost.setText(ItemCost);


   }


    @NonNull
    @Override
    public AdapterCart.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_cart,parent,false);
        return new AdapterCart.myviewholder(view);
    }



   class myviewholder extends RecyclerView.ViewHolder
    {
        public TextView name ,cost;

        //      Context context;
        public myviewholder(@NonNull View itemView){
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.name);

            cost=(TextView)itemView.findViewById(R.id.cost);





        }



    }
}



