package com.sagar.android_projects.circularreveal;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;

import java.util.Random;

/**
 * created by SAGAR NAYAK 13/11/2017
 * this is an activity to show a demo for circular reveal done within activity.
 * the touch point of user is taken from the view and circular reveal starts from that point.
 * you can get an idea of how circular reveal is done from this class. and use it according to your use.
 * just set the starting point as your need and the starting radius and target radius.
 */
public class CircularRevealWithinActivity extends AppCompatActivity {

    /*
    view to perform circular reveal.
     */
    View view;
    /*
    background of the activity
     */
    ConstraintLayout constraintLayout;

    /*
    this is the variable to save the generated color and this will be set to background once the
    circular reveal animation ends. this is not required for circular reveal, but done just to
    make the ui look good.
     */
    int intGeneratedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal_within);

        view = findViewById(R.id.view);
        constraintLayout = (ConstraintLayout) findViewById(R.id.background);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                performCircularReveal(motionEvent.getX(), motionEvent.getY(), view.getWidth(), view.getHeight(), view);
                return false;
            }
        });
    }

    /**
     * perform circular reveal.
     * @param x starting x pos
     * @param y starting y pos
     * @param width width of view to reveal
     * @param height height of view to reveal
     * @param view view which will be revealed
     */
    private void performCircularReveal(float x, float y, float width, float height, View view) {
        int hypotenuse = (int) Math.hypot(width, height);
        Animator anim = ViewAnimationUtils.createCircularReveal(view, (int) x, (int) y, 0, hypotenuse);
        anim.setDuration(700);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                /*
                change the background color once animation ends. this is just for ui to look good.
                not required for circular reveal.
                 */
                constraintLayout.setBackgroundColor(intGeneratedColor);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        intGeneratedColor = generateRandomColor();
        view.setBackgroundColor(intGeneratedColor);
        view.setVisibility(View.VISIBLE);
        anim.start();
    }

    /**
     * generate a random color
     * @return random color code
     */
    private int generateRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CircularRevealWithinActivity.this, MainActivity.class));
        finish();
    }
}
