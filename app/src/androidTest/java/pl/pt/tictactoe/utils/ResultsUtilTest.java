package pl.pt.tictactoe.utils;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import pl.pt.tictactoe.data.Result;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ResultsUtilTest {

    Result result;
    ArrayList<Result> resultFromSharedPreferences;
    Context mockedContext;

    @Before
    public void setUp() {
        String data = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        result = new Result(data, 4,5,1,0);
        resultFromSharedPreferences = new ArrayList<>();
        resultFromSharedPreferences.add(result);
        mockedContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void checkIfSingleResultSavedToMemoryIsInMemory() {
        ResultsUtil.setSingleResultDataToSharedPreferences(mockedContext, result);

        assertEquals(result, ResultsUtil.getSingleResultsDataFromSharedPreferences(mockedContext).get(0));
    }

    @Test
    public void checkIfMultipleResultSavedToMemoryIsInMemory() {
        ResultsUtil.setMultipleResultDataToSharedPreferences(mockedContext, result);

        assertEquals(result, ResultsUtil.getMultipleResultsDataFromSharedPreferences(mockedContext).get(0));
    }


}