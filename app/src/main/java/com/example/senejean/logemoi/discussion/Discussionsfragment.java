package com.example.senejean.logemoi.discussion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senejean.logemoi.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Discussionsfragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discussions, container, false);

    }
}
