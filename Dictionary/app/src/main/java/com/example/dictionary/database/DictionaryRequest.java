package com.example.dictionary.database;
import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionary.Activities.Dictionary;
import com.example.dictionary.DictionaryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        final String app_id = "2f8bd933";
        final String app_key = "6dee84ef268852627381f87561f98268";
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
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
            definitionTextView.setText(def);


            Toast.makeText(context, "Definition Found", Toast.LENGTH_SHORT).show();


        } catch (JSONException e) {
            definitionTextView.setText("*No Definition Found*");
            Toast.makeText(context, "There is no definition", Toast.LENGTH_SHORT).show();
            int deleteItemNumber = dictionaryAdapter.getItemCount();
        }
        Log.v("Result of Dictionary", "onPostExecute" + result);

    }
}
