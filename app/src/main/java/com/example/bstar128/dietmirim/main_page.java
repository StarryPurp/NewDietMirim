package com.example.bstar128.dietmirim;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
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

public class main_page extends ActivityGroup {
    //String TAG = ">>디버깅";
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

        final TabHost.TabSpec ts3=t1.newTabSpec("Tab Spec 3");
        ts3.setContent(R.id.SIGN);
        ts3.setIndicator("LOGIN");
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
        databaseReference  = FirebaseDatabase.getInstance().getReference("users");

        log =(Button)findViewById(R.id.log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                       // Log.e(TAG, "로그인 시도 : " + identity.getText().toString() + "/" + password.getText().toString());
                        DataSnapshot c ;
                        DataSnapshot cd;
                        Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
                        while(child.hasNext()) {
                            c =child.next();
                            if (c.getKey().equals(identity.getText().toString())) {
                                Iterator<DataSnapshot> c_datas = c.getChildren().iterator();
                                while (c_datas.hasNext()){
                                    cd = c_datas.next();
                                    if(cd.getKey().equals("passwd")){
                                        String pw = (String) cd.getValue();
                                        if(pw.equals((password.getText().toString()))){
                                            Toast.makeText(getApplicationContext(), "로그인!", Toast.LENGTH_LONG).show();
                                            // 로그인 처리
                                            //setContentView(R.layout.profile);
                                            Intent i=new Intent(main_page.this,profile.class);
                                            Window w=getLocalActivityManager().startActivity("PROFILE",i);
                                            setContentView(w.getDecorView());
                                            return;
                                        }
                                    }
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
