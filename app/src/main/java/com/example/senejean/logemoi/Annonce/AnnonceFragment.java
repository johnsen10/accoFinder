package com.example.senejean.logemoi.Annonce;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senejean.logemoi.R;
import com.example.senejean.logemoi.model.Post;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AnnonceFragment extends Fragment {
    //La ce ne sont que des donnees de test
    private List<Post>annonceList=new ArrayList<>();
    AnnonceViewModel   annonceViewModel;
    RecyclerView annonceRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    AnnonceRecyclerViewDataAdapter annonceRecyclerViewDataAdapter;

    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private  CollectionReference postRef = rootRef.collection("posts");
    List<Post> postToDisplay=new ArrayList<Post>();
    String titre="", description="", userId="",imagePath="", idPost="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_annonce, container, false);
       // return inflater.inflate(R.layout.annonce_item, container, false);

        View view= inflater.inflate(R.layout.fragment_annonce, container,false);


       //annonceInitialisation();
        //recuperation des annonces avec notre ViewModel
        initAnnonceViewModel();
        loadAnnanonces();
        initRecyclerView(view);


        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        /*annonceViewModel.updateAddedPost().observe(getActivity(),postToUpdateLiveData->{
            postToDisplay=new ArrayList<Post>();
            for(Post postToUpdate: postToUpdateLiveData){
                if (!annonceRecyclerViewDataAdapter.postItemList.contains(postToUpdate)) {
                    postToDisplay.add(postToUpdate);
                }
            }
            annonceRecyclerViewDataAdapter.postItemList=postToDisplay;
        });*/


        postRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e !=null){
                    return ;
                }
                //annonceRecyclerViewDataAdapter.postItemList=new ArrayList<Post>();
               postToDisplay=new ArrayList<Post>();
                for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    Map<String, Object> map = (Map<String, Object>) documentSnapshot.getData();
                    titre=map.get("titre").toString();
                    description=map.get("description").toString();
                    imagePath=map.get("imagePath").toString();
                    userId=map.get("userId").toString();
                    idPost=map.get("id_post").toString();
                    Post post= new Post(userId,description,imagePath);
                    post.setId_post(idPost);

                    if (!annonceRecyclerViewDataAdapter.postItemList.contains(post)) {
                        postToDisplay.add(post);
                    }
                }
                annonceRecyclerViewDataAdapter.postItemList=postToDisplay;


//                annonceRecyclerViewDataAdapter.postItemList=postToDisplay;
            }
        });
    }

    public  void initRecyclerView(View view){

        annonceRecyclerView= view.findViewById(R.id.icon_annonce);
        layoutManager=new LinearLayoutManager(getActivity());
        annonceRecyclerView.setLayoutManager(layoutManager);
        annonceRecyclerViewDataAdapter=new AnnonceRecyclerViewDataAdapter(annonceList,getActivity());
        annonceRecyclerView.setAdapter(annonceRecyclerViewDataAdapter);

    }

private static final String TAG="AnnonceFragment";

        public void loadAnnanonces(){
            annonceViewModel.displayPosts().observe(getActivity(),postToDisplayInAnnonceFragmentLiveData->{
                for(Post postToDisplay: postToDisplayInAnnonceFragmentLiveData){
                    annonceList.add(postToDisplay);
                }
            });

            /*postRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (DocumentSnapshot queryDocumentSnapshot: queryDocumentSnapshots.getDocuments()){
                        Map<String, Object> map = (Map<String, Object>) queryDocumentSnapshot.getData();
                        titre=map.get("titre").toString();
                        description=map.get("description").toString();
                        imagePath=map.get("imagePath").toString();
                        userId=map.get("userId").toString();
                        idPost=map.get("id_post").toString();
                        Post post= new Post(userId,description,imagePath);
                        post.setId_post(idPost);
                        //Post post= (Post) queryDocumentSnapshot.getData();
                        postToDisplay.add(post);
                        Log.d(TAG,queryDocumentSnapshot.getId()+"==>"+queryDocumentSnapshot.getData());
                    }
                }
            });*/


    }

    private void initAnnonceViewModel(){
        annonceViewModel= new ViewModelProvider(this).get(AnnonceViewModel.class);
/*       annonceViewModel.displayPosts().observe(getActivity(),postToDisplayInAnnonceFragmentLiveData->{
            for(Post postToDisplay: postToDisplayInAnnonceFragmentLiveData){
                annonceList.add(postToDisplay);
            }
            });
*/

    }
}

/*

            postRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    if (e !=null) {
                        return;
                    }


                    for(QueryDocumentSnapshot postSnaphots: queryDocumentSnapshots){
                        //  postToDisplay.add(postSnaphots);
                        Post postDisplay= postSnaphots.toObject(Post.class);
                        postToDisplay.add(postDisplay);

                    }
                }
            });*/