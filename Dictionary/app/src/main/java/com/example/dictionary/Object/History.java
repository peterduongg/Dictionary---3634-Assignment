package com.example.dictionary.Object;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity
public class History {

    @PrimaryKey
    @NonNull
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


}
