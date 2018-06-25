package com.example.bstar128.dietmirim;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by HANSUNG on 2017-10-19.
 */

public class excercise_calender extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //날마다의 섭취한 칼로리 / 기초대사량 보여주기
        super.onCreate(savedInstanceState);
        setContentView(R.layout.excercise_calender);

        TextView t=(TextView)findViewById(R.id.texts);
    }
}
