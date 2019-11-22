package com.example.dictionary.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.Other.VideoAdapter;
import com.example.dictionary.Object.YouTubeVideos;
//import com.example.dictionary.Activities.youtubeActivity;
import com.example.dictionary.R;

import java.util.Vector;

public class YouTubeVideoFragment extends Fragment {

    public RecyclerView ytRV;
    Vector<YouTubeVideos> youTubeVideos = new Vector<YouTubeVideos>();
    Button videoButton;


    public YouTubeVideoFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_video_recycler, container, false);
        ytRV = view.findViewById(R.id.ytRecyclerView);
        ytRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        ytRV.setLayoutManager(layoutManager);

        //modularising the adding process of the youtube videos into the fragment
        addVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/gHIuB1Q0GBE\" frameborder=\"0\" allowfullscreen></iframe>");
        addVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/RUwhivWbWSY\" frameborder=\"0\" allowfullscreen></iframe>");
        addVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/fhwejpYSVMw\" frameborder=\"0\" allowfullscreen></iframe>");
        addVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/sJxzZRw04To\" frameborder=\"0\" allowfullscreen></iframe>");
        addVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/l9aMjd3F9J8\" frameborder=\"0\" allowfullscreen></iframe>");
        addVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ISIMlLWA3bs\" frameborder=\"0\" allowfullscreen></iframe>");
        addVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/4XnPmj5qXbo\" frameborder=\"0\" allowfullscreen></iframe>");
        addVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/QJQg_NXLyxg\" frameborder=\"0\" allowfullscreen></iframe>");

        VideoAdapter videoAdapter = new VideoAdapter(youTubeVideos);

        ytRV.setAdapter(videoAdapter);

        return view;
    }

    public void addVideo(String link){
        youTubeVideos.add(new YouTubeVideos(link));
    }
}