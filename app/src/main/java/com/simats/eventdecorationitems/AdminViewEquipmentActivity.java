package com.simats.eventdecorationitems;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;

public class AdminViewEquipmentActivity extends AppCompatActivity {

    private RecyclerView equipmentRecyclerView;
    private AdminEquipmentAdapter adapter;
    private ArrayList<Equipment> equipmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_equipment);

        ImageView backButton = findViewById(R.id.back_button);
        EditText searchEditText = findViewById(R.id.search_edit_text);
        ChipGroup categoryChipGroup = findViewById(R.id.category_chip_group);
        equipmentRecyclerView = findViewById(R.id.equipment_recycler_view);

        equipmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdminEquipmentAdapter(this, equipmentList);
        equipmentRecyclerView.setAdapter(adapter);

        backButton.setOnClickListener(v -> onBackPressed());

        loadDummyData();
    }

    private void loadDummyData() {
        // Speakers (IDs 1, 2, 3)
        equipmentList.add(new Equipment("1", "Large Event Speakers", "Speakers", "Professional Audio", "2000W, Bluetooth enabled, Weather resistant", 150, "Available", R.drawable.ic_large_event_speakers));
        equipmentList.add(new Equipment("2", "Bluetooth Speakers", "Speakers", "Portable", "500W, Rechargeable battery, 10hr playback", 50, "Available", R.drawable.ic_bluetooth_speaker));
        equipmentList.add(new Equipment("3", "Stage Speakers", "Speakers", "Concert Grade", "3000W, Line array system, Professional grade", 250, "Limited", R.drawable.ic_stage_speakers));
        
        // Carpets (IDs 4, 5, 6)
        equipmentList.add(new Equipment("4", "Red Carpet", "Carpets", "Event Carpet", "50ft x 4ft, Velvet finish, Premium quality", 100, "Available", R.drawable.ic_red_carpet));
        equipmentList.add(new Equipment("5", "Event Carpet", "Carpets", "Floor Covering", "100 sq ft, Durable material, Easy to clean", 75, "Available", R.drawable.ic_event_carpet));
        equipmentList.add(new Equipment("6", "Mandap Carpet", "Carpets", "Wedding Decor", "200 sq ft, Traditional design, Luxury finish", 120, "Available", R.drawable.ic_mandap_carpet));
        
        // Tents (IDs 7, 8, 9)
        equipmentList.add(new Equipment("7", "Wedding Tent", "Tents", "Large Event", "40x60ft, Waterproof, Seats 200 people", 500, "Available", R.drawable.ic_wedding_tent));
        equipmentList.add(new Equipment("8", "Canopy Tent", "Tents", "Medium Event", "20x20ft, UV protection, Pop-up design", 200, "Available", R.drawable.ic_canopy_tent));
        equipmentList.add(new Equipment("9", "Small Shade Tent", "Tents", "Small Event", "10x10ft, Portable, Easy setup", 80, "Limited", R.drawable.ic_small_shade_tent));
        
        // Cooking Vessels (IDs 10, 11, 12)
        equipmentList.add(new Equipment("10", "Large Cooking Vessels", "Cooking Vessels", "Commercial Grade", "50L capacity, Stainless steel, Heavy duty", 60, "Available", R.drawable.ic_large_cooking_vessels));
        equipmentList.add(new Equipment("11", "Gas Stoves", "Cooking Vessels", "Cooking Equipment", "4 burner, LPG compatible, Commercial use", 40, "Available", R.drawable.ic_gas_stove));
        equipmentList.add(new Equipment("12", "Serving Pots", "Cooking Vessels", "Serving Ware", "Set of 10, Insulated, Buffet style", 50, "Available", R.drawable.ic_serving_pots));
        
        // Chairs (IDs 13, 14, 15)
        equipmentList.add(new Equipment("13", "Plastic Chairs", "Chairs", "Standard Seating", "Stackable, Lightweight, Set of 50", 100, "Available", R.drawable.ic_plastic_chair));
        equipmentList.add(new Equipment("14", "Wedding Chairs", "Chairs", "Premium Seating", "Chiavari style, Gold finish, Cushioned", 150, "Available", R.drawable.ic_wedding_chairs));
        equipmentList.add(new Equipment("15", "VIP Cushioned Chairs", "Chairs", "Luxury Seating", "Leather finish, Extra padding, Premium quality", 200, "Limited", R.drawable.ic_vip_chairs));
        
        // Hospitality Items (IDs 16, 17, 18)
        equipmentList.add(new Equipment("16", "Banquet Tables", "Hospitality Items", "Tables", "6ft x 3ft, Folding, Set of 20", 120, "Available", R.drawable.ic_banquet_tables));
        equipmentList.add(new Equipment("17", "Stage Platform", "Hospitality Items", "Stage Items", "20x15ft, Modular, Height adjustable", 300, "Available", R.drawable.ic_stage_platform));
        equipmentList.add(new Equipment("18", "Event Curtains", "Hospitality Items", "Draping", "30ft height, Velvet, Multiple colors", 180, "Available", R.drawable.ic_event_curtains));
        
        // Decoration Items (IDs 19, 20, 21, 22)
        equipmentList.add(new Equipment("19", "LED Lighting System", "Decoration Items", "Lighting", "RGB, DMX control, 50 lights", 250, "Available", R.drawable.ic_led_lighting));
        equipmentList.add(new Equipment("20", "Flower Stands", "Decoration Items", "Floral Decor", "Metal, Adjustable height, Set of 10", 100, "Available", R.drawable.ic_flower_stands));
        equipmentList.add(new Equipment("21", "Backdrop Decor", "Decoration Items", "Background", "15x10ft, Customizable, Premium fabric", 200, "Limited", R.drawable.ic_backdrop_decor));
        equipmentList.add(new Equipment("22", "Floral Arches", "Decoration Items", "Arches", "8ft tall, Metal frame, Floral arrangements", 220, "Available", R.drawable.ic_floral_arches));
        
        adapter.notifyDataSetChanged();
    }
}

