package com.example.dictionary.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dictionary.Activities.Dictionary;
import com.example.dictionary.Activities.MainActivity;
import com.example.dictionary.R;

public class DictionaryMenuFragment extends Fragment {
    Button dictionaryButton;
    Button wordGeneratorButton;
    Button historyButton;

    public DictionaryMenuFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dictionary_menu, container, false);
        dictionaryButton = view.findViewById(R.id.dictionary_button);
        wordGeneratorButton = view.findViewById(R.id.word_generator_button);
        historyButton = view.findViewById(R.id.history_button);

        dictionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Dictionary.class);
                startActivity(intent);
            }
        });

        return view;
    }


}
