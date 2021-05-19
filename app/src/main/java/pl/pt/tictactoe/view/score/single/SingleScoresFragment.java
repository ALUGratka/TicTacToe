package pl.pt.tictactoe.view.score.single;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Comparator;

import pl.pt.tictactoe.R;
import pl.pt.tictactoe.data.Result;
import pl.pt.tictactoe.utils.ResultsUtil;

public class SingleScoresFragment extends Fragment {

    private ListView listView;
    private ArrayList<Result> results = new ArrayList<>();

    SingleScoresAdapter singleScoresAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.fragment_single_score_list_view);
        results = ResultsUtil.getSingleResultsDataFromSharedPreferences(view.getContext());

        if(results != null) sortResultsList();
        else results = new ArrayList<>();

        singleScoresAdapter = new SingleScoresAdapter(view.getContext(), results);
        listView.setAdapter(singleScoresAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_single_scores, container, false);
    }


    private void sortResultsList() {
        results.sort(Comparator.comparing(Result::getNumberOfGames).reversed());
    }
}
