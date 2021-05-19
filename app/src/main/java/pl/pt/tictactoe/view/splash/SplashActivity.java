package pl.pt.tictactoe.view.splash;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pl.pt.tictactoe.R;
import pl.pt.tictactoe.navigation.Navigator;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        handler.postDelayed(() -> {
            Navigator.startMenu(SplashActivity.this);
            finish();
        },2000);
    }
}
