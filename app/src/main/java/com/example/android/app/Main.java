package com.example.android.app;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import model.Chef;
import sql.DatabaseHelper;

public class Main extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
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
    private FrameLayout fragmentContainer;
    private CardView cardV;
    SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

   SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = getSharedPreferences("favs",MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);





        

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Home()).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new Home();
                            break;
                        case R.id.nav_food:
                            selectedFragment = new Food();
                            break;
                        case R.id.nav_favs:
                            selectedFragment = new Favs();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };



}
