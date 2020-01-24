package com.example.senejean.logemoi.favori;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.senejean.logemoi.R;
import com.example.senejean.logemoi.model.Post;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriRecyclerViewDataAdapter extends RecyclerView.Adapter<FavoriRecyclerViewItemHolder> {
    private TextView descriptionAnnonceLike=null;
    private ImageView imageAnnonceLike =null;
    private  TextView titreAnnionceLike =null;
    List<Post> postLikeItemList;
    private Context context;

    public FavoriRecyclerViewDataAdapter(List<Post> postLikeItemList, Context context) {
        this.postLikeItemList = postLikeItemList;
        this.context = context;
    }

    @Override
    public FavoriRecyclerViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Get LayoutInflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View favoriItemView= layoutInflater.inflate(R.layout.favori_item, parent, false);

        FavoriRecyclerViewItemHolder itemFav= new FavoriRecyclerViewItemHolder(favoriItemView);
        return itemFav;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriRecyclerViewItemHolder holder, int position) {
        if(postLikeItemList!=null){
            Post postLikeItem= postLikeItemList.get(position);
            holder.getDescriptionAnnonceLike().setText(postLikeItem.getDescription());
            holder.getTitreAnnonceLike().setText(postLikeItem.getTitre());
            //Glide.with(context).load( postLikeItem.getImagePath()).into(holder.getImageAnnonceLike());


        }
    }


    @Override
    public int getItemCount() {
        int ret = 0;
        if(postLikeItemList!=null)
        {
            ret = postLikeItemList.size();
        }
        return ret;
    }
}
