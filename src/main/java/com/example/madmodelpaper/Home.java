package com.example.madmodelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {

    EditText username,password;
    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username = findViewById(R.id.etUserNameH);
        password = findViewById(R.id.etPasswordH);
        login = findViewById(R.id.btnLoginH);
        register = findViewById(R.id.btnRegisterH);

        //direct next page to after click on the register button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this,ProfileManagement.class);
                //Intent intent = new Intent(getApplicationContext(),ProfileManagement.class);  //same as above
                startActivity(intent);
            }
        });
    }
}