package pl.pt.tictactoe.data;

import java.util.Objects;

public class Result {

    private final String date;
    private final int numberOfGames;
    private final int numberOfWon;
    private final int numberOfLost;
    private final int numberOfDraw;

    public Result(String date, int numberOfGames, int numberOfWon, int numberOfLost, int numberOfDraw) {
        this.date = date;
        this.numberOfGames = numberOfGames;
        this.numberOfWon = numberOfWon;
        this.numberOfLost = numberOfLost;
        this.numberOfDraw = numberOfDraw;
    }

    public String getDate() {
        return date;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public int getNumberOfWon() {
        return numberOfWon;
    }

    public int getNumberOfLost() {
        return numberOfLost;
    }

    public int getNumberOfDraw() {
        return numberOfDraw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result that = (Result) o;
        return numberOfGames == that.numberOfGames &&
                numberOfWon == that.numberOfWon &&
                numberOfLost == that.numberOfLost &&
                numberOfDraw == that.numberOfDraw &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, numberOfGames, numberOfWon, numberOfLost, numberOfDraw);
    }
}
