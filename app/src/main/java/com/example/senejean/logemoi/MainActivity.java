package com.example.senejean.logemoi;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.senejean.logemoi.Auth.AuthActivity;
import com.example.senejean.logemoi.model.User;
import com.example.senejean.logemoi.Annonce.AnnonceFragment;
import com.example.senejean.logemoi.discussion.Discussionsfragment;
import com.example.senejean.logemoi.favori.FavorisFragment;
import com.example.senejean.logemoi.recherche.RechercheFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import static com.example.senejean.logemoi.utilis.Constants.USER;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private GoogleSignInClient googleSignInClient;

    final Fragment fragmentAnnonce = new AnnonceFragment();
    final Fragment fragmentFavoris = new FavorisFragment();
    final Fragment fragmentDiscussions = new Discussionsfragment();
    final Fragment fragmentRecherche = new RechercheFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentAnnonce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_activity_test);
        User user = getUserFromIntent();
        initGoogleSignInClient();

        //final AnnonceFragment mainFragment= new AnnonceFragment();
        //loadFragment(new AnnonceFragment());

        fm.beginTransaction().add(R.id.fragment_container, fragmentRecherche, "4").hide(fragmentRecherche).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragmentDiscussions, "3").hide(fragmentDiscussions).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragmentFavoris, "2").hide(fragmentFavoris).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragmentAnnonce, "1").hide(fragmentAnnonce).commit();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            // Fragment fragment =null;
            switch (item.getItemId()) {
                case R.id.action_annonce:
                    // Toast.makeText(MainActivity.this, "Annonces", Toast.LENGTH_SHORT).show();
                    //fragment= new AnnonceFragment();
                    //fragLoader(fragment);
                    fm.beginTransaction().hide(active).show(fragmentAnnonce).commit();
                    active = fragmentAnnonce;
                    return true;
                case R.id.action_enregistre:
                    // Toast.makeText(MainActivity.this, "Elt enregistres", Toast.LENGTH_SHORT).show();
                    //fragment=new FavorisFragment();
                    //fragLoader(fragment);
                    fm.beginTransaction().hide(active).show(fragmentFavoris).commit();
                    active = fragmentFavoris;
                    return true;
                case R.id.action_message:
                    //Toast.makeText(MainActivity.this, "Discussions", Toast.LENGTH_SHORT).show();
                    //fragment=new Discussionsfragment();
                    //fragLoader(fragment);
                    fm.beginTransaction().hide(active).show(fragmentDiscussions).commit();
                    active = fragmentDiscussions;
                    return true;
                case R.id.action_recherche:
                    //Toast.makeText(MainActivity.this, "Recheches", Toast.LENGTH_SHORT).show();
                    //fragment=new RechercheFragment();
                    //fragLoader(fragment);
                    fm.beginTransaction().hide(active).show(fragmentRecherche).commit();
                    active = fragmentRecherche;
                    return true;
            }
            return true;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private User getUserFromIntent() {
        return (User) getIntent().getSerializableExtra(USER);
    }

    private void initGoogleSignInClient() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    /*    private void initMessageTextView() {
            messageTextView = findViewById(R.id.message_text_view);
        }

        private void setMessageToMessageTextView(User user) {
            String message = "You are logged in as: " + user.name;
            messageTextView.setText(message);
        }
    */
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            goToAuthInActivity();
        }
    }

    private void goToAuthInActivity() {
        Intent intent = new Intent(MainActivity.this, AuthActivity.class);
        startActivity(intent);
    }

    private void signOut() {
        singOutFirebase();
        signOutGoogle();
    }

    private void singOutFirebase() {
        firebaseAuth.signOut();
    }

    private void signOutGoogle() {
        googleSignInClient.signOut();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.sign_out_button) {
            signOut();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}