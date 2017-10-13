package com.sagar.android_projects.circularreveal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * created by SAGAR NAYAK 13/11/2017
 * this is an activity to demonstrate the activity transition with a circular reveal animation.
 * this activity will start a new activity with a circular reveal.
 */
public class CircularRevealActivityTransition extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
        setContentView(R.layout.activity_circular_reveal_transition);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = (Button) findViewById(R.id.transition);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CircularRevealActivityTransition.this, CircularRevealTargetActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CircularRevealActivityTransition.this, MainActivity.class));
        finish();
    }
}
