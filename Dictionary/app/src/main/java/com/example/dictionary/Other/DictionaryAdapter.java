package com.example.dictionary.Other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dictionary.Object.History;
import com.example.dictionary.R;
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
        //getting position of historical word
        final History dictionaryAtPosition = dictionaryToAdapt.get(position);
        holder.historyWord.setText(dictionaryAtPosition.getWord());
        //removing selecting if button clicked
        holder.clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.historyDao().delete(dictionaryAtPosition);
                Toast.makeText(v.getContext(), "History entry removed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dictionaryToAdapt.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView historyWord;
        Button clearButton;


        public HistoryViewHolder(View v) {
            super(v);
            view = v;
            historyWord = v.findViewById(R.id.historyWordTextView);
            clearButton = v.findViewById(R.id.removeButton);

        }

    }
}