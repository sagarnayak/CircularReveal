package com.sagar.android_projects.circularreveal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * created by SAGAR NAYAK 13/11/2017
 * this is an android app to display the use of circular reveal in activity and during the activity
 * transition.
 */
public class MainActivity extends AppCompatActivity {

    /*
    button to start the circular reveal inside an activity.
     */
    Button buttonCircularRevealWithinActivity;
    /*
    button to start circular reveal between two activity
     */
    Button buttonCircularRevealActivityTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonCircularRevealWithinActivity = (Button) findViewById(R.id.within_activity);
        buttonCircularRevealActivityTransition = (Button) findViewById(R.id.other_activity);

        buttonCircularRevealWithinActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CircularRevealWithinActivity.class));
                finish();
            }
        });

        buttonCircularRevealActivityTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CircularRevealActivityTransition.class));
                finish();
            }
        });
    }
}
