package pl.pt.tictactoe.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.pt.tictactoe.data.Cell;

public class CheckBoardUtil {

    public static final int MAX_COUNT_IN_LINE = 5;

    public static boolean checkBoard(List<Cell> list) {
        for (Cell cell : list) {
            int x = cell.getX();
            int y = cell.getY();

            boolean win = checkHorizontal(x, y, list);
            if (win) return true;
            win = checkVertical(x, y, list);
            if (win) return true;
            win = checkLeftDiagonal(x, y, list);
            if (win) return true;
            win = checkRightDiagonal(x, y, list);
            if (win) return true;
        }
        return false;
    }

    public static boolean checkHorizontal(int x, int y, List<Cell> cells) {
        int count = 1;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (cells.contains(new Cell(x + i, y))) {
                count++;
            } else break;
        }
        if (count == MAX_COUNT_IN_LINE) return true;

        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (cells.contains(new Cell(x - i, y))) {
                count++;
            } else break;
        }

        if (count == MAX_COUNT_IN_LINE) return true;
        return false;
    }


    public static boolean checkVertical(int x, int y, List<Cell> cells) {
        int count = 1;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (cells.contains(new Cell(x, y - i))) {
                count++;
            } else break;
        }

        if (count == MAX_COUNT_IN_LINE) return true;

        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (cells.contains(new Cell(x, y + i))) {
                count++;
            } else break;
        }

        if (count == MAX_COUNT_IN_LINE) return true;
        return false;
    }

    public static boolean checkLeftDiagonal(int x, int y, List<Cell> cells) {
        int count = 1;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (cells.contains(new Cell(x - i, y + i))) {
                count++;
            } else {
                break;
            }
        }

        if (count == MAX_COUNT_IN_LINE) return true;

        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (cells.contains(new Cell(x + i, y - i))) {
                count++;
            } else {
                break;
            }
        }

        if (count == MAX_COUNT_IN_LINE) return true;
        return false;
    }

    public static boolean checkRightDiagonal(int x, int y, List<Cell> cells) {
        int count = 1;
        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            if (cells.contains(new Cell(x - i, y - i))) {
                count++;
            } else {
                break;
            }
        }

        if (count == MAX_COUNT_IN_LINE) return true;

        for (int i = 1; i < MAX_COUNT_IN_LINE; i++) {
            int new_x = x+i;
            int new_y = y+i;
            if (cells.contains(new Cell(new_x, new_y))) {
                count++;
            } else {
                break;
            }
        }

        if (count == MAX_COUNT_IN_LINE) return true;
        return false;
    }

}
