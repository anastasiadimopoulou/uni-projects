package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.Toast;

import android.Order;
import android.Product;
import android.Shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

public class ProductSelectionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button confirmOrderButton;
    private Shop selectedShop;
    private HashMap<Product, Integer> selectedProducts = new HashMap<>(); // προϊόν -> ποσότητα

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_selection);//βρίσκω το αντίστοιχο xml

        //παίρνω τα δεδομένα του καταστήματος από την προηγούμενη οθόνη
        selectedShop = (Shop) getIntent().getSerializableExtra("selectedShop");

        //
        recyclerView = findViewById(R.id.productRecyclerView);
        confirmOrderButton = findViewById(R.id.checkout);

        //γεμίζει την λίστα με τα προιόντα μέσω του ProductAdapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ProductAdapter(selectedShop.getProducts(), selectedProducts));

        //όταν πατηθεί το κουμπί "Checkout"
        confirmOrderButton.setOnClickListener(v -> {
            if (selectedProducts.isEmpty()) {
                Toast.makeText(this, "Δεν έχετε επιλέξει προϊόντα", Toast.LENGTH_SHORT).show();
                return;
            }

            //Δημιουργώ αντικείμενο Order
            Order order = new Order(
                    selectedShop.getShopName(),
                    selectedProducts,
                    selectedShop.getFoodCategory(),
                    selectedShop
            );

            //στέλνω στην επόμενη οθόνη το αντικείμενο order
            Intent intent = new Intent(ProductSelectionActivity.this, OrderSummaryActivity.class);
            intent.putExtra("order", order);
            startActivity(intent);
        });
    }
}
