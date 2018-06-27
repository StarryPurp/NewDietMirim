package com.example.bstar128.dietmirim;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Hansung on 2017-11-19.
 */

public class profile extends Activity{
    Button edit;
    TextView name, height, weight;
    View profiler;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private  FirebaseAuth.AuthStateListener mAuthStateListener;

    private ListView mListView;

    String name_s,heis,weig,userID;
    //이름, 키, 몸무게, 이메일,수정버튼
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        profiler.setVisibility(View.VISIBLE);

        name=(TextView)profiler.findViewById(R.id.name_text);
        height=(TextView)profiler.findViewById(R.id.height_text);
        weight=(TextView)profiler.findViewById(R.id.weight_text);

        mAuth=FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance();
        myRef=mDatabase.getReference();
        FirebaseUser user=mAuth.getCurrentUser();
        userID=user.getUid();

        edit=(Button)findViewById(R.id.edit_info);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),sign_in.class);
                startActivity(i);
            }
        });// 수정하는 부분-> 회원가입 창을 이용

// Read from the database
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Toast.makeText(getApplicationContext(),"지나감",Toast.LENGTH_LONG).show();
                showData(dataSnapshot);
                //Toast.makeText(getApplicationContext(),"지나감",Toast.LENGTH_LONG).show();

                /*cd=child.next();
                name_s=(String)cd.getValue();*/
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        name.setText(name_s);



    }
    private void showData(DataSnapshot dataSnapshot){
        for(DataSnapshot ds:dataSnapshot.getChildren()){
            UsersInfo uinfo=new UsersInfo();
            uinfo.setName(ds.child(userID).getValue(UsersInfo.class).getName());
            uinfo.setHeight(ds.child(userID).getValue(UsersInfo.class).getHeight());
            uinfo.setWeight(ds.child(userID).getValue(UsersInfo.class).getWeight());

            name_s=uinfo.getName();
            heis= String.valueOf(uinfo.getHeight());
            weig=String.valueOf(uinfo.getWeight());

            name.setText(name_s);
            height.setText(heis);
            weight.setText(weig);

            Toast.makeText(getApplicationContext(),"지나감",Toast.LENGTH_LONG).show();
        }
    }
}
