package com.example.android.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RequestViewHolder extends RecyclerView.ViewHolder {
    TextView request;
    TextView dateText;
    public RequestViewHolder(View itemView) {
        super(itemView);
        request = (TextView)itemView.findViewById(R.id.requestdes);
        dateText = (TextView)itemView.findViewById(R.id.date);


    }
}
