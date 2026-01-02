package com.simats.eventdecorationitems;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;

public class ViewEquipmentActivity extends AppCompatActivity {

    private RecyclerView equipmentRecyclerView;
    private AdminEquipmentAdapter adapter;
    private ArrayList<Equipment> equipmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_equipment);

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
        equipmentList.add(new Equipment("SPK001", "Large Event Speakers", "Speakers", "Professional Audio", "2000W, Bluetooth enabled, Weather resistant", 11250, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("SPK002", "Bluetooth Speakers", "Speakers", "Portable", "500W, Rechargeable battery, 10hr playback", 3750, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("SPK003", "Stage Speakers", "Speakers", "Concert Grade", "3000W, Line array system, Professional grade", 18750, "Limited", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("CRP001", "Red Carpet", "Carpets", "Event Carpet", "50ft x 4ft, Velvet finish, Premium quality", 7500, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("CRP002", "Event Carpet", "Carpets", "Floor Covering", "100 sq ft, Durable material, Easy to clean", 5625, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("CRP003", "Mandap Carpet", "Carpets", "Wedding Decor", "200 sq ft, Traditional design, Luxury finish", 9000, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("TNT001", "Wedding Tent", "Tents", "Large Event", "40x60ft, Waterproof, Seats 200 people", 37500, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("TNT002", "Canopy Tent", "Tents", "Medium Event", "20x20ft, UV protection, Pop-up design", 15000, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("TNT003", "Small Shade Tent", "Tents", "Small Event", "10x10ft, Portable, Easy setup", 6000, "Limited", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("VSL001", "Large Cooking Vessels", "Cooking Vessels", "Commercial Grade", "50L capacity, Stainless steel, Heavy", 4500, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("VSL002", "Gas Stoves", "Cooking Vessels", "Cooking Equipment", "4 burner, LPG compatible, Commercial use", 3000, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("VSL003", "Serving Pots", "Cooking Vessels", "Serving Ware", "Set of 10, Insulated, Buffet style", 3750, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("VSL004", "Boilers", "Cooking Vessels", "Water Heating", "100L capacity, Electric/Gas, Food grade", 5250, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("CHR001", "Plastic Chairs", "Chairs", "Standard Seating", "", 7500, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("CHR002", "Wedding Chairs", "Chairs", "Premium Seating", "Chiavari style, Gold finish, Cushioned", 11250, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("CHR003", "VIP Cusioned Chairs", "Chairs", "Luxury Seating", "Leather finish, Extra padding, Premium quality", 15000, "Limited", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("HSP001", "Banquet Tables", "Hospitality Items", "Tables", "6ft x 3ft, Folding, Set of 20", 9000, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("HSP002", "Stage Platform", "Hospitality Items", "Stage Items", "20x15ft, Modular, Height adjustable", 22500, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("HSP003", "Event Curtains", "Hospitality Items", "Draping", "30ft height, Velvet, Multiple colors", 13500, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("DEC001", "LED Lighting System", "Decoration Items", "Lighting", "RGB, DMX control, 50 lights", 18750, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("DEC002", "Flower Stands", "Decoration Items", "Floral Decor", "Metal, Adjustable height, Set of 10", 7500, "Available", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("DEC003", "Backdrop Decor", "Decoration Items", "Background", "15x10ft, Customizable, Premium", 15000, "Limited", R.drawable.ic_launcher_foreground));
        equipmentList.add(new Equipment("DEC004", "Floral Arches", "Decoration Items", "Arches", "8ft tall, Metal frame, Floral arrangements", 16500, "Available", R.drawable.ic_launcher_foreground));
        adapter.notifyDataSetChanged();
    }
}