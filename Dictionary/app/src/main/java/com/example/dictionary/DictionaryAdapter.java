package com.example.dictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dictionary.Object.History;
import com.example.dictionary.database.HistoryAppDatabase;

import java.util.List;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.HistoryViewHolder>{
    private List<History> dictionaryToAdapt;

    //defining database instance
    HistoryAppDatabase database;


    public DictionaryAdapter(Context context){
        database = HistoryAppDatabase.getInstance(context);
    }

    public void setData(List<History> dictionaryToAdapt) {
        this.dictionaryToAdapt = dictionaryToAdapt;

    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.historyword, parent, false);

        HistoryViewHolder historyViewHolder = new HistoryViewHolder(view);
        return historyViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryViewHolder holder, int position) {
        final History dictionaryAtPosition = dictionaryToAdapt.get(position);
        holder.historyWord.setText(dictionaryAtPosition.getWord());

    }

    @Override
    public int getItemCount() {
        return dictionaryToAdapt.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        public View view;
//        public Button searchButton;
        public TextView historyWord;


        public HistoryViewHolder(View v) {
            super(v);
            view = v;
//            searchButton = v.findViewById(R.id.searchButtonXML);
            historyWord = v.findViewById(R.id.historyWordTextView);
        }

    }
}