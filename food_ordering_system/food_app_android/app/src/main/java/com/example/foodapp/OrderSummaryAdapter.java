package com.example.foodapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.Product;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.OrderViewHolder> {

    private final List<Map.Entry<Product, Integer>> orderItems; // hashmap της μορφής <προιον, ποσότητα >

    public OrderSummaryAdapter(HashMap<Product, Integer> orderMap) {
        this.orderItems = new ArrayList<>(orderMap.entrySet());
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {// φτιάχνει την λίστα, την εντοπίζει ωσ xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_summary, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {//πέρνει τια τιμές από το προιον και γεμίζει τα πεδία
        Product product = orderItems.get(position).getKey();
        int quantity = orderItems.get(position).getValue();
        double totalPrice = product.getPrice() * quantity;

        holder.productNameText.setText("Προϊόν: " + product.getProductName());// γεμίζω τα πεδία
        holder.quantityText.setText("Ποσότητα: " + quantity);
        holder.priceText.setText("Σύνολο: " + totalPrice + "€");
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView productNameText, quantityText, priceText;

        public OrderViewHolder(@NonNull View itemView) {//εντοπίζει τα πεδία της οθόνης από το xml
            super(itemView);
            productNameText = itemView.findViewById(R.id.productNameText);
            quantityText = itemView.findViewById(R.id.quantityText);
            priceText = itemView.findViewById(R.id.priceText);
        }
    }
}
