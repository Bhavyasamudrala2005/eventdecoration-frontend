package com.simats.eventdecorationitems;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SpeakerAdapter extends RecyclerView.Adapter<SpeakerAdapter.SpeakerViewHolder> {

    private List<Speaker> speakerList;

    public SpeakerAdapter(List<Speaker> speakerList) {
        this.speakerList = speakerList;
    }

    @NonNull
    @Override
    public SpeakerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speaker, parent, false);
        return new SpeakerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeakerViewHolder holder, int position) {
        Speaker speaker = speakerList.get(position);
        holder.speakerName.setText(speaker.getName());
        holder.speakerType.setText(speaker.getType());
        holder.ratingText.setText(String.format("%.1f • %d reviews", speaker.getRating(), speaker.getReviews()));
        holder.availabilityStatus.setText(speaker.getAvailability());
        holder.price.setText(speaker.getPrice());

        if ("Large Event Speakers".equals(speaker.getName())) {
            holder.speakerImage.setImageResource(R.drawable.ic_large_event_speakers);
        } else if ("Bluetooth Speakers".equals(speaker.getName())) {
            holder.speakerImage.setImageResource(R.drawable.ic_bluetooth_speaker);
        } else if ("Stage Speakers".equals(speaker.getName())) {
            holder.speakerImage.setImageResource(R.drawable.ic_stage_speakers);
        }

    }

    @Override
    public int getItemCount() {
        return speakerList.size();
    }

    public static class SpeakerViewHolder extends RecyclerView.ViewHolder {
        ImageView speakerImage;
        TextView speakerName;
        TextView speakerType;
        TextView ratingText;
        TextView availabilityStatus;
        TextView price;

        public SpeakerViewHolder(@NonNull View itemView) {
            super(itemView);
            speakerImage = itemView.findViewById(R.id.speaker_image);
            speakerName = itemView.findViewById(R.id.speaker_name);
            speakerType = itemView.findViewById(R.id.speaker_type);
            ratingText = itemView.findViewById(R.id.rating_text);
            availabilityStatus = itemView.findViewById(R.id.availability_status);
            price = itemView.findViewById(R.id.price);
        }
    }
}
