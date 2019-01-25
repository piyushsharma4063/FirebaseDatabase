package com.firebasedatabase.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProfileActivity extends AppCompatActivity {

    private EditText idET, nameET, emailET, numberET, ageET, addressET;
    private Button mProfileBtn;
    private DatabaseReference mDatabaseReference;
    private String id, name, email, number, age, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Profile");

        idET = (EditText) findViewById(R.id.id_et);
        nameET = (EditText) findViewById(R.id.name_et);
        emailET = (EditText) findViewById(R.id.email_et);
        numberET = (EditText) findViewById(R.id.number_et);
        ageET = (EditText) findViewById(R.id.age_et);
        addressET = (EditText) findViewById(R.id.address_et);

        mProfileBtn = (Button) findViewById(R.id.createProfileBtn);

        initViews();
    }

    private void initViews() {

        mProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = idET.getText().toString();
                name = nameET.getText().toString();
                email = emailET.getText().toString();
                number = numberET.getText().toString();
                age = ageET.getText().toString();
                address = addressET.getText().toString();

                if(TextUtils.isEmpty(id)){
                    Toast.makeText(CreateProfileActivity.this, "Enter Id", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(CreateProfileActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(CreateProfileActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(number)){
                    Toast.makeText(CreateProfileActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(age)){
                    Toast.makeText(CreateProfileActivity.this, "Enter Age", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(address)){
                    Toast.makeText(CreateProfileActivity.this, "Enter Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                Profile profile = new Profile();
                profile.setId(id);
                profile.setName(name);
                profile.setEmail(email);
                profile.setNumber(number);
                profile.setAge(age);
                profile.setAddress(address);
                mDatabaseReference.child(id).setValue(profile);

                Intent intent = new Intent(CreateProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(CreateProfileActivity.this, "Success - Uploaded to Firebase Database", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
