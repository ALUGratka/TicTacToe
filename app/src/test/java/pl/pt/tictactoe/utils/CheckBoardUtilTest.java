package pl.pt.tictactoe.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import pl.pt.tictactoe.data.Cell;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckBoardUtilTest {

    public int x;
    public int y;
    public List<Cell> cells;

    @Before
    public void setUp() {
        x = 3;
        y = 4;
        cells = new ArrayList<>();
    }

    @Test
    public void shouldReturnFalseForCheckHorizontal() {
        //Given
        cells.add(new Cell(3, 4));

        //Then
        assertFalse(CheckBoardUtil.checkHorizontal(x, y, cells));
    }

    @Test
    public void shouldReturnTrueForCheckHorizontal() {
        cells.add(new Cell(3, 4));
        cells.add(new Cell(4, 4));
        cells.add(new Cell(5, 4));
        cells.add(new Cell(6, 4));
        cells.add(new Cell(7, 4));

        assertTrue(CheckBoardUtil.checkHorizontal(x, y, cells));
    }

    @Test
    public void shouldReturnFalseForCheckVertical() {
        //Given
        cells.add(new Cell(3, 4));
        cells.add(new Cell(3, 3));
        cells.add(new Cell(3, 5));
        cells.add(new Cell(3, 6));

        //Then
        assertFalse(CheckBoardUtil.checkVertical(x, y, cells));
    }

    @Test
    public void shouldReturnTrueForCheckVertical() {
        //Given
        cells.add(new Cell(3, 4));
        cells.add(new Cell(3, 3));
        cells.add(new Cell(3, 5));
        cells.add(new Cell(3, 6));
        cells.add(new Cell(3, 7));

        //Then
        assertTrue(CheckBoardUtil.checkVertical(x, y, cells));
    }

    @Test
    public void shouldReturnFalseForCheckLeftDiagonal() {
        cells.add(new Cell(3, 4));
        cells.add(new Cell(2, 5));
        cells.add(new Cell(4, 3));
        cells.add(new Cell(5, 2));
        cells.add(new Cell(6, 2));

        assertFalse(CheckBoardUtil.checkLeftDiagonal(x, y, cells));
    }

    @Test
    public void shouldReturnTrueForCheckLeftDiagonal() {
        cells.add(new Cell(3, 4));
        cells.add(new Cell(2, 5));
        cells.add(new Cell(4, 3));
        cells.add(new Cell(5, 2));
        cells.add(new Cell(6, 1));

        assertTrue(CheckBoardUtil.checkLeftDiagonal(x, y, cells));
    }

    @Test
    public void shouldReturnFalseForCheckRightDiagonal() {
        cells.add(new Cell(3, 4));
        cells.add(new Cell(2, 5));
        cells.add(new Cell(4, 3));
        cells.add(new Cell(5, 2));
        cells.add(new Cell(6, 2));

        assertFalse(CheckBoardUtil.checkRightDiagonal(x, y, cells));
    }

    @Test
    public void shouldReturnTrueForCheckRightDiagonal() {
        cells.add(new Cell(3, 4));
        cells.add(new Cell(1, 2));
        cells.add(new Cell(2, 3));
        cells.add(new Cell(4, 5));
        cells.add(new Cell(5, 6));

        assertTrue(CheckBoardUtil.checkRightDiagonal(x, y, cells));
    }

    @Test
    public void shouldReturnTrueForCheckBoardWhenIsHorizontal() {
        cells.add(new Cell(3, 4));
        cells.add(new Cell(4, 4));
        cells.add(new Cell(5, 4));
        cells.add(new Cell(6, 4));
        cells.add(new Cell(7, 4));

        assertTrue(CheckBoardUtil.checkBoard(cells));
    }

    @Test
    public void shouldReturnTrueForCheckBoardWhenIsVertical() {
        //Given
        cells.add(new Cell(3, 4));
        cells.add(new Cell(3, 3));
        cells.add(new Cell(3, 5));
        cells.add(new Cell(3, 6));
        cells.add(new Cell(3, 7));

        assertTrue(CheckBoardUtil.checkBoard(cells));
    }

    @Test
    public void shouldReturnTrueForCheckBoardWhenIskLeftDiagonal() {
        //Given
        cells.add(new Cell(3, 4));
        cells.add(new Cell(2, 5));
        cells.add(new Cell(4, 3));
        cells.add(new Cell(5, 2));
        cells.add(new Cell(6, 1));

        assertTrue(CheckBoardUtil.checkBoard(cells));
    }

    @Test
    public void shouldReturnTrueForCheckBoardWhenIskRightDiagonal() {
        //Given
        cells.add(new Cell(3, 4));
        cells.add(new Cell(1, 2));
        cells.add(new Cell(2, 3));
        cells.add(new Cell(4, 5));
        cells.add(new Cell(5, 6));

        assertTrue(CheckBoardUtil.checkBoard(cells));
    }

    @Test
    public void shouldReturnFalseForCheckBoard() {
        //Given
        cells.add(new Cell(3, 4));
        cells.add(new Cell(1, 9));
        cells.add(new Cell(7, 3));
        cells.add(new Cell(3, 5));
        cells.add(new Cell(5, 6));

        assertFalse(CheckBoardUtil.checkBoard(cells));
    }


}