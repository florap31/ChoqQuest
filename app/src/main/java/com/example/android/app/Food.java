package com.example.android.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Food extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private UserRequestsAdapter reqAdapter;
    private List<UserRequest> arrayList;
    View parentHolder;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        parentHolder = inflater.inflate(R.layout.food, container, false);
        recyclerView = (RecyclerView) parentHolder.findViewById(R.id.requestsView);
        loadData();
        layoutManager = new LinearLayoutManager(parentHolder.getContext());
        reqAdapter = new UserRequestsAdapter(arrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(reqAdapter);
        return parentHolder;
    }

    public void loadData() {
        //retrieve list
        SharedPreferences preferences = getContext().getSharedPreferences("reqs", Context.MODE_PRIVATE);
        Gson gson =  new Gson();
        String json1 = preferences.getString("request list", null);
        Type type = new TypeToken<List<UserRequest>>() {}.getType();
        arrayList = gson.fromJson(json1, type);
        if(arrayList == null) {
            arrayList = new ArrayList<>();
        }
    }
}
