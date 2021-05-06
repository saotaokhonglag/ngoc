package com.example.mainform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class lookScreen extends AppCompatActivity {

    private static int SPLASH_SREEN = 5000;

    // Tạo biến
    Animation topAmin, bottomAni;
    ImageView image;
    TextView logo, slogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_look_screen);

        topAmin = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAni = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);


            // Hiệu ứng Ani
            image = findViewById(R.id.imageView);
            logo = findViewById(R.id.tvhrm);
            slogan = findViewById(R.id.textView2);

            image.setAnimation(topAmin);
            logo.setAnimation(bottomAni);
            slogan.setAnimation(bottomAni);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(lookScreen.this, Login.class);

                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View,String>(image,"logo_image");
                    pairs[1] = new Pair<View,String>(logo,"logo_text");

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(lookScreen.this,pairs);
                    startActivity(intent,options.toBundle());
                }
            },SPLASH_SREEN);

    }
}