package com.example.madmodelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.madmodelpaper.Database.DBHandler;

public class ProfileManagement extends AppCompatActivity {

    EditText username,dob,password;
    Button add,updateProfile;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.etUserNamePM);
        dob = findViewById(R.id.etdobPM);
        password = findViewById(R.id.edPasswordPM);
        add = findViewById(R.id.addPM);
        updateProfile = findViewById(R.id.updateProfilePM);
        male = findViewById(R.id.radioButton1);
        female = findViewById(R.id.radioButton2);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent = new Intent(ProfileManagement.this,EditProfile.class);
                //                startActivity(intent); ------these 2 methodes are same------
                Intent intent = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(intent);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(male.isChecked()){
                    gender ="Male";
                }
                else{
                    gender = "Female";
                }

               DBHandler dbHandler = new DBHandler(getApplicationContext());
               long newID = dbHandler.addInfo(username.getText().toString(),dob.getText().toString(),password.getText().toString(),gender);
               Toast.makeText(ProfileManagement.this, "User added. User ID :"+newID, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ProfileManagement.this,EditProfile.class);
                startActivity(intent);

                // not definitely needed
                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(true);
                female.setChecked(false);

            }
        });

    }
}