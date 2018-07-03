package com.example.nick.earthquakeapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class EarthquakeViewHolder extends RecyclerView.ViewHolder {
    TextView dateTextView;
    TextView placeTextView;
    TextView magnitudeTextView;


    public EarthquakeViewHolder(View itemView) {
        super(itemView);
        dateTextView = (TextView)itemView.findViewById(R.id.dateTextView);
        placeTextView = (TextView)itemView.findViewById(R.id.placeTextView);
        magnitudeTextView = (TextView)itemView.findViewById(R.id.magnitudeTextView);
    }
}
