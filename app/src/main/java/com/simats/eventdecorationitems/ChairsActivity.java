package com.simats.eventdecorationitems;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChairsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chairs);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        RecyclerView chairsRecyclerView = findViewById(R.id.chairs_recycler_view);
        chairsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Chair> chairList = new ArrayList<>();
        chairList.add(new Chair("Plastic Chairs", "Standard Seating", 4.2, 56, "Available", "₹100/day", R.drawable.ic_chair));
        chairList.add(new Chair("Wedding Chairs", "Premium Seating", 4.8, 38, "Available", "₹150/day", R.drawable.ic_chair));
        chairList.add(new Chair("VIP Cushioned Chairs", "Luxury Seating", 4.9, 22, "Limited", "₹200/day", R.drawable.ic_chair));

        ChairAdapter chairAdapter = new ChairAdapter(chairList);
        chairsRecyclerView.setAdapter(chairAdapter);
    }
}
