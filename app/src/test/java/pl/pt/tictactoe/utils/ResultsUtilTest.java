package pl.pt.tictactoe.utils;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import pl.pt.tictactoe.data.Result;
import pl.pt.tictactoe.view.game.GameActivity;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ResultsUtilTest {

    Result result;
    ArrayList<Result> resultFromSharedPreferences;

    @Mock
    Context mockedContext;

    @Before
    public void setUp() {
        String data = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        result = new Result(data, 4,5,1,0);
        resultFromSharedPreferences = new ArrayList<>();
        resultFromSharedPreferences.add(result);
    }

    @Test
    public void checkIfGameResultSavedToMemoryIsInMemory() {
        ResultsUtil.setSingleResultDataToSharedPreferences(mockedContext, result);

        when(ResultsUtil.getSingleResultsDataFromSharedPreferences(mockedContext)).thenReturn(ResultsUtil.getSingleResultsDataFromSharedPreferences(mockedContext));

        assertEquals(result.getDate(), ResultsUtil.getSingleResultsDataFromSharedPreferences(mockedContext).get(0).getDate());
    }

}