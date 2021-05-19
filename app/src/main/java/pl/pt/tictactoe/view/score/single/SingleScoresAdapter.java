package pl.pt.tictactoe.view.score.single;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pl.pt.tictactoe.R;
import pl.pt.tictactoe.data.Result;

public class SingleScoresAdapter extends BaseAdapter {

    Context context;
    ArrayList<Result> results;
    LayoutInflater layoutInflater;

    public SingleScoresAdapter(Context context, ArrayList<Result> results) {
        this.context = context;
        this.results = results;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ;
    }

    @Override
    public int getCount() {
        if (results == null) return 0;
        else if (results.size() < 10) return results.size();
        else return 10;
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_score_activity_single_player, parent, false);
        }

        TextView number = convertView.findViewById(R.id.item_score_activity_single_player_result_number);
        TextView date = convertView.findViewById(R.id.item_score_activity_single_player_result_date);
        TextView games = convertView.findViewById(R.id.item_score_activity_single_player_number_of_games);
        TextView win = convertView.findViewById(R.id.item_score_activity_single_player_number_of_win);
        TextView lost = convertView.findViewById(R.id.item_score_activity_single_player_number_of_lost);
        TextView draw = convertView.findViewById(R.id.item_score_activity_single_player_number_of_draw);

        Result result = results.get(position);

        number.setText(position+1+".");
        date.setText(result.getDate());
        games.setText(String.valueOf(result.getNumberOfGames()));
        win.setText(String.valueOf(result.getNumberOfWon()));
        lost.setText(String.valueOf(result.getNumberOfLost()));
        draw.setText(String.valueOf(result.getNumberOfDraw()));

        return convertView;
    }
}
