package com.example.foodapp;

import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import android.Filter ;
import android.Request;
import android.Product;
import android.Shop;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText categoryEdit, priceEdit;
    private RatingBar starsRatingBar;
    private Button submitButton;
    private Handler handler;

    private final String Latitude = "37.9897";
    private final String Longitude = "23.7260";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Συνδέουμε τα components με το layout
        categoryEdit = findViewById(R.id.categoryEdit);
        priceEdit = findViewById(R.id.priceEdit);
        starsRatingBar = findViewById(R.id.starsRatingBar);
        submitButton = findViewById(R.id.submitButton);

        // Handler για να πάρουμε απάντηση από το SocketThread
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {//στέλνουμε την λίστα με τα φιλτραλισμένα καταστήματα στην επόμενη οθόνη
                if (msg.what == 1) {
                    ArrayList<Shop> shopList = (ArrayList<Shop>) msg.obj;
                    Intent intent = new Intent(MainActivity.this, ShopListActivity.class);
                    intent.putExtra("shopList", shopList);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                }
            }
        };

        // οταν πατατάμε το κουμπί "search"
        submitButton.setOnClickListener(v -> {

            //παίρνουμε τις τιμές για τα φίλτρα
            String category = categoryEdit.getText().toString().trim();
            String price = priceEdit.getText().toString().trim();
            float rating = starsRatingBar.getRating();
            int stars = (int) rating;
            String starsStr = String.valueOf(stars);

            // δημιουργούμε αντικείμενο filter
            Filter filter = new Filter(category, starsStr, price, Latitude, Longitude, 1);

            // ξεκινάμε thread για αποστολή στο backend
            new SocketThread("Filter", filter, null, handler).start();
        });
    }
}