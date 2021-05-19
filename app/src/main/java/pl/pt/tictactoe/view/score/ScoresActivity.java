package pl.pt.tictactoe.view.score;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import pl.pt.tictactoe.R;

public class ScoresActivity extends FragmentActivity {

    private Button returnButton;
    private ImageView rightArrow;
    private ImageView leftArrow;
    private LockableViewPager viewPager;
    private TextView subtitle;

    private PagerAdapter pagerAdapter;

    public static Intent getStartingIntent(Context context) {
        return new Intent(context, ScoresActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        returnButton = findViewById(R.id.activity_scores_return_button);
        rightArrow = findViewById(R.id.activity_scores_right_arrow);
        leftArrow = findViewById(R.id.activity_scores_left_arrow);
        subtitle = findViewById(R.id.activity_scores_subtitle);
        viewPager = findViewById(R.id.activity_scores_view_pager);


        setOnButtonClickListeners();

        pagerAdapter = new ScoresPagerAdapter(getSupportFragmentManager(), ViewPager.AUTOFILL_TYPE_LIST, this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setSwipeable(false);
    }

    private void setOnButtonClickListeners () {
        returnButton.setOnClickListener(v -> {
            finish();
        });

        rightArrow.setOnClickListener(v -> {
            viewPager.setCurrentItem(1);
            leftArrow.setVisibility(View.VISIBLE);
            rightArrow.setVisibility(View.INVISIBLE);
            subtitle.setText(R.string.activity_scores_multiple_play_title);
        });

        leftArrow.setOnClickListener(v -> {
            viewPager.setCurrentItem(0);
            rightArrow.setVisibility(View.VISIBLE);
            leftArrow.setVisibility(View.INVISIBLE);
            subtitle.setText(R.string.activity_scores_single_play_title);
        });
    }

}
