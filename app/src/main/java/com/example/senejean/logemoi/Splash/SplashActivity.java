package com.example.senejean.logemoi.Splash;

import android.content.Intent;
import android.os.Bundle;

import com.example.senejean.logemoi.Auth.AuthActivity;
import com.example.senejean.logemoi.MainActivity;
import com.example.senejean.logemoi.model.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import static com.example.senejean.logemoi.utilis.Constants.USER;

public class SplashActivity extends AppCompatActivity {

    SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSplashViewModel();
        checkIfUserIsAuthenticated();
    }

    private void initSplashViewModel() {
        splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
    }

    private void checkIfUserIsAuthenticated() {
        splashViewModel.checkIfUserIsAuthenticated();
        splashViewModel.isUserAuthenticatedLiveData.observe(this, user -> {
            if (!user.isAuthenticated) {
                goToAuthInActivity();
                finish();
            } else {
                getUserFromDatabase(user.uid);
            }
        });
    }

    private void goToAuthInActivity() {
        Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
        startActivity(intent);
    }

    private void getUserFromDatabase(String uid) {
        splashViewModel.setUid(uid);
        splashViewModel.userLiveData.observe(this, user -> {
            goToMainActivity(user);
            finish();
        });
    }

    private void goToMainActivity(User user) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.putExtra(USER, user);
        startActivity(intent);
    }
}
