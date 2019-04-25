package com.example.android.app;

import android.content.Context;
import android.content.SharedPreferences;
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
import java.util.List;

import model.Chef;

public class Favs extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ChefDetailsAdapter favsAdapter;
    private List<Chef> arrayList;
    View parentHolder;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        parentHolder = inflater.inflate(R.layout.favs, container, false);
        recyclerView = (RecyclerView) parentHolder.findViewById(R.id.favsView);
        loadData();
        layoutManager = new LinearLayoutManager(parentHolder.getContext());
        favsAdapter = new ChefDetailsAdapter(arrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(favsAdapter);
        return parentHolder;
    }

    public void loadData() {
        //retrieve list
        SharedPreferences preferences = getContext().getSharedPreferences("favs", Context.MODE_PRIVATE);
        Gson gson =  new Gson();
        String json1 = preferences.getString("fav list", null);
        Type type = new TypeToken<List<Chef>>() {}.getType();
        arrayList = gson.fromJson(json1, type);
        if(arrayList == null) {
            arrayList = new ArrayList<>();
        }
    }
}
