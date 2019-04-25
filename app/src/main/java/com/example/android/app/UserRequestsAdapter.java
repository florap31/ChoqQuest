package com.example.android.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class UserRequestsAdapter extends RecyclerView.Adapter<RequestViewHolder> {
    List<UserRequest> requestList;
    Context context;
    public UserRequestsAdapter(List<UserRequest> requestList) {
        this.requestList = requestList;
    }
    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemview = inflater.inflate(R.layout.requestsitem, parent, false);
        RequestViewHolder viewHolder = new RequestViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        UserRequest requestDetails = requestList.get(position);
        holder.request.setText(requestDetails.getRequest());
        holder.dateText.setText(requestDetails.getDate());
    }


    @Override
    public int getItemCount() {
        return requestList.size();
    }
}
