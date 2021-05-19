package pl.pt.tictactoe.view.site;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pl.pt.tictactoe.R;
import pl.pt.tictactoe.navigation.Navigator;
import pl.pt.tictactoe.utils.GameUtil;

public class ChooseSideActivity extends AppCompatActivity {

    private GameUtil userSide;

    Button startButton;
    Button backButton;
    TextView xTextView;
    TextView oTextView;
    ImageView xImageView;
    ImageView oImageView;

    public static Intent getStartingIntent(Context context) {
        return new Intent(context, ChooseSideActivity.class);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_site);

        startButton = findViewById(R.id.activity_choose_site_start_button);
        backButton = findViewById(R.id.activity_choose_site_back_button);
        xTextView = findViewById(R.id.activity_choose_site_x_sign_text);
        oTextView = findViewById(R.id.activity_choose_site_o_sign_text);
        xImageView = findViewById(R.id.activity_choose_site_x_sign_button);
        oImageView = findViewById(R.id.activity_choose_site_o_sign_button);

        buttonsOnClickListeners();
    }

    private void buttonsOnClickListeners() {
        backButton.setOnClickListener(v -> {
            finish();
        });

        xImageView.setOnClickListener(v -> {
            xTextView.setTypeface(null, Typeface.BOLD);
            oTextView.setTypeface(null, Typeface.NORMAL);
            startButton.setEnabled(true);
            xImageView.setSelected(true);
            oImageView.setSelected(false);
            userSide = GameUtil.SINGLE_PLAY_X;
        });

        oImageView.setOnClickListener(v -> {
            oTextView.setTypeface(null, Typeface.BOLD);
            xTextView.setTypeface(null, Typeface.NORMAL);
            startButton.setEnabled(true);
            xImageView.setSelected(false);
            oImageView.setSelected(true);
            userSide = GameUtil.SINGLE_PLAY_O;
        });
        startButton.setOnClickListener( v -> {
            Navigator.startGame(this, userSide);
            finish();
        });
    }
}
