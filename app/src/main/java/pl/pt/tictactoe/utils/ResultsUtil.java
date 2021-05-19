package pl.pt.tictactoe.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.viewpager.widget.PagerAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import pl.pt.tictactoe.data.Result;

public class ResultsUtil {

    private final static String PREFS_TAG = "SharedPrefs";
    private final static String MULTIPLE_RESULTS_TAG = "MultiplePlayResults";
    private final static String SINGLE_RESULTS_TAG = "SinglePlayResults";

    public static ArrayList<Result> getMultipleResultsDataFromSharedPreferences(Context context) {
        Gson gson = new Gson();
        ArrayList<Result> resultFromSharedPreferences;

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
        String jsonPreferences = sharedPreferences.getString(MULTIPLE_RESULTS_TAG, "");

        Type type = new TypeToken<ArrayList<Result>>() {}.getType();
        resultFromSharedPreferences = gson.fromJson(jsonPreferences, type);

        return resultFromSharedPreferences;
    }

    public static ArrayList<Result> getSingleResultsDataFromSharedPreferences(Context context) {
        Gson gson = new Gson();
        ArrayList<Result> resultFromSharedPreferences = new ArrayList<>();

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
        String jsonPreferences = sharedPreferences.getString(SINGLE_RESULTS_TAG, "");

        Type type = new TypeToken<ArrayList<Result>>() {}.getType();
        resultFromSharedPreferences = gson.fromJson(jsonPreferences, type);

        return resultFromSharedPreferences;
    }

    public static void setMultipleResultDataToSharedPreferences(Context context, Result result) {
        ArrayList<Result> resultFromSharedPreferences = getMultipleResultsDataFromSharedPreferences(context);

        if(resultFromSharedPreferences == null) resultFromSharedPreferences = new ArrayList<>();;
        if( result.getNumberOfGames()>0) resultFromSharedPreferences.add(result);


        Gson gson = new Gson();
        String json = gson.toJson(resultFromSharedPreferences);

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(MULTIPLE_RESULTS_TAG, json).apply();
    }

    public static void setSingleResultDataToSharedPreferences(Context context, Result result) {
        ArrayList<Result> resultFromSharedPreferences = getSingleResultsDataFromSharedPreferences(context);

        if(resultFromSharedPreferences == null) resultFromSharedPreferences = new ArrayList<>();;
        if( result.getNumberOfGames()>0) resultFromSharedPreferences.add(result);

        Gson gson = new Gson();
        String json = gson.toJson(resultFromSharedPreferences);

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(SINGLE_RESULTS_TAG, json).apply();
    }
}
