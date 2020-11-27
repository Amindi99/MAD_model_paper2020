package com.example.madmodelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.madmodelpaper.Database.DBHandler;

import java.util.List;

public class EditProfile extends AppCompatActivity {

    EditText username,dob,password;
    Button edit,delete,search;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username = findViewById(R.id.userNameEP);
        dob = findViewById(R.id.dobEP);
        password = findViewById(R.id.passwordEP);
        delete = findViewById(R.id.btndeleteEP);
        edit = findViewById(R.id.btneditEP);
        search = findViewById(R.id.btnsearchEp);
        male = findViewById(R.id.radioButton3);
        female = findViewById(R.id.radioButton4);

        //After searching by name it will fill the all the fields form the user data automatically
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List user = dbHandler.readAllInfo(username.getText().toString());

                // if search name not in daatabase it will print a toast msg like this
                if (user.isEmpty()) {
                    Toast.makeText(EditProfile.this, "No such user", Toast.LENGTH_SHORT).show();
                    // from that this will clear the searched name
                    username.setText(null);
                } else {

                    Toast.makeText(EditProfile.this, "User found! User : "+user.get(0).toString(), Toast.LENGTH_SHORT).show();
                    //if searched name is available it will display the above details
                    username.setText(user.get(0).toString());
                    dob.setText(user.get(1).toString());
                    password.setText(user.get(2).toString());

                    if (user.get(3).toString().equals("Male")) {
                        male.setChecked(true);
                    } else {
                        female.setChecked(true);
                    }


                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(male.isChecked()){
                    gender ="Male";
                }
                else{
                    gender = "Female";
                }
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                Boolean status = dbHandler.updateInfor(username.getText().toString(),dob.getText().toString(),password.getText().toString(),gender);
                if (status){
                    Toast.makeText(EditProfile.this, "User updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditProfile.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.deleteInfo(username.getText().toString());

                Toast.makeText(EditProfile.this, "User deleted", Toast.LENGTH_SHORT).show();

                // After deleting the data it will delete the data from the filled fields.
                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(false);
                female.setChecked(false);

            }
        });

    }
}