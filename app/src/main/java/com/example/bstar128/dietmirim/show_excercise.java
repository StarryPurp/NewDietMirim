package com.example.bstar128.dietmirim;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by HANSUNG on 2017-11-10.
 */

public class show_excercise extends Activity {
    TextView walk_kcal,walk_time,run_kcal,run_time,stairs_kcal,stairs_time,jumprope_kcal,jumprope_time,sqaut_kcal,sqaut_time,flank_kcal,flank_time,
            updown_kcal,updown_time,kcal;
    LinearLayout excercise;
    Button exc_but;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_excercise);

        excercise=(LinearLayout)findViewById(R.id.excercise);
        exc_but=(Button)findViewById(R.id.excercise_but);

        exc_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excercise.setVisibility(View.VISIBLE);
            }
        });
    }
}
