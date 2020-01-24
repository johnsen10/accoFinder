package com.example.senejean.logemoi.Splash;

import android.app.Application;

import com.example.senejean.logemoi.model.User;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SplashViewModel extends AndroidViewModel {

    private SplashRepository splashRepository;
    LiveData<User> isUserAuthenticatedLiveData;
    LiveData<User> userLiveData;

    public SplashViewModel(Application application) {
        super(application);
        splashRepository = new SplashRepository();
    }

    void checkIfUserIsAuthenticated() {
        isUserAuthenticatedLiveData = splashRepository.checkIfUserIsAuthenticatedInFirebase();
    }

    void setUid(String uid) {
        userLiveData = splashRepository.addUserToLiveData(uid);
    }
}
