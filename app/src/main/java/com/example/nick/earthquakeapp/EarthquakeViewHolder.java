package com.example.nick.earthquakeapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class EarthquakeViewHolder extends RecyclerView.ViewHolder {
    TextView dateTextView;
    TextView directionTextView;
    TextView magnitudeTextView;
    TextView exactPlaceTextView;


    public EarthquakeViewHolder(View itemView) {
        super(itemView);
        exactPlaceTextView = (TextView)itemView.findViewById(R.id.exactPlaceTextId);
        dateTextView = (TextView)itemView.findViewById(R.id.dateTextView);
        directionTextView = (TextView)itemView.findViewById(R.id.directionTextId);
        magnitudeTextView = (TextView)itemView.findViewById(R.id.magnitudeTextView);
    }
}
