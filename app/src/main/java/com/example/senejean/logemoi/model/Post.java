package com.example.senejean.logemoi.model;

import android.net.Uri;

import com.google.android.gms.tasks.Task;

import java.util.Date;

public class Post {

    private String imagePath;
    private String userId;
    private String id_post;
    private String description;
    private String titre="Magnifique 3 1/2 a chicoutimi";


    public Post(String userId /*,String titre*/, String description, String imagePath) {
        this.imagePath = imagePath;
        this.userId = userId;
        this.description = description;
        this.id_post="";

    }

    public Post(){}

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId_post() {
        return id_post;
    }

    public void setId_post(String id_post) {
        this.id_post = id_post;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

 }
