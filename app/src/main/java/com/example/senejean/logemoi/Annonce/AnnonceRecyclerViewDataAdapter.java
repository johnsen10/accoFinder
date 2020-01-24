package com.example.senejean.logemoi.Annonce;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.senejean.logemoi.R;
import com.example.senejean.logemoi.model.Post;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnnonceRecyclerViewDataAdapter extends RecyclerView.Adapter<AnnonceRecyclerViewItemHolder> {

    List<Post>postItemList;
    private Context context;
    private  FirebaseFirestore racineRef=FirebaseFirestore.getInstance();
    //private CollectionReference postRefs = racineRef.collection("posts");


    public AnnonceRecyclerViewDataAdapter(List<Post> postItemList, Context context) {
        this.postItemList = postItemList;
        this.context= context;
    }

    @Override
    public AnnonceRecyclerViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Get LayoutInflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the RecyclerView item layout xml.
        View annonceItemView = layoutInflater.inflate(R.layout.annonce_item, parent, false);

/*
        // Get annonce title text view object.
        final TextView annonceTitleView = (TextView)annonceItemView.findViewById(R.id.my_text_view_titre_annonce);
        final TextView annonceDescriptionView = (TextView)annonceItemView.findViewById(R.id.my_text_view_description_annonce);

        // Get annonce image view and imageButton object.
        final ImageView annonceImageView = (ImageView)annonceItemView.findViewById(R.id.card_view_image_annonce);
        final ImageButton annonceImageButtonLikeView= annonceItemView.findViewById(R.id.like_button);
*/
        // Create and return our custom Car Recycler View Item Holder object.
        AnnonceRecyclerViewItemHolder ret = new AnnonceRecyclerViewItemHolder(annonceItemView);
        return ret;

    }

    @Override
    public void onBindViewHolder(@NonNull AnnonceRecyclerViewItemHolder holder, int position) {
        if(postItemList!=null){
            Post postItem= postItemList.get(position);
            holder.getDesciptionAnnonce().setText(postItem.getDescription());
            holder.getTitreAnnonce().setText(postItem.getTitre());
            Glide.with(context).load( postItem.getImagePath()).into(holder.getImageAnnonce());
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if(postItemList!=null)
        {
            ret = postItemList.size();
        }
        return ret;
    }


}
