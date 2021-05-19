package pl.pt.tictactoe.navigation;

import android.content.Context;

import pl.pt.tictactoe.utils.GameUtil;
import pl.pt.tictactoe.utils.UserUtil;
import pl.pt.tictactoe.view.game.GameActivity;
import pl.pt.tictactoe.view.menu.MenuActivity;
import pl.pt.tictactoe.view.score.ScoresActivity;
import pl.pt.tictactoe.view.site.ChooseSideActivity;

public class Navigator {

    public static void startMenu(Context context) {
        context.startActivity(MenuActivity.getStartingIntent(context));
    }

    public static void startChooseSide (Context context) {
        context.startActivity(ChooseSideActivity.getStartingIntent(context));
    }

    public static void startGame (Context context, GameUtil USER_MODE) {
        context.startActivity(GameActivity.getStartingIntent(context, USER_MODE));
    }

    public static void startScores(Context context) {
        context.startActivity(ScoresActivity.getStartingIntent(context));
    }

}
