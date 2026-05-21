package com.example.foodapp;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    public interface OnShopClickListener {
        void onShopClick(Shop shop);
    }

    private final ArrayList<Shop> shopList;
    private final OnShopClickListener listener;

    public ShopAdapter(ArrayList<Shop> shopList, OnShopClickListener listener) {
        this.shopList = shopList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shop, parent, false);//ορίζω με βάση το xml πως θα εμφανίζεται κάθε κατάστημα
        return new ShopViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Shop shop = shopList.get(position);
        holder.bind(shop, listener);
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    static class ShopViewHolder extends RecyclerView.ViewHolder {
        TextView shopName, shopCategory, shopRating;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            shopName = itemView.findViewById(R.id.shopName);
            shopCategory = itemView.findViewById(R.id.shopCategory);
            shopRating = itemView.findViewById(R.id.shopRating);
        }

        public void bind(Shop shop, OnShopClickListener listener) {
            shopName.setText(shop.getShopName());
            shopCategory.setText("Category: " + shop.getFoodCategory());
            shopRating.setText("Rating: " + shop.getStars() + " stars (" + shop.getNoOfVotes() + " votes)");

            itemView.setOnClickListener(v -> listener.onShopClick(shop));
        }
    }
}