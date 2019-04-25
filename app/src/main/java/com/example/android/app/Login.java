package com.example.android.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import sql.DatabaseHelper;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText TextUsername;
    EditText TextPassword;
    TextView status;
    Button signup;
    ImageButton next;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db = new DatabaseHelper(this);
        //Buttons
        signup = (Button)findViewById(R.id.signupButton);
        signup.setOnClickListener(this);
        next = (ImageButton)findViewById(R.id.nextButton);
        next.setOnClickListener(this);
        //text
        TextUsername =(EditText)findViewById(R.id.userName);
        TextPassword =(EditText)findViewById(R.id.pass);
        status = (TextView)findViewById(R.id.status);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.signupButton:
                Intent intent = new Intent(this, Signup.class);
                this.startActivity(intent);
                break;

            case R.id.nextButton:
                String user = TextUsername.getText().toString().trim();
                String pwd = TextPassword.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if(res) {
                    Requests requests = new Requests();
                    requests.setUser(user);
                    Intent intent2 = new Intent(this, Main.class);
                    this.startActivity(intent2);
                }
                else
                    status.setText("Login failed");
                break;

            default:
                break;
        }

    }
}
