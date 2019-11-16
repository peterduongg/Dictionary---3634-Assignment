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

import com.example.dictionary.Activities.QuizQuestions;
import com.example.dictionary.R;

public class QuizMenuFragment extends Fragment {
    Button buttonStartMediumQuiz;

    public QuizMenuFragment(){
        //empty constructor to refer to
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_quiz_menu, container, false);

        buttonStartMediumQuiz = view.findViewById(R.id.mediumQuizButton);

        buttonStartMediumQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz(view);
            }
        });
        return view;
    }
    private void startQuiz(View view){
        Intent intent = new Intent(view.getContext(), QuizQuestions.class);
        startActivity(intent);
    }
}
