package com.example.senejean.logemoi.recherche;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.senejean.logemoi.R;


import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import static android.app.Activity.RESULT_OK;


public class RechercheFragment extends Fragment {

    private Button uploadBouton;
    private Button saveBouton;
    private ImageView imageView;
    EditText editTextDescription;
    private String textBoutonDescription;

    private Uri uriImagetosave;
    private Bitmap bitmapImagetosave;

    private RechercheViewModel rechercheViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       // return inflater.inflate(R.layout.fragment_recherche, container, false);

        View view= inflater.inflate(R.layout.fragment_recherche, container,false);
        uploadBouton=view.findViewById(R.id.uploadButton);
        saveBouton=view.findViewById(R.id.save_Button);
        imageView=view.findViewById(R.id.image);
        editTextDescription= view.findViewById(R.id.description_post);

        initRechercheViewModel();

        uploadBouton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                upload();}
        });

        saveBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textBoutonDescription= editTextDescription.getText().toString();
                rechercheViewModel.savePost(uriImagetosave,textBoutonDescription);
                Toast.makeText(getContext(), "image postée", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstancestate) {


        super.onActivityCreated(savedInstancestate);
    }

    public void upload(){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 1);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode == RESULT_OK ){
            Uri returnUri = data.getData();
            //on fait cette affectation redondante pour passer l'Uri a notre ViewModel afin denvoyer limage a firestore
            this.uriImagetosave=returnUri;
            Bitmap bitmapImage = null;
            try {
                bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmapImage);
            //this.bitmapImagetosave=bitmapImage;
        }


    }

    private void initRechercheViewModel(){
        rechercheViewModel= new ViewModelProvider(this).get(RechercheViewModel.class);
    }
}
