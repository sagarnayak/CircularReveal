package com.sagar.android_projects.circularreveal;

import android.animation.Animator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;

/**
 * created by SAGAR NAYAK 13/11/2017
 * this activity will start itself with a circular reveal. there are few preparations we have to make
 * to make this happen.
 * 1. set a transparent background to this activity at the manifest.
 * 2. set a background color to activity container.
 * 3. start the reveal animation at onCreate()
 */
public class CircularRevealTargetActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
        setContentView(R.layout.activity_circular_reveal_target);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        constraintLayout = (ConstraintLayout) findViewById(R.id.target_container);

        if (savedInstanceState == null) {
            constraintLayout.setVisibility(View.INVISIBLE);

            ViewTreeObserver viewTreeObserver = constraintLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity();
                        constraintLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        }
    }

    /**
     * method to start the circular reveal animation.
     */
    private void circularRevealActivity() {

        int cx = constraintLayout.getWidth() / 2;
        int cy = constraintLayout.getHeight() / 2;

        float finalRadius = Math.max(constraintLayout.getWidth(), constraintLayout.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(constraintLayout, cx, cy, 0, finalRadius);
        circularReveal.setDuration(1000);

        // make the view visible and start the animation
        constraintLayout.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

    /**
     * method to start a circular reveal animation which is from larger radius to 0 radius.
     */
    private void reverseCircularRevealActivity() {

        int cx = constraintLayout.getWidth() / 2;
        int cy = constraintLayout.getHeight() / 2;

        float startRadius = Math.max(constraintLayout.getWidth(), constraintLayout.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(constraintLayout, cx, cy, startRadius, 0);
        circularReveal.setDuration(1000);

        circularReveal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                /*
                finish the activity when the animation ends.
                 */
                constraintLayout.setVisibility(View.INVISIBLE);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        // make the view visible and start the animation
        constraintLayout.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

    @Override
    public void onBackPressed() {
        reverseCircularRevealActivity();
    }
}
