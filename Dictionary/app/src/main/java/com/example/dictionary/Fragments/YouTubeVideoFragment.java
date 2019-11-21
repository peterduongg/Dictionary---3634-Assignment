package com.example.dictionary.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.Activities.QuizEasyQuestionsActivity;
import com.example.dictionary.Activities.QuizMenuActivity;
import com.example.dictionary.Activities.VideoAdapter;
import com.example.dictionary.Activities.YouTubeVideos;
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
        ytRV = (RecyclerView) view.findViewById(R.id.ytRecyclerView);
//        videoButton = view.findViewById(R.id.videoActivityFragmentButton);
        ytRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        ytRV.setLayoutManager(layoutManager);

        youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/eBXaKNAfmHw\" frameborder=\"0\" allowfullscreen></iframe>") );
        youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/KyJ71G2UxTQ\" frameborder=\"0\" allowfullscreen></iframe>") );
        youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/y8Rr39jKFKU\" frameborder=\"0\" allowfullscreen></iframe>") );
        youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8Hg1tqIwIfI\" frameborder=\"0\" allowfullscreen></iframe>") );
        youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/uhQ7mh_o_cM\" frameborder=\"0\" allowfullscreen></iframe>") );

        VideoAdapter videoAdapter = new VideoAdapter(youTubeVideos);

        ytRV.setAdapter(videoAdapter);

//        videoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), youtubeActivity.class);
//                startActivity(intent);
//            }
//        });


        return view;
    }
}