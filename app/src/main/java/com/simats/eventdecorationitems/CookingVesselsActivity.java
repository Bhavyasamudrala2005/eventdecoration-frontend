package com.simats.eventdecorationitems;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CookingVesselsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_vessels);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        RecyclerView cookingVesselsRecyclerView = findViewById(R.id.cooking_vessels_recycler_view);
        cookingVesselsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<CookingVessel> cookingVesselList = new ArrayList<>();
        cookingVesselList.add(new CookingVessel("Large Cooking Vessels", "Commercial Grade", 4.5, 29, "Available", "₹300/day", R.drawable.ic_cooking));
        cookingVesselList.add(new CookingVessel("Gas Stoves", "Cooking Equipment", 4.2, 45, "Available", "₹40/day", R.drawable.ic_equipment_placeholder_purple));
        cookingVesselList.add(new CookingVessel("Serving Pots", "Serving Ware", 4.8, 15, "Available", "₹50/day", R.drawable.ic_equipment_placeholder_purple));

        CookingVesselsAdapter adapter = new CookingVesselsAdapter(cookingVesselList);
        cookingVesselsRecyclerView.setAdapter(adapter);
    }
}