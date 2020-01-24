package com.example.senejean.logemoi.favori;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.senejean.logemoi.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriRecyclerViewItemHolder extends RecyclerView.ViewHolder {
    private TextView titreAnnonceLike =null;
    private TextView descriptionAnnonceLike =null;
    private ImageView imageAnnonceLike =null;

    public FavoriRecyclerViewItemHolder(@NonNull View itemView) {
        super(itemView);
        this.descriptionAnnonceLike= itemView.findViewById(R.id.description_annonce_like);
        this.titreAnnonceLike =itemView.findViewById(R.id.titre_annonce_like);
        this.imageAnnonceLike=itemView.findViewById(R.id.image_like);
    }

    public TextView getTitreAnnonceLike() {
        return titreAnnonceLike;
    }

    public void setTitreAnnonceLike(TextView titreAnnonceLike) {
        this.titreAnnonceLike = titreAnnonceLike;
    }

    public TextView getDescriptionAnnonceLike() {
        return descriptionAnnonceLike;
    }

    public void setDescriptionAnnonceLike(TextView descriptionAnnonceLike) {
        this.descriptionAnnonceLike = descriptionAnnonceLike;
    }

    public ImageView getImageAnnonceLike() {
        return imageAnnonceLike;
    }

    public void setImageAnnonceLike(ImageView imageAnnonceLike) {
        this.imageAnnonceLike = imageAnnonceLike;
    }
}
