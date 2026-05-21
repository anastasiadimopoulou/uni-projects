package com.example.foodapp;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Order;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Filter ;
import android.Request;
import android.Product;
import android.Shop;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> products;
    private final HashMap<Product, Integer> selectionMap;

    public ProductAdapter(List<Product> products, HashMap<Product, Integer> selectionMap) {
        this.products = products;
        this.selectionMap = selectionMap;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product, selectionMap);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, available;
        EditText amountInput;

        //βρίσκει τα πεδία του product
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
            available = itemView.findViewById(R.id.productAvailable);
            amountInput = itemView.findViewById(R.id.amountInput);
        }

        //
        public void bind(Product product, HashMap<Product, Integer> selectionMap) {
            name.setText(product.getProductName());
            price.setText("Τιμή: " + product.getPrice() + "€");
            available.setText("Διαθέσιμα: " + product.getAvailableAmount());

            amountInput.addTextChangedListener(new TextWatcher() {
                //πριν συμπληρώσουμε την ποσότητα
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}


                //αφού συμπληρώσουμε την ποσότητα
                @Override
                public void afterTextChanged(Editable s) {
                    //έλεγχος εγκυρότητας για την ποσότητα
                    try {
                        int amount = Integer.parseInt(s.toString());
                        if (amount > 0 && amount <= product.getAvailableAmount()) {
                            selectionMap.put(product, amount);
                        } else {
                            selectionMap.remove(product);
                        }
                    } catch (NumberFormatException e) {
                        selectionMap.remove(product);
                    }
                }
            });
        }
    }
}