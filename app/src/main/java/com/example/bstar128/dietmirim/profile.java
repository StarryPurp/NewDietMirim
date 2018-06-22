package com.example.bstar128.dietmirim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by Hansung on 2017-11-19.
 */

public class profile extends Activity{
    Button edit;
    //이름, 키, 몸무게, 이메일,수정버튼
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

       edit.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),sign_in.class);
               startActivity(i);
           }
        });// 수정하는 부분-> 회원가입 창을 이용
    }
}
