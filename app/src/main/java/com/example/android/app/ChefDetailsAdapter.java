package com.example.android.app;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.support.v7.widget.PopupMenu;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import model.Chef;
import sql.DatabaseHelper;

import static android.content.Context.MODE_PRIVATE;

public class ChefDetailsAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private List<Chef> chefsList;


    private List<Chef> chefsList2;
    Context context;
    private FrameLayout fragmentContainer;

    private SharedPreferences.Editor editor;
    //DatabaseHelper databaseHelper;
    //SQLiteDatabase db;

    public ChefDetailsAdapter(List<Chef> chefsList) {
        if(this.chefsList!=null)
            (this.chefsList).clear();
        this.chefsList=chefsList;
    }
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View iteView = inflater.inflate(R.layout.listitem, parent, false);
        UserViewHolder viewHolder = new UserViewHolder(iteView);
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return chefsList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position) {
        final Chef chefdets = chefsList.get(position);
        holder.chefImage.setBackgroundResource(chefdets.getImage());
        holder.chefSpecialty.setText(chefdets.getSpecialty());
        holder.chefDesc.setText(chefdets.getDescription());
        holder.chefName.setText(chefdets.getUsername());
        holder.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Chef userDetails =chefsList.get(position);

                //databaseHelper = new DatabaseHelper(context);
                //db = databaseHelper.getWritableDatabase();

                PopupMenu menu = new PopupMenu(context, holder.ivMenu);


                menu.inflate(R.menu.popup);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.fav:
                                chefsList2 = new ArrayList<>();
                                int image = chefdets.getImage();
                                String chefSpecialty = chefdets.getSpecialty();
                                String chefDesc = chefdets.getDescription();
                                String chefName = chefdets.getUsername();
                                Chef chefItem = new Chef();
                                chefItem.setSpecialty(chefSpecialty);
                                chefItem.setImage(image);
                                chefItem.setUsername(chefName);
                                chefItem.setDescription(chefDesc);
                                chefsList2.add(chefItem);
                                saveData(chefsList2);
                                break;
                            case R.id.request:
                                Intent intent2 = new Intent(context, Requests.class);
                                context.startActivity(intent2);



                                break;
                        }
                        return false;
                    }
                });
                menu.show();

            }
        });
    }

    public void saveData(List<Chef> chefs) {
        //commit list
        //initialize shared preferences
        SharedPreferences preferences = context.getSharedPreferences("favs", MODE_PRIVATE);
        editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(chefs);
        editor.putString("fav list", json);
        editor.apply();

    }




}
