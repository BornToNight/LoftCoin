package com.borntonight.loftcoin.ui.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.borntonight.loftcoin.R;
import com.borntonight.loftcoin.ui.main.MainActivity;
import com.borntonight.loftcoin.ui.welcome.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler();

    // Задача перехода
    private Runnable goNext;

    private SharedPreferences prefs;

    @VisibleForTesting
    SplashIdling idling = new NoopIdling();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean(WelcomeActivity.KEY_SHOW_WELCOME, true)) {
            goNext = () -> {
                startActivity(new Intent(this, WelcomeActivity.class));
                idling.idle();
            };
        } else {
            goNext = () -> {
                startActivity(new Intent(this, MainActivity.class));
                idling.idle();
            };
        }
        // Задержка перед переходом
        handler.postDelayed(goNext, 1500);
        idling.busy();
    }

    @Override
    protected void onStop() {
        // Удаление задачи из хендлера
        handler.removeCallbacks(goNext);
        super.onStop();
    }

    private static class NoopIdling implements SplashIdling {

        @Override
        public void busy() {

        }

        @Override
        public void idle() {

        }
    }
}
