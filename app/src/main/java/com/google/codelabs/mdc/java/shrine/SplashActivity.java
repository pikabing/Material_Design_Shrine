package com.google.codelabs.mdc.java.shrine;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shr_splash);
        try {
            VideoView videoHolder = findViewById(R.id.splash_video);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_video);
            videoHolder.setVideoURI(video);
            videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    jump();
                }
            });
            videoHolder.start();
        } catch (Exception ex) {
            jump();
        }
//
//        ImageView logo = findViewById(R.id.splash_logo);
//        logo.animate().rotationY(90).setDuration(700);
//        logo.animate().rotationX(45).setDuration(1000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        jump();
        return true;
    }

    private void jump() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
