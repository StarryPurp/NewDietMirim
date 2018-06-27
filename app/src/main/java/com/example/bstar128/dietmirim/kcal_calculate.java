package com.example.bstar128.dietmirim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HANSUNG on 2017-10-19.
 */
public class kcal_calculate extends Activity{
    Button test;
    EditText kcal_text;
    Intent i;
    int kcal;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //칼로리 계산
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kcal_calculate);


        test=(Button)findViewById(R.id.test);
        kcal_text=(EditText)findViewById(R.id.kcal);


       test.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              i=new Intent(getApplicationContext(),show_excercise.class);
               startActivity(i);
           }
       });

       /* kcal=Integer.parseInt(kcal_text.getText().toString());

        Toast tm=Toast.makeText(kcal_calculate.this,"기초 대사량: "+kcal,Toast.LENGTH_SHORT);
       Display dis=((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
       int xoffset=(int)(Math.random()*dis.getWidth());
       int yoffset=(int)(Math.random()*dis.getHeight());
       tm.setGravity(Gravity.TOP|Gravity.LEFT,xoffset,yoffset);
       tm.show(); //잘 뜨는지 테스트!*/

        //남자면 (질량)×24, 여자면  0.9×(질량)×24<- 기초 대사량
        //BMI-> 몸무게/ 키(m) 제곱

    }
}
//        Toast tm=Toast.makeText(kcal_calculate.this,"칼로리 계산중",Toast.LENGTH_SHORT);
//        Display dis=((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
//        int xoffset=(int)(Math.random()*dis.getWidth());
//        int yoffset=(int)(Math.random()*dis.getHeight());
//
//       tm.setGravity(Gravity.TOP|Gravity.LEFT,xoffset,yoffset);
//       tm.show(); //잘 뜨는지 테스트!