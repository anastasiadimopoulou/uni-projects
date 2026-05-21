package com.example.foodapp;

import android.Order;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

public class OrderSummaryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button confirmOrderButton;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        //παίρνουμε το order από την προηγούμενη οθόνη
        order = (Order) getIntent().getSerializableExtra("order");

        //έλεγχος οτι ήρθε σωστά
        if (order == null) {
            Toast.makeText(this, "⚠ Η παραγγελία δεν φορτώθηκε σωστά.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        //εμφάνιση παραγγελίας με RecyclerView
        recyclerView = findViewById(R.id.detailsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OrderSummaryAdapter(order.getOrder_items()));


        confirmOrderButton = findViewById(R.id.btn_place_order);

        //όταν πατηθεί το κουμπί "Place order"
        confirmOrderButton.setOnClickListener(v -> {
            new SocketThread("Order", null, order, new Handler(Looper.getMainLooper()) { //στελνουμε στο SocketThread "Order"
                @Override
                public void handleMessage(@NonNull Message msg) {
                    if (msg.what == 1) {
                        Toast.makeText(OrderSummaryActivity.this, "Παραγγελία ολοκληρώθηκε με επιτυχία", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(OrderSummaryActivity.this, "Σφάλμα: " + msg.obj, Toast.LENGTH_LONG).show();
                    }
                }
            }).start();

            //πηγαινω στην αρχική οθόνη αφου έχει γίνει το Order
            Intent intent = new Intent(OrderSummaryActivity.this, MainActivity.class);
            intent.putExtra("order", order);
            startActivity(intent);
        });
    }
}
