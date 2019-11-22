package com.example.dictionary.Other;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionary.Activities.DictionaryActivity;
import com.example.dictionary.database.HistoryAppDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// https://developer.oxforddictionaries.com/documentation?fbclid=IwAR2J3wemtcZhC0kkfTQpRwGm6NB0XyPTBAwolZ8NEWTid_ICrBbVOivTEyo#!/Entries/get_entries_source_lang_word_id
public class DictionaryRequest extends AsyncTask <String, Integer, String> {

    Context context;
    TextView definitionTextView;
    HistoryAppDatabase historyAppDatabase;
    DictionaryAdapter dictionaryAdapter;


    public DictionaryRequest(Context context, TextView DefinitionTextView){
        this.context = context;
        definitionTextView = DefinitionTextView;
        historyAppDatabase = HistoryAppDatabase.getInstance(context);
        dictionaryAdapter = new DictionaryAdapter(context);
    }


    @Override
    protected String doInBackground(String... params) {
        //defining API keys and ID
        final String app_id = "2f8bd933";
        final String app_key = "6dee84ef268852627381f87561f98268";
        try {
            //taking in URL parameter
            URL url = new URL(params[0]);
            //making connecting to secure HTTP(S)
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            //setting previous variables into corresponding location to get response
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        String def;
        try {

            JSONObject js = new JSONObject(result);
            JSONArray results = js.getJSONArray("results");

            JSONObject lEntries = results.getJSONObject(0);
            JSONArray laArray = lEntries.getJSONArray("lexicalEntries");

            JSONObject entries = laArray.getJSONObject(0);
            JSONArray e = entries.getJSONArray("entries");

            JSONObject jsonObject = e.getJSONObject(0);
            JSONArray sensesArray = jsonObject.getJSONArray("senses");

            JSONObject de = sensesArray.getJSONObject(0);
            JSONArray d = de.getJSONArray("definitions");

            def = d.getString(0);
            //place definition into text view
            definitionTextView.setText(def);

            //calling the 'addHistory" method in DictionaryActivity to add word since response was successful
            DictionaryActivity.addHistory(context);
            //feedback response
            Toast.makeText(context, "Definition Found! Added to history.", Toast.LENGTH_SHORT).show();


        } catch (JSONException e) {
            //no response feedback
            definitionTextView.setText("*No Definition Found*");
            Toast.makeText(context, "There is no definition", Toast.LENGTH_SHORT).show();

        }
        Log.v("Result of DictionaryActivity", "onPostExecute" + result);

    }
}
