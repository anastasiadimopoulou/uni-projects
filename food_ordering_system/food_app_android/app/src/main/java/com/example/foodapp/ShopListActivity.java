package com.example.foodapp;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
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

public class ShopListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Shop> shopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        //παίρνουμε τα καταστήματα απο την προηγούμενη οθόνη
        shopList = (ArrayList<Shop>) getIntent().getSerializableExtra("shopList");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //όταν έχει επιλεχθεί κατάστημα στέλνουμε τα δεδομένα του στην επόμενη οθόνη
        recyclerView.setAdapter(new ShopAdapter(shopList, selectedShop -> {
            Intent intent = new Intent(ShopListActivity.this, ProductSelectionActivity.class);
            intent.putExtra("selectedShop", selectedShop);
            startActivity(intent);
        }));
    }
}