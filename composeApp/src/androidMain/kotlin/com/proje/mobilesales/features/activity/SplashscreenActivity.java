package com.proje.mobilesales.features.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.InputDeviceCompat;
import com.proje.mobilesales.R;

public class SplashscreenActivity extends AppCompatActivity {
    private final Handler handler = new Handler();
    private AppCompatImageView logo;
    private Animation slideIn;
    private Animation slideOut;

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

     protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (!this.isTaskRoot()) {
            final Intent intent = this.getIntent();
            final String action = intent.getAction();
            if (intent.hasCategory("android.intent.category.LAUNCHER") && "android.intent.action.MAIN".equals(action)) {
                this.finish();
            }
        }
         this.setContentView(R.layout.activity_splashscreen);
         this.getWindow().getDecorView().setSystemUiVisibility(InputDeviceCompat.SOURCE_TOUCHSCREEN);
        logo = this.findViewById(R.id.logo);
        slideIn = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.slide_right_in_splash);
        slideOut = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.slide_right_out_splash);
        logo.startAnimation(slideIn);
        slideIn.setAnimationListener(new AnimationAnimationListenerC26161());
    }
    class AnimationAnimationListenerC26161 implements Animation.AnimationListener {
        public void onAnimationRepeat(final Animation animation) {
        }
        public void onAnimationStart(final Animation animation) {
        }

        AnimationAnimationListenerC26161() {
        }

        public void lambdaonAnimationEnd0() {
            logo.startAnimation(slideOut);
        }
        public void onAnimationEnd(final Animation animation) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    lambdaonAnimationEnd0();
                }
            }, 500L);
            slideOut.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(final Animation animation2) {
                }
                public void onAnimationStart(final Animation animation2) {
                }
                public void onAnimationEnd(final Animation animation2) {
                    logo.setVisibility(View.GONE);
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            });
        }
    }
}
