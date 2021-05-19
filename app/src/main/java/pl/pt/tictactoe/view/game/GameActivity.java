package pl.pt.tictactoe.view.game;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import pl.pt.tictactoe.R;
import pl.pt.tictactoe.data.Cell;
import pl.pt.tictactoe.data.Result;
import pl.pt.tictactoe.utils.CheckBoardUtil;
import pl.pt.tictactoe.utils.GameUtil;
import pl.pt.tictactoe.utils.ResultsUtil;
import pl.pt.tictactoe.utils.UserUtil;

public class GameActivity extends AppCompatActivity implements GameView {

    private static GameUtil gameMode;

    private final ImageView[][] cellImage = new ImageView[10][10];
    private final Drawable[] drawCell = new Drawable[4];
    private UserUtil currentPlayer;
    private ArrayList<Cell> xCells = new ArrayList<>();
    private ArrayList<Cell> oCells = new ArrayList<>();
    private int xWins = 0;
    private int oWins = 0;
    private int draw = 0;

    private LinearLayout.LayoutParams rowLayoutParams;
    private LinearLayout.LayoutParams cellLayoutParams;

    private Button exitGameButton;
    private TextView currentPlayerX;
    private TextView currentPlayerO;
    private LinearLayout gameLayout;
    private LinearLayout rowLayout;
    private TextView numberOfXWins;
    private TextView numberOfOWins;
    private TextView numberOfDraws;

