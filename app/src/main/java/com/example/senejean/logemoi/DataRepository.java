package com.example.senejean.logemoi;

import android.net.Uri;

import com.example.senejean.logemoi.model.Post;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import static com.example.senejean.logemoi.utilis.Constants.USERS;

public class DataRepository {

    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = rootRef.collection(USERS);
    private  CollectionReference postRef = rootRef.collection("posts");

    public MutableLiveData<List<Post>> allPostListMutableLiveData=new MutableLiveData<>();
    public MutableLiveData<List<Post>> updatePostListAddedMutableLiveData = new MutableLiveData<>();

    private String descriptionRepo="", imagePathRepo="", idPostRepo="", titreRepo="", userIdRepo="";
    private List<Post> postToDisplayRepo=new ArrayList<Post>();
    private List<Post> postToDisplayUpdateRepo=new ArrayList<Post>();

   public void createPost(Uri imageUri, String descriptionPost){

        StorageReference mImageref=FirebaseStorage.getInstance().getReference().child("images"+UUID.randomUUID().toString());


       mImageref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
           @Override
           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

               //here
               Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
               while (!urlTask.isSuccessful()) ;
               Uri downloadUrl = urlTask.getResult();

               final String sdownload_url = String.valueOf(downloadUrl);
               storeUserDataPost("mockUserId", descriptionPost,sdownload_url);
           }});


        String savedImagePath= mImageref.getDownloadUrl().toString();
       Task<Uri> url =mImageref.getDownloadUrl();
       
    }

    /**
     * Méthode utilisée pour finaliser le stockage des posts dans notre BD
     * @param userId  identifiant de l'utilisateur
     * @param description description du post
     * @param pathSavedImage  url de l'image a sauvegarder
     */
    private void storeUserDataPost(String userId,String description, String pathSavedImage){
        MutableLiveData<Post> createdPost = new MutableLiveData();
        Post postTest = new Post(userId/*, titre*/ ,description, pathSavedImage);
        //createdPost.setValue(postTest);
        String idPost= postRef.document().getId();
        postTest.setId_post(idPost);
        postRef.document(idPost).set(postTest);


    }

    /**
     *  Méthode  permettant la Recuperation des post a afficher dans le fragment annonce. A lier avec la liveData dans notre ViewModel correspondant: AnnonceViewmodel
     *  Donc des qu'on lance notre fragment, on recupere one shot toutes nos annonces qu'on affiche
     * @return
     */
    public LiveData<List<Post>> getAllPostToDisplay(){

        postRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
               List<Post> postToDisplay=new ArrayList<>();
                for (DocumentSnapshot queryDocumentSnapshot: queryDocumentSnapshots.getDocuments()){
                    Map<String, Object> map = (Map<String, Object>) queryDocumentSnapshot.getData();
                    titreRepo=map.get("titre").toString();
                    descriptionRepo=map.get("description").toString();
                    imagePathRepo=map.get("imagePath").toString();
                    userIdRepo=map.get("userId").toString();
                    idPostRepo=map.get("id_post").toString();
                    Post post= new Post(userIdRepo,descriptionRepo,imagePathRepo);
                    post.setId_post(idPostRepo);
                    //Post post= (Post) queryDocumentSnapshot.getData();
                    postToDisplay.add(post);

                }
                allPostListMutableLiveData.setValue(postToDisplay);
            }
        });

        return allPostListMutableLiveData;
    }
    private static final String TAG="DataRepository";


    public LiveData<List<Post>> getUpdatePostAdded(){

        postRef.addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e !=null) {
                    return;
                }


                for(DocumentSnapshot postSnaphots: queryDocumentSnapshots.getDocuments()){
                    Map<String, Object> map = (Map<String, Object>) postSnaphots.getData();
                    titreRepo=map.get("titre").toString();
                    descriptionRepo=map.get("description").toString();
                    imagePathRepo=map.get("imagePath").toString();
                    userIdRepo=map.get("userId").toString();
                    idPostRepo=map.get("id_post").toString();
                    Post post= new Post(userIdRepo,descriptionRepo,imagePathRepo);
                    post.setId_post(idPostRepo);

                    postToDisplayUpdateRepo.add(post);

                }
            }
        });
        updatePostListAddedMutableLiveData.setValue(postToDisplayRepo);

       return updatePostListAddedMutableLiveData;
    }

    public void savePostLikeForCurrentUser(){

    }

}
