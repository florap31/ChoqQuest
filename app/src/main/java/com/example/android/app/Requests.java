package com.example.android.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class Requests extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText request;
    private TextView textView;
    private TextView status;
    private Button submit;
    private String user;
    public List<UserRequest> userRequests;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter reqAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requests);
        request = (EditText)findViewById(R.id.requestText);
        submit = (Button)findViewById(R.id.submit);
        status = (TextView)findViewById(R.id.statusreq);
        loadData();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!request.getText().toString().isEmpty()) {
                   UserRequest userRequest = new UserRequest();
                    userRequest.setRequest(request.getText().toString());
                    userRequest.setDate(Calendar.getInstance().getTime().toString());
                    userRequests.add(userRequest);
                    saveData(userRequests);
                    status.setText("Your request has been sent");
                }
            }
        });




    }
    public void saveData( List<UserRequest> userRequests) {
        //commit list
        //initialize shared preferences
        preferences = getSharedPreferences("reqs", MODE_PRIVATE);
        editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userRequests);
        editor.putString("request list", json);
        editor.apply();

    }
    public void loadData() {
        //retrieve list
        SharedPreferences preferences = this.getSharedPreferences("reqs", Context.MODE_PRIVATE);
        Gson gson =  new Gson();
        String json1 = preferences.getString("request list", null);
        Type type = new TypeToken<List<UserRequest>>() {}.getType();
        userRequests = gson.fromJson(json1, type);
        if(userRequests == null) {
            userRequests= new ArrayList<>();
        }


    }

    public List<UserRequest> getUserRequests() {
        return userRequests;
    }


    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
}
