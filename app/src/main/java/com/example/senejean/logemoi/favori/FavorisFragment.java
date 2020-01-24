package com.example.senejean.logemoi.favori;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senejean.logemoi.R;
import com.example.senejean.logemoi.model.Post;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavorisFragment extends Fragment {

    //La ce ne sont que des donnees de test
    private List<Post> annonceLikeList=new ArrayList<>();
    RecyclerView favoriRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    FavoriRecyclerViewDataAdapter favoriRecyclerViewDataAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_favoris, container, false);
       initFavoriRecyclerView(view);

        return view;
        //return inflater.inflate(R.layout.fragment_favoris, container, false);


    }

    private void initFavoriRecyclerView(View view){
        favoriRecyclerView= view.findViewById(R.id.icon_favori);
        layoutManager=new LinearLayoutManager(getActivity());
        favoriRecyclerView.setLayoutManager(layoutManager);
        initLikeTest();
        favoriRecyclerViewDataAdapter=new FavoriRecyclerViewDataAdapter(annonceLikeList,getActivity());
        favoriRecyclerView.setAdapter(favoriRecyclerViewDataAdapter);
    }

    private void initLikeTest(){
        annonceLikeList.add(new Post("user1","ma superbe description1","imgaePath1"));
        annonceLikeList.add(new Post("user2","ma superbe description2","imgaePath2"));
        annonceLikeList.add(new Post("user3","ma superbe description3","imgaePath3"));
        annonceLikeList.add(new Post("user4","ma superbe description4","imgaePath4"));



    }
}
