/*
 * *
 *  * Created by Youssef Assad on 5/12/18 8:41 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 5/12/18 8:34 PM
 *
 */

package youssef.revealactivity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import io.codetail.animation.SupportAnimator;

/**
 * Created by Joseph27 on 5/15/18.
 */

public class RevealActivity extends AppCompatActivity {

    private static final  int ANIMATION_DURATION  = 400;
    boolean Maskhidden = true;
    int cx , cy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reveal);

        cx = getIntent().getExtras().getInt(MainActivity.FAB_X_POSITION);
        cy = getIntent().getExtras().getInt(MainActivity.FAB_Y_POSITION);

        runanimation();
    }

    private void runanimation(){
        final FrameLayout mContainer = findViewById(R.id.container);


        mContainer.post(new Runnable() {
            @Override
            public void run() {

                final View mRevealView = mContainer;

                int radius = Math.max(mRevealView.getWidth(), mRevealView.getHeight());


                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

                    SupportAnimator animator =
                            io.codetail.animation.ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, radius);
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    SupportAnimator animator_reverse = animator.reverse();

                    if (Maskhidden) {
                        mRevealView.setVisibility(View.VISIBLE);
                        animator.start();
                        Maskhidden = false;

                    } else {

                        animator_reverse.addListener(new SupportAnimator.AnimatorListener() {
                            @Override
                            public void onAnimationStart() {

                            }

                            @Override
                            public void onAnimationEnd() {
                                mRevealView.setVisibility(View.INVISIBLE);
                                Maskhidden = true;
                                finish();
                            }

                            @Override
                            public void onAnimationCancel() {

                            }

                            @Override
                            public void onAnimationRepeat() {

                            }
                        });
                        animator_reverse.setDuration(ANIMATION_DURATION);
                        animator_reverse.start();

                    }


                } else {

                    if (Maskhidden) {
                        Animator anim = android.view.ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, radius);
                        mRevealView.setVisibility(View.VISIBLE);
                        mRevealView.requestLayout();
                        anim.setDuration(ANIMATION_DURATION);
                        anim.start();
                        Maskhidden = false;

                    } else {
                        Animator anim = android.view.ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, radius, 0);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mRevealView.setVisibility(View.INVISIBLE);
                                Maskhidden = true;
                                finish();
                            }
                        });
                        anim.setDuration(ANIMATION_DURATION);
                        anim.start();

                    }


                }

            }
        });

    }


    @Override
    public void onBackPressed() {
        runanimation();
    }
}
