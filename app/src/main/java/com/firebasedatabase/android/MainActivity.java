package com.firebasedatabase.android;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mProfileRV;
    private Button mCreateProfileBtn;
    private ProfileAdapter mProfileAdapter;
    private ArrayList<Profile> mProfileList;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Profile");

        mProfileList = new ArrayList<>();
        mProfileRV = (RecyclerView) findViewById(R.id.profileRV);
        mCreateProfileBtn = (Button) findViewById(R.id.createProfileBtn);

        initViews();

    }

    private void initViews() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mProfileRV.setLayoutManager(linearLayoutManager);
        mProfileAdapter = new ProfileAdapter(this, mProfileList);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Profile profile = dataSnapshot1.getValue(Profile.class);
                    mProfileList.add(profile);

                }
                mProfileRV.setAdapter(mProfileAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mCreateProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateProfileActivity.class);
                startActivity(intent);
            }
        });

    }
}
