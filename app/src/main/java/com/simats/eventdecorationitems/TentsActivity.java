package com.simats.eventdecorationitems;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tents);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        RecyclerView tentsRecyclerView = findViewById(R.id.tents_recycler_view);
        tentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Tent> tentList = new ArrayList<>();
        tentList.add(new Tent("Wedding Tent", "Large Event", 4.9, 42, "Available", "₹500/day", R.drawable.ic_tent));
        tentList.add(new Tent("Canopy Tent", "Medium Event", 4.4, 35, "Available", "₹200/day", R.drawable.ic_tent));
        tentList.add(new Tent("Small Shade Tent", "Small Event", 4.1, 27, "Limited", "₹80/day", R.drawable.ic_tent));

        TentAdapter tentAdapter = new TentAdapter(tentList);
        tentsRecyclerView.setAdapter(tentAdapter);
    }
}
