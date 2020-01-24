package com.example.senejean.logemoi.model;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    public String id_user;
    public String name;
    public String fname;
    public String email;
    private List<String> postIdLikeList;
    public boolean isBailleur;
    public String uid;
    private String password;


    @Exclude
    public boolean isAuthenticated;
    @Exclude
    public
    boolean isNew;
    @Exclude
    public
    boolean isCreated;

    public User() {
    }

    public User(String id_user, String name, String email/*, boolean isBailleur*/) {
        this.uid = id_user;
        this.name = name;
        this.email = email;
        postIdLikeList =new ArrayList<>();
        //this.isBailleur = isBailleur;
    }

    public List<String> getPostIdLikeList() {
        return postIdLikeList;
    }

    public void setPostIdLikeList(List<String> postIdLikeList) {
        this.postIdLikeList = postIdLikeList;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBailleur() {
        return isBailleur;
    }

    public void setBailleur(boolean bailleur) {
        isBailleur = bailleur;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public void setfname(String fname) {
        this.fname=fname;
    }
}
