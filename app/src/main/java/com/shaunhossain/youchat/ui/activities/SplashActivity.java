package com.shaunhossain.youchat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.google.firebase.auth.FirebaseAuth;
import com.shaunhossain.youchat.R;

/**
 * Created by Shaunhossain on 24/08/2017.
 */

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_MS = 2000;
    private Handler mHandler;
    private Runnable mRunnable;
    Thread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        timer = new Thread(){
            public void run(){
                try{
                    synchronized (this) {
                        wait(10000);
                    }
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
    @Override

    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            synchronized(timer){

                timer.notify();

            }

        }

        return true;
    }
}