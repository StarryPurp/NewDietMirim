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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Hansung on 2017-11-19.
 */

public class profile extends ActivityGroup{
    Button edit;
    TextView name, height, weight;
    private FirebaseDatabase mDatabase;
    DatabaseReference myRef;
    List<Object> Array = new ArrayList<Object>();
    //이름, 키, 몸무게, 이메일,수정버튼
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        mDatabase=FirebaseDatabase.getInstance();
        myRef=mDatabase.getReference();
        name=(TextView)findViewById(R.id.name_text);
        height=(TextView)findViewById(R.id.height_text);
        weight=(TextView)findViewById(R.id.weight_text);

// Read from the database
        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/

        name.getText();
        height.getText();
        weight.getText();//파이어베이스에서 불러온 값으로 세팅해주기

        edit=(Button)findViewById(R.id.edit_info);
       edit.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),sign_in.class);
               startActivity(i);
           }
        });// 수정하는 부분-> 회원가입 창을 이용
    }
}