    public static Intent getStartingIntent(Context context, GameUtil USER_MODE) {
        gameMode = USER_MODE;
        return new Intent(context, GameActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        exitGameButton = findViewById(R.id.activity_game_finish_button);
        currentPlayerX = findViewById(R.id.activity_game_X_playing_text_view);
        currentPlayerO = findViewById(R.id.activity_game_O_playing_text_view);
        gameLayout = findViewById(R.id.activity_game_board_game_layout);
        numberOfXWins = findViewById(R.id.activity_game_X_win_number_text_view);
        numberOfOWins = findViewById(R.id.activity_game_O_win_number_text_view);
        numberOfDraws = findViewById(R.id.activity_game_draw_number_text_view);

        setUpGameBoard();
        setUpGameMode();
        loadSingleCell();
        setButtonListeners();

        if (savedInstanceState != null) {
            getSaveInstanceState(savedInstanceState);
            restoreGameBoard(xCells, oCells);
        } else {
            generateGameBoard();
        }

        numberOfXWins.setText(xWins + getResources().getString(R.string.game_activity_x_wod_count));
        numberOfOWins.setText(oWins + getResources().getString(R.string.game_activity_o_wod_count));
        numberOfDraws.setText(draw + getResources().getString(R.string.game_activity_draw_wod_count));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("xCells", xCells);
        outState.putParcelableArrayList("oCells", oCells);
        outState.putInt("xWins", xWins);
        outState.putInt("oWins", oWins);
        outState.putInt("draw", draw);
        super.onSaveInstanceState(outState);
    }

    private void getSaveInstanceState(Bundle savedInstanceState) {
        xCells = savedInstanceState.getParcelableArrayList("xCells");
        oCells = savedInstanceState.getParcelableArrayList("oCells");
        xWins = savedInstanceState.getInt("xWins");
        oWins = savedInstanceState.getInt("oWins");
        draw = savedInstanceState.getInt("draw");
    }

    @Override
    public void onBackPressed() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.activity_game_alert_dialog_title)
                .setMessage(R.string.activity_game_alert_dialog_text)
                .setNegativeButton(R.string.activity_game_alert_dialog_negative_answer, (dialog, which) -> {
                }).setPositiveButton(R.string.activity_game_alert_dialog_positive_answer, (dialog, which) -> {
            if (gameMode == GameUtil.MULTIPLE_PLAY) {
                ResultsUtil.setMultipleResultDataToSharedPreferences(this,
                        new Result(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date()),
                                (xWins + oWins + draw), xWins, oWins, draw));
            } else if (gameMode == GameUtil.SINGLE_PLAY_X){
                ResultsUtil.setSingleResultDataToSharedPreferences(this,
                        new Result(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date()),
                                (xWins + oWins + draw), xWins, oWins, draw));
            } else {
                ResultsUtil.setSingleResultDataToSharedPreferences(this,
                        new Result(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date()),
                                (xWins + oWins + draw), oWins, xWins, draw));
            }

            super.onBackPressed();
        }).show();
    }

    private void setButtonListeners() {
        exitGameButton.setOnClickListener(v -> {
                    new MaterialAlertDialogBuilder(v.getContext())
                            .setTitle(R.string.activity_game_alert_dialog_title)
                            .setMessage(R.string.activity_game_alert_dialog_text)
                            .setNegativeButton(R.string.activity_game_alert_dialog_negative_answer, (dialog, which) -> {
                            }).setPositiveButton(R.string.activity_game_alert_dialog_positive_answer, (dialog, which) -> {
                        if (gameMode == GameUtil.MULTIPLE_PLAY) {
                            ResultsUtil.setMultipleResultDataToSharedPreferences(this,
                                    new Result(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date()),
                                            (xWins + oWins + draw), xWins, oWins, draw));
                        } else if (gameMode == GameUtil.SINGLE_PLAY_X){
                            ResultsUtil.setSingleResultDataToSharedPreferences(this,
                                    new Result(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date()),
                                            (xWins + oWins + draw), xWins, oWins, draw));
                        } else {
                            ResultsUtil.setSingleResultDataToSharedPreferences(this,
                                    new Result(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date()),
                                            (xWins + oWins + draw), oWins, xWins, draw));
                        }
                        finish();
                    }).show();
                }
        );
    }

    private void loadSingleCell() {
        drawCell[3] = ResourcesCompat.getDrawable(getResources(), R.drawable.shape_cell_background, null);

        drawCell[0] = null;

        drawCell[1] = ResourcesCompat.getDrawable(getResources(), R.drawable.button_x_on_board, null);
        drawCell[2] = ResourcesCompat.getDrawable(getResources(), R.drawable.button_o_on_board, null);
    }

    private void setUpGameMode() {
        if (gameMode == GameUtil.MULTIPLE_PLAY || gameMode == GameUtil.SINGLE_PLAY_X) {
            currentPlayer = UserUtil.USER_X;
            setCurrentPlayerOEnabled(View.GONE);
            setCurrentPlayerXEnabled(View.VISIBLE);
        } else if (gameMode == GameUtil.SINGLE_PLAY_O) {
            currentPlayer = UserUtil.USER_O;
            setCurrentPlayerXEnabled(View.GONE);
            setCurrentPlayerOEnabled(View.VISIBLE);
        }
    }

    private void setUpGameBoard() {
        int width, height;

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            width = setScreenWidth();
            height = width;
        } else {
            height = setScreenHeight();
            width = height;
        }

        rowLayoutParams = new LinearLayout.LayoutParams(width, height / 10);
        cellLayoutParams = new LinearLayout.LayoutParams(width / 10, height / 10);
    }

    private void restoreGameBoard(ArrayList<Cell> xCells, ArrayList<Cell> oCells) {
        for (int i = 0; i < 10; i++) {
            rowLayout = new LinearLayout(this);
            for (int j = 0; j < 10; j++) {
                cellImage[i][j] = new ImageView(this);
                if (xCells.contains(new Cell(i + 1, j + 1)))
                    cellImage[i][j].setBackground(drawCell[1]);
                else if (oCells.contains(new Cell(i + 1, j + 1)))
                    cellImage[i][j].setBackground(drawCell[2]);
                else cellImage[i][j].setBackground(drawCell[3]);

                gameLogic(i, j);

                rowLayout.addView(cellImage[i][j], cellLayoutParams);
            }
            gameLayout.addView(rowLayout, rowLayoutParams);
        }
    }

    private void generateGameBoard() {
        for (int i = 0; i < 10; i++) {
            rowLayout = new LinearLayout(this);
            for (int j = 0; j < 10; j++) {
                cellImage[i][j] = new ImageView(this);

                cellImage[i][j].setBackground(drawCell[3]);

                gameLogic(i, j);

                rowLayout.addView(cellImage[i][j], cellLayoutParams);
            }
            gameLayout.addView(rowLayout, rowLayoutParams);
        }
    }

    private void resetGame() {
        xCells = new ArrayList<>();
        oCells = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cellImage[i][j].setBackground(drawCell[0]);
                cellImage[i][j].setBackground(drawCell[3]);
                gameLogic(i, j);
            }
        }
    }

    private void gameLogic(int i, int j) {
        final int x = i;
        final int y = j;
        final ArrayList<Cell> xPointsCells = xCells;
        final ArrayList<Cell> oPointsCells = oCells;

        cellImage[i][j].setOnClickListener(v -> {


            if (xPointsCells.size() + oPointsCells.size() > 75) {
                gameOverLogic();
            } else if (cellImage[x][y].getBackground() == drawCell[1] || cellImage[x][y].getBackground() == drawCell[2]) {
            } else if (currentPlayer == UserUtil.USER_X) {
                cellImage[x][y].setBackground(drawCell[1]);

                if (gameMode == GameUtil.MULTIPLE_PLAY) {
                    currentPlayer = UserUtil.USER_O;
                    setCurrentPlayerXEnabled(View.GONE);
                    setCurrentPlayerOEnabled(View.VISIBLE);
                }

                xPointsCells.add(new Cell(x + 1, y + 1));
                if (CheckBoardUtil.checkBoard(xPointsCells)) {
                    gameOverLogic(UserUtil.USER_X);
                }
                else {
                    if (gameMode == GameUtil.SINGLE_PLAY_X) {
                        botGameLogic(oPointsCells, drawCell[2]);
                        if (CheckBoardUtil.checkBoard(oPointsCells)) {
                            gameOverLogic(UserUtil.USER_O);
                            currentPlayer = UserUtil.USER_X;
                        }
                    }
                }

            } else if (currentPlayer == UserUtil.USER_O) {
                if (gameMode == GameUtil.SINGLE_PLAY_O) {
                    botGameLogic(xPointsCells, drawCell[1]);
                    if (CheckBoardUtil.checkBoard(xPointsCells)) {
                        gameOverLogic(UserUtil.USER_X);
                        currentPlayer = UserUtil.USER_O;
                    }
                }
                cellImage[x][y].setBackground(drawCell[2]);

                if (gameMode == GameUtil.MULTIPLE_PLAY) {
                    currentPlayer = UserUtil.USER_X;
                    setCurrentPlayerOEnabled(View.GONE);
                    setCurrentPlayerXEnabled(View.VISIBLE);
                }

                oPointsCells.add(new Cell(x + 1, y + 1));
                if (CheckBoardUtil.checkBoard(oPointsCells)) {
                    gameOverLogic(UserUtil.USER_O);
                }
            }
        });
    }

    private void botGameLogic(ArrayList<Cell> cells, Drawable drawable) {
        int randomX;
        int randomY;
        do {
            randomX = (new Random()).nextInt(10);
            randomY = (new Random()).nextInt(10);
        } while (cellImage[randomX][randomY].getBackground() == drawCell[1] || cellImage[randomX][randomY].getBackground() == drawCell[2]);
        cellImage[randomX][randomY].setBackground(drawable);
        cells.add(new Cell(randomX+1, randomY+1));
    }

    private void gameOverLogic(UserUtil user) {
        if (user == UserUtil.USER_X) {
            Toast.makeText(this, "User X won this round!", Toast.LENGTH_SHORT).show();
            xWins++;
            numberOfXWins.setText(xWins + getString(R.string.game_activity_x_wod_count));
        } else {
            Toast.makeText(this, "User O won this round!", Toast.LENGTH_SHORT).show();
            oWins++;
            numberOfOWins.setText(oWins + getString(R.string.game_activity_o_wod_count));
        }
        resetGame();
    }

    private void gameOverLogic() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        draw++;
        numberOfDraws.setText(draw + getString(R.string.game_activity_draw_wod_count));
        resetGame();
    }

    private int setScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Log.i("screen_width", String.valueOf(displayMetrics.widthPixels));
        return displayMetrics.widthPixels - 180;
    }

    private int setScreenHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Log.i("screen_height", String.valueOf(displayMetrics.heightPixels));
        return displayMetrics.heightPixels - 300;
    }

    @Override
    public void setCurrentPlayerXEnabled(int visible) {
        currentPlayerX.setVisibility(visible);
    }

    @Override
    public void setCurrentPlayerOEnabled(int visible) {
        currentPlayerO.setVisibility(visible);
    }
}
