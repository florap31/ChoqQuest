package com.example.android.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import sql.DatabaseHelper;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    EditText TextUsername;
    EditText TextPassword;
    EditText TextEmail;
    RadioButton radioButton;
    RadioGroup radioGroup;
    Button login;
    ImageButton next;
    TextView status;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        db = new DatabaseHelper(this);
        //Buttons
        login = (Button)findViewById(R.id.loginButton);
        login.setOnClickListener(this);
        next = (ImageButton)findViewById(R.id.nextButton);
        next.setOnClickListener(this);
        //text
        TextUsername =(EditText)findViewById(R.id.userName);
        TextPassword =(EditText)findViewById(R.id.pass);
        TextEmail =(EditText)findViewById(R.id.email);
        status = (TextView)findViewById(R.id.status);
        //radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.loginButton:
                Intent intent = new Intent(this, Login.class);
                this.startActivity(intent);
                break;

            case R.id.nextButton:
               // int selectedId = radioGroup.getCheckedRadioButtonId();
                //radioButton = (RadioButton)findViewById(selectedId);
                //String type = radioButton.getText().toString().trim();
                String user = TextUsername.getText().toString().trim();
                String pass = TextPassword.getText().toString().trim();
                String email = TextEmail.getText().toString().trim();
                    long val = db.addUser(user, pass, email);
                    if (val > 0) {
                        Intent intent2 = new Intent(this, Main.class);
                        this.startActivity(intent2);
                    }else if (user.isEmpty() && pass.isEmpty() &&email.isEmpty()) {
                        status.setText("Registration failed");
                    } else
                        status.setText("Registration failed");
                break;

            default:
                break;
        }

    }
}
