package com.example.senejean.logemoi.Annonce;

import android.app.Application;

import com.example.senejean.logemoi.DataRepository;
import com.example.senejean.logemoi.model.Post;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AnnonceViewModel extends AndroidViewModel {
    public LiveData<List<Post>> postTodisplayInAnnonceFragmentLiveData;
    public LiveData<List<Post>> postListUpdateLiveData;

    DataRepository annonceRepository;

    public AnnonceViewModel(@NonNull Application application) {
        super(application);
        this.annonceRepository = new DataRepository();
    }

    /**
     * Méthode permettant de recuperer les donnees relatives aux annonces de notre repository
     * @return
     */
    public LiveData<List<Post>> displayPosts(){
       postTodisplayInAnnonceFragmentLiveData= annonceRepository.getAllPostToDisplay();
        return postTodisplayInAnnonceFragmentLiveData;
        //return annonceRepository.getAllPostToDisplay();
    }

    public LiveData<List<Post>> updateAddedPost(){
        postListUpdateLiveData=annonceRepository.getUpdatePostAdded();
        return postListUpdateLiveData;
    }
}
