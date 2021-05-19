package pl.pt.tictactoe.view.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pl.pt.tictactoe.R;
import pl.pt.tictactoe.navigation.Navigator;
import pl.pt.tictactoe.utils.GameUtil;

public class MenuActivity extends AppCompatActivity {

    Button singlePlayButton;
    Button multiplePlayButton;
    Button scoreButton;
    Button exitButton;

    public static Intent getStartingIntent(Context context) {
        return new Intent(context, MenuActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        singlePlayButton = findViewById(R.id.activity_menu_single_play_button);
        multiplePlayButton = findViewById(R.id.activity_menu_multiple_play_button);
        scoreButton = findViewById(R.id.activity_menu_score_button);
        exitButton = findViewById(R.id.activity_menu_exit_button);

        buttonsOnClickListeners();
    }

    private void buttonsOnClickListeners() {
        exitButton.setOnClickListener(v -> {
            finish();
        });
        singlePlayButton.setOnClickListener(v -> {
            Navigator.startChooseSide(this);
        });

        multiplePlayButton.setOnClickListener(v -> {
            Navigator.startGame(this, GameUtil.MULTIPLE_PLAY);

        });

        scoreButton.setOnClickListener(v -> {
            Navigator.startScores(this);
        });
    }
}
