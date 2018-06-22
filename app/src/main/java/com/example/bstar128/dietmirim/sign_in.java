package com.example.bstar128.dietmirim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Iterator;


/**
 * Created by HANSUNG on 2017-10-24.
 */

public class sign_in extends Activity{
    //회원가입-> 성별, 이름, 아이디, 비밀번호, 키, 몸무게
    private DatabaseReference databaseReference;
    private EditText editId, name, passwd, tall, weight;
    private SeekBar seekbar;
    private RadioGroup gender;
    private ValueEventListener checkRegister = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
            while (child.hasNext()) {
                if (editId.getText().toString().equals(child.next().getKey())) {
                    Toast.makeText(getApplicationContext(), "존재하는 아이디 입니다.", Toast.LENGTH_LONG).show();
                    databaseReference.removeEventListener(this);
                    return;
                }
            }
            makeNewId();
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        databaseReference  = FirebaseDatabase.getInstance().getReference("users");

        editId = (EditText)findViewById(R.id.identity_s);
        name = (EditText)findViewById(R.id.name);
        passwd=(EditText)findViewById(R.id.ps);
        tall = (EditText)findViewById(R.id.tall);
        weight = (EditText)findViewById(R.id.heavy);
        seekbar = (SeekBar)findViewById(R.id.seekBar2);
        gender = (RadioGroup)findViewById(R.id.gender);

        Button check = (Button)findViewById(R.id.enter);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(checkRegister);
                Intent intent = new Intent(getApplicationContext(),main_page.class);
                startActivity(intent);
            }
        });
    }

    /*public class myDBHelper extends SQLiteOpenHelper{
        public myDBHelper(Context c){
            super(c,"DietDE",null,1);

        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE DietMirim (Mname CHAR(20) PRIMARY KEY, height INTEGER, weight INTEGER)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DietMirim");
            onCreate(sqLiteDatabase);
        }
    }*/

    void makeNewId()
    {
        Date date = new Date(System.currentTimeMillis());
        databaseReference.child(editId.getText().toString()).child("가입일").setValue(date.toString());
        databaseReference.child(editId.getText().toString()).child("name").setValue(name.getText().toString());
        databaseReference.child(editId.getText().toString()).child("passwd").setValue(passwd.getText().toString());
        databaseReference.child(editId.getText().toString()).child("height").setValue(tall.getText().toString());
        databaseReference.child(editId.getText().toString()).child("weight").setValue(weight.getText().toString());
        databaseReference.child(editId.getText().toString()).child("exercise_p").setValue(seekbar.getProgress());
        RadioButton rb = (RadioButton) findViewById(gender.getCheckedRadioButtonId());
        databaseReference.child(editId.getText().toString()).child("gender").setValue(rb.getText().toString());


        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
    }
}







