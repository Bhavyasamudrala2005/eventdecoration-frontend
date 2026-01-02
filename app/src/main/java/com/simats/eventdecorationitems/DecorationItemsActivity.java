package com.simats.eventdecorationitems;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DecorationItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoration_items);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        RecyclerView decorationItemsRecyclerView = findViewById(R.id.decoration_items_recycler_view);
        decorationItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<DecorationItem> decorationItemList = new ArrayList<>();
        decorationItemList.add(new DecorationItem("LED Lighting System", "Lighting", 4.8, 41, "Available", "₹250/day", R.drawable.ic_decoration));
        decorationItemList.add(new DecorationItem("Flower Stands", "Floral Decor", 4.4, 30, "Available", "₹100/day", R.drawable.ic_decoration));
        decorationItemList.add(new DecorationItem("Backdrop Decor", "Background", 4.9, 28, "Limited", "₹200/day", R.drawable.ic_decoration));
        decorationItemList.add(new DecorationItem("Floral Arches", "Arches", 4.7, 23, "Available", "₹220/day", R.drawable.ic_decoration));

        DecorationItemAdapter decorationItemAdapter = new DecorationItemAdapter(decorationItemList);
        decorationItemsRecyclerView.setAdapter(decorationItemAdapter);
    }
}
