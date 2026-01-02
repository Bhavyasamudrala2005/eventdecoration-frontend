package com.simats.eventdecorationitems;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SpeakersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakers);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        RecyclerView speakersRecyclerView = findViewById(R.id.speakers_recycler_view);
        speakersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Speaker> speakerList = new ArrayList<>();
        speakerList.add(new Speaker("Large Event Speakers", "Professional Audio", 4.5, 28, "Available", "₹700/day", R.drawable.ic_speaker));
        speakerList.add(new Speaker("Bluetooth Speakers", "Portable", 4.2, 45, "Available", "₹500/day", R.drawable.ic_speaker));
        speakerList.add(new Speaker("Stage Speakers", "Concert Grade", 4.8, 15, "Limited", "₹1000/day", R.drawable.ic_speaker));

        SpeakerAdapter adapter = new SpeakerAdapter(speakerList);
        speakersRecyclerView.setAdapter(adapter);
    }
}
