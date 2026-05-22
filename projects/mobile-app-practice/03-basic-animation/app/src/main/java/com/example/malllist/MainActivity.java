package com.example.malllist;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private TextView skipView;
    private final Handler handler = new Handler(Looper.getMainLooper());

    private float curX = 0;
    private float curY = 0;
    private float nextX = 0;
    private int width = 0;
    private boolean isMoving = false;

    private final Runnable moveRunnable = new Runnable() {
        @Override
        public void run() {
            if (!isMoving) {
                return;
            }
            if (nextX > 2 * width) {
                imageView.clearAnimation();
                imageView.setVisibility(View.GONE);
                isMoving = false;
                return;
            }

            nextX += 100;
            TranslateAnimation anim = new TranslateAnimation(curX, nextX, curY, curY);
            curX = nextX;
            anim.setDuration(20);
            anim.setFillAfter(true);
            imageView.startAnimation(anim);
            handler.postDelayed(this, 20);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        skipView = findViewById(R.id.skipView);
        skipView.setTextColor(Color.BLACK);
        skipView.bringToFront();

        startTweenedAnim();
        imageView.post(this::startFrameAnim);
    }

    private void startTweenedAnim() {
        RotateAnimation animRotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animRotate.setDuration(1000);
        animRotate.setFillAfter(true);

        ScaleAnimation animScale = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animScale.setInterpolator(new BounceInterpolator());
        animScale.setDuration(1000);
        animScale.setFillAfter(true);

        AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
        animAlpha.setDuration(2000);
        animAlpha.setFillAfter(true);

        AnimationSet set = new AnimationSet(true);
        set.addAnimation(animRotate);
        set.addAnimation(animScale);
        set.addAnimation(animAlpha);
        imageView.startAnimation(set);
    }

    private void startFrameAnim() {
        AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
        anim.start();
    }

    public void skip(View v) {
        skipView.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;

        imageView.clearAnimation();
        curX = imageView.getX();
        curY = imageView.getY();
        nextX = curX;
        isMoving = true;
        handler.removeCallbacks(moveRunnable);
        handler.post(moveRunnable);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(moveRunnable);
        super.onDestroy();
    }
}
