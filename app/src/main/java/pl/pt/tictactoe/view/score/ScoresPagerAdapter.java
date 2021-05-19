package pl.pt.tictactoe.view.score;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import pl.pt.tictactoe.view.score.multiple.MultipleScoresFragment;
import pl.pt.tictactoe.view.score.single.SingleScoresFragment;

public class ScoresPagerAdapter extends FragmentPagerAdapter {

    Context context;

    public ScoresPagerAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position == 0 ){
            fragment = new SingleScoresFragment();
        }
        else if (position == 1) {
            fragment = new MultipleScoresFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }


}
