package com.simats.eventdecorationitems;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CarpetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carpets);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());

        RecyclerView carpetsRecyclerView = findViewById(R.id.carpets_recycler_view);
        carpetsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Carpet> carpetList = new ArrayList<>();
        // Corrected price for Red Carpet to match the detail view
        carpetList.add(new Carpet("Red Carpet", "Event Carpet", 4.6, 32, "Available", "₹8000/day", R.drawable.ic_carpet));
        carpetList.add(new Carpet("Event Carpet", "Floor Covering", 4.3, 21, "Available", "₹300/day", R.drawable.ic_carpet));
        carpetList.add(new Carpet("Mandap Carpet", "Wedding Decor", 4.7, 18, "Available", "₹600/day", R.drawable.ic_carpet));

        CarpetAdapter carpetAdapter = new CarpetAdapter(carpetList);
        carpetsRecyclerView.setAdapter(carpetAdapter);
    }
}
