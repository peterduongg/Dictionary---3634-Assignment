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

import com.example.dictionary.Activities.QuizMenuActivity;
import com.example.dictionary.R;

public class QuizWarningFragment extends Fragment {
    Button continueButton;

    public QuizWarningFragment(){
        //empty constructor to refer to
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.quiz_warning_fragment, container, false);

        continueButton = view.findViewById(R.id.buttonContinue);
        //button leading to Quiz Menu
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizMenu(view);
            }
        });
        return view;
    }
    private void startQuizMenu(View view){
        Intent intent = new Intent(view.getContext(), QuizMenuActivity.class);
        startActivity(intent);
    }

}
