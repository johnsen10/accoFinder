package com.example.senejean.logemoi.recherche;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;

import com.example.senejean.logemoi.DataRepository;
import com.example.senejean.logemoi.model.Post;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class RechercheViewModel extends AndroidViewModel {
    private DataRepository postDataRepository;
    public LiveData<Post> postToSaveLiveData;


    public RechercheViewModel(@NonNull Application application) {
        super(application);
        postDataRepository=new DataRepository();
    }

    public void savePost(Uri imageTosave, String decriptionPost){
        /*postToSaveLiveData= */postDataRepository.createPost(imageTosave, decriptionPost);
    }
}
