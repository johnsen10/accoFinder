package com.example.senejean.logemoi.Auth;

import android.app.Application;

import com.example.senejean.logemoi.model.User;
import com.google.firebase.auth.AuthCredential;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AuthViewModel extends AndroidViewModel {

    private AuthRepository authRepository;
    LiveData<User> authenticatedUserLiveData;
    LiveData<User> createdUserLiveData;

    public AuthViewModel(Application application) {
        super(application);
        authRepository = new AuthRepository();
    }

    void signInWithGoogle(AuthCredential googleAuthCredential) {
        authenticatedUserLiveData = authRepository.firebaseSignInWithGoogle(googleAuthCredential);
    }

    void createUser(User authenticatedUser) {
        createdUserLiveData = authRepository.createUserInFirestoreIfNotExists(authenticatedUser);
    }
}
