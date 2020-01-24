package com.example.senejean.logemoi.Annonce;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.senejean.logemoi.R;
import com.example.senejean.logemoi.model.Post;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnnonceRecyclerViewItemHolder extends RecyclerView.ViewHolder {


    private TextView titreAnnonce=null;
    private TextView desciptionAnnonce=null;
    private ImageView imageAnnonce =null;
    private ImageButton imageButtonLike=null;

    public AnnonceRecyclerViewItemHolder(View itemView) {
        super(itemView);
        this.titreAnnonce = itemView.findViewById(R.id.my_text_view_titre_annonce);
        this.desciptionAnnonce = itemView.findViewById(R.id.my_text_view_description_annonce);
        this.imageAnnonce = itemView.findViewById(R.id.card_view_image_annonce);
        this.imageButtonLike=itemView.findViewById(R.id.like_button);
    }

    public ImageButton getImageButtonLike() {
        return imageButtonLike;
    }

    public void setImageButtonLike(ImageButton imageButtonLike) {
        this.imageButtonLike = imageButtonLike;
    }

    public TextView getTitreAnnonce() {
        return titreAnnonce;
    }

    public void setTitreAnnonce(TextView titreAnnonce) {
        this.titreAnnonce = titreAnnonce;
    }

    public TextView getDesciptionAnnonce() {
        return desciptionAnnonce;
    }

    public void setDesciptionAnnonce(TextView desciptionAnnonce) {
        this.desciptionAnnonce = desciptionAnnonce;
    }

    public ImageView getImageAnnonce() {
        return imageAnnonce;
    }

    public void setImageAnnonce(ImageView imageAnnonce) {
        this.imageAnnonce = imageAnnonce;
    }
}
