package com.example.bstar128.dietmirim;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by HANSUNG on 2017-11-10.
 */

public class show_excercise extends Activity {
    TextView walk_kcal, walk_time, run_kcal, run_time, stairs_kcal, stairs_time, jumprope_kcal, jumprope_time, sqaut_kcal, sqaut_time, flank_kcal, flank_time,
            updown_kcal, updown_time, kcal;
    LinearLayout excercise;
    Button exc_but;
    String userID;

    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private ListView mListView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_excercise);

        excercise = (LinearLayout) findViewById(R.id.excercise);
        exc_but = (Button) findViewById(R.id.excercise_but);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        exc_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excercise.setVisibility(View.VISIBLE);
            }
        });
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Toast.makeText(getApplicationContext(), "지나감", Toast.LENGTH_LONG).show();
                showData(dataSnapshot);
                //Toast.makeText(getApplicationContext(),"지나감",Toast.LENGTH_LONG).show();

                /*cd=child.next();
                name_s=(String)cd.getValue();*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            UsersInfo uinfo = new UsersInfo();
            uinfo.setHeight(ds.child(userID).getValue(UsersInfo.class).getHeight());
            uinfo.setWeight(ds.child(userID).getValue(UsersInfo.class).getWeight());


            Toast.makeText(getApplicationContext(), "지나감", Toast.LENGTH_LONG).show();
        }
    }
}
