package com.example.petopia.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.petopia.db.AppDatabase;
import com.example.petopia.db.Item;
import com.example.petopia.db.ItemDAO;
import java.util.List;
import com.example.petopia.R;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.CartViewHolder> {

    List<Item> items;
    TextView cartItemTotalPrice, cartTotalPrice, cartDeliveryCharge;
    private LayoutInflater inflater;

    public cartAdapter(List<Item> items, TextView cartItemTotalPrice, TextView cartTotalPrice, TextView cartDeliveryCharge) {
        this.items = items;
        this.cartItemTotalPrice = cartItemTotalPrice;
        this.cartTotalPrice = cartTotalPrice;
        this.cartDeliveryCharge = cartDeliveryCharge;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(com.example.petopia.R.layout.cart_item_format, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {

        int price = Integer.parseInt(items.get(position).getPrice());
        int totalPrice = price * items.get(position).getQuantity();


        holder.cartItemTitle.setText(String.valueOf(items.get(position).getTitle()));
        holder.cartItemPrice.setText(String.valueOf(totalPrice));
        holder.cartItemQuantity.setText(String.valueOf(items.get(position).getQuantity()));


        holder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qnt = items.get(position).getQuantity();
                qnt++;
                items.get(position).setQuantity(qnt);
                notifyDataSetChanged();
                updatePrice();
            }
        });

        holder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qnt = items.get(position).getQuantity();
                if (qnt > 1) {
                    qnt--;
                    items.get(position).setQuantity(qnt);
                    notifyDataSetChanged();
                    updatePrice();
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = Room.databaseBuilder(holder.cartItemTitle.getContext(), AppDatabase.class, "cart_db").allowMainThreadQueries().build();
                ItemDAO itemDAO = db.itemDAO();
                itemDAO.deleteById(items.get(position).getId());
                items.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, items.size()); // Notify item range changed to update positions
                updatePrice();
            }
        });


    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        TextView cartItemTitle, cartItemPrice, cartItemQuantity;
        LinearLayout cartItemLayout;
        ImageButton delete;
        Button increment, decrement;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            cartItemTitle = itemView.findViewById(R.id.cartItemTitle);
            cartItemPrice = itemView.findViewById(R.id.cartItemPrice);
            cartItemQuantity = itemView.findViewById(R.id.cartItemQuantity);
            cartItemLayout = itemView.findViewById(R.id.cartItemLayout);
            delete = itemView.findViewById(R.id.cartItemDeleteBtn);
            increment = itemView.findViewById(R.id.id_cartQuantityIncrement);
            decrement = itemView.findViewById(R.id.id_cartQuantityDecrement);
        }
    }


    private void updatePrice() {
        int sum = 0, total = 0, delivery = 110;
        for (int i = 0; i < items.size(); i++) {
            sum = sum + (Integer.parseInt(items.get(i).getPrice()) * items.get(i).getQuantity());
        }
        total += sum + delivery;


        cartItemTotalPrice.setText(String.valueOf(sum)+" tk");
        if(sum == 0){
            cartTotalPrice.setText("0 tk");
            cartDeliveryCharge.setText("0 tk");
        }else {
            cartTotalPrice.setText(String.valueOf(total) + " tk");
            cartDeliveryCharge.setText(String.valueOf(delivery) + " tk");
        }


    }


}
