package com.example.bstar128.dietmirim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

/**
 * Created by HANSUNG on 2017-10-19.
 */

public class main_page extends Activity {
    TabHost t1;
    ImageView kcal;
    EditText identity,password;
    Button sign,log;
    private DatabaseReference databaseReference;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        kcal=(ImageView) findViewById(R.id.kcalanddbmi);

        t1=(TabHost)findViewById(R.id.tabhost);
        t1.setup();

        TabHost.TabSpec ts1=t1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.BMI);
        ts1.setIndicator("BMI");
        t1.addTab(ts1);

        TabHost.TabSpec ts2=t1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.CALENDER);
        ts2.setIndicator("CALENDER");
        t1.addTab(ts2);

        TabHost.TabSpec ts3=t1.newTabSpec("Tab Spec 3");
        ts3.setContent(R.id.SIGN);
        ts3.setIndicator("PROFILE");
        t1.addTab(ts3);

        Button b = findViewById(R.id.sign);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), sign_in.class);
                startActivity(i);
            }
        });

        kcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),kcal_calculate.class);
                startActivity(i);
            }
        });

        identity=(EditText)findViewById(R.id.identity);
        password=(EditText)findViewById(R.id.password);
        sign=(Button)findViewById(R.id.sign);
        log=(Button)findViewById(R.id.log);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), sign_in.class);
                startActivity(i);
            }
        });
        sign.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent i=new Intent(getApplicationContext(),sign_in.class);
                startActivity(i);
                return false;
            }
        });
        databaseReference  = FirebaseDatabase.getInstance().getReference("users");

        log =(Button)findViewById(R.id.log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
                        while(child.hasNext()) {
                            if (child.next().getKey().equals(identity.getText().toString())) {
                                if(child.next().getValue().equals(password.getText().toString())) {
                                    Toast.makeText(getApplicationContext(), "로그인!", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        }
                        Toast.makeText(getApplicationContext(),"존재하지 않는 아이디이거나 패스워드가 일치하지않습니다.",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        sign = (Button)findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),sign_in.class);
                startActivity(intent);
            }
        });
    }
}
