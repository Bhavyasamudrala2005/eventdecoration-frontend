package com.simats.eventdecorationitems;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HospitalityItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitality_items);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        RecyclerView hospitalityItemsRecyclerView = findViewById(R.id.hospitality_items_recycler_view);
        hospitalityItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<HospitalityItem> hospitalityItemList = new ArrayList<>();
        hospitalityItemList.add(new HospitalityItem("Banquet Tables", "Tables", 4.5, 33, "Available", "₹400/day", R.drawable.ic_hospitality));
        hospitalityItemList.add(new HospitalityItem("Stage Platform", "Stage Items", 4.7, 17, "Available", "₹500/day", R.drawable.ic_hospitality));
        hospitalityItemList.add(new HospitalityItem("Event Curtains", "Draping", 4.6, 25, "Available", "₹1000/day", R.drawable.ic_hospitality));

        HospitalityItemAdapter hospitalityItemAdapter = new HospitalityItemAdapter(hospitalityItemList);
        hospitalityItemsRecyclerView.setAdapter(hospitalityItemAdapter);
    }
}
