package com.example.android.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import model.Chef;

public class Home extends Fragment {
    private TextView name;
    private TextView specialty;
    private TextView description;
    private ScrollView scrollView;
    private CardView cardView;
    private CircleImageView imageView;
    private Chef chef;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter chefAdapter;
    private RecyclerView.LayoutManager layoutManager;

    List<Chef> chefDetails;
    View parentHolder;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        parentHolder = inflater.inflate(R.layout.home, container, false);
        name = (TextView)parentHolder.findViewById(R.id.chefName);
        specialty = (TextView)parentHolder.findViewById(R.id.specialty);
        description = (TextView)parentHolder.findViewById(R.id.description);
        imageView = (CircleImageView)parentHolder.findViewById(R.id.chefImage);
        recyclerView = (RecyclerView)parentHolder.findViewById(R.id.recycler);
        createChefList();
        layoutManager = new LinearLayoutManager(getContext());
        chefAdapter = new ChefDetailsAdapter(chefDetails);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chefAdapter);
        return parentHolder;
    }

    private void createChefList() {
        chefDetails = new ArrayList<Chef>();
        Chef chefItem = new Chef();
        chefItem.setSpecialty("Gourmet food");
        chefItem.setDescription("Several years making gourmet food in restaurants.");
        chefItem.setUsername("Gordon");
        chefItem.setImage(R.drawable.gordon);

        chefDetails.add(chefItem);
        Chef chefItem1 = new Chef();
        chefItem1.setSpecialty("Dessert");
        chefItem1.setDescription("Experience baking pastries and other desserts.");
        chefItem1.setUsername("Ann");
        chefItem1.setImage(R.drawable.gordon);
       chefDetails.add(chefItem1);
        Chef chefItem2 = new Chef();
        chefItem2.setSpecialty("Indian food");
        chefItem2.setDescription("Knowledge of authentic Indian food.");
        chefItem2.setUsername("Dr. Hoyt");
        chefItem2.setImage(R.drawable.gordon);
        chefDetails.add(chefItem2);
        Chef chefItem3 = new Chef();
        chefItem3.setSpecialty("French food");
        chefItem3.setDescription("Lived in France for 20 years working with pastries");
        chefItem3.setUsername("Pierre");
        chefItem3.setImage(R.drawable.gordon);
        chefDetails.add(chefItem3);
    }
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.chefCard:

                Intent intent2 = new Intent(getActivity(), Requests.class);
                this.getActivity().startActivity(intent2);
                break;

            case R.id.floatingActionButton:
                Intent intent3 = new Intent(this.getActivity(), Requests.class);
                this.getActivity().startActivity(intent3);

                break;

            default:
                break;
        }

    }
}
