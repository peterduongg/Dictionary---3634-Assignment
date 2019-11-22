package com.example.dictionary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.dictionary.Fragments.DictionaryMenuFragment;
import com.example.dictionary.Fragments.QuizWarningFragment;
import com.example.dictionary.Fragments.YouTubeVideoFragment;
import com.example.dictionary.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new DictionaryMenuFragment();
        swapFragment(fragment);
        //setting button navigation bar
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                //if clicked on DictionaryActivity bar change to it
                if (menuItem.getItemId() == R.id.nav_Dictionary) {
                    //change below to your fragment
                    Fragment fragment = new DictionaryMenuFragment();
                    swapFragment(fragment);
                    return true;
                    //if clicked on video bar change to it
                } else if (menuItem.getItemId() == R.id.nav_Videos) {
                    //change below to your fragment
                    Fragment fragment = new YouTubeVideoFragment();
                    swapFragment(fragment);
                    return true;
                    //if clicked on quiz bar change to it
                } else if (menuItem.getItemId() == R.id.nav_Quiz) {
                    Fragment fragment = new QuizWarningFragment();
                    swapFragment(fragment);
                    return true;
                } else
                return false;
            }
        });

    }

    //method that takes variables to swap to corresponding fragment
    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }
}
