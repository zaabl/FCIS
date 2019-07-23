package com.pharaohtech.fcis.controls;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pharaohtech.fcis.models.User;
import com.pharaohtech.fcis.R;

import java.io.ByteArrayOutputStream;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FirebaseMethods {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public String FIREBASE_IMAGE_STORAGE = "photos/users/";
    private Context mContext;
    FirebaseFirestore fireStoreDB = FirebaseFirestore.getInstance();
    private String userID;
    private StorageReference mStorageReference;

    public FirebaseMethods(Context context) {
        mAuth = FirebaseAuth.getInstance();
        mStorageReference = FirebaseStorage.getInstance().getReference();

        mContext = context;

        if (mAuth.getCurrentUser() != null) {
            userID = mAuth.getCurrentUser().getUid();
        }

    }

    //-------------------------------------------Registration---------------------------------------
    //==============================================================================================

    public void registerNewEmail(final User user, String password, final Bitmap image) {
        Log.d(TAG, "registerNewEmail: " + user.getEmail() + "   " + password);
        mAuth.createUserWithEmailAndPassword(user.getEmail(), password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(mContext, "Please re-check your data",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            Log.d(TAG, "onComplete: " + e.getMessage());


                        } else if (task.isSuccessful()) {
                            sendVerificationEmail();
                            userID = mAuth.getCurrentUser().getUid();
                            user.setUser_id(userID);
                            uploadNewProfilePhoto(image, user);
                        }

                    }
                });
    }

    private void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                            } else {
                                Toast.makeText(mContext, "couldn't send verification email.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void addNewUser(User user, String profilePic){
        user.setProfile_photo(profilePic);
        fireStoreDB.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(mContext, R.string.registerToastSignup, Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                    }
                });
    }

    //-------------------------------------------Login----------------------------------------------
    //==============================================================================================

    public void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user.isEmailVerified()){
                                Log.d(TAG, "onComplete: success. email is verified.");
                            }else{
                                Toast.makeText(mContext, R.string.loginToastVerified, Toast.LENGTH_SHORT).show();
                                mAuth.signOut();
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(mContext, R.string.loginToastFailed, Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    //------------------------------------UploadingProfilePhoto-------------------------------------
    //==============================================================================================

    private void uploadNewProfilePhoto(Bitmap image,  final User user) {
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final StorageReference storageReference = mStorageReference
                .child(FIREBASE_IMAGE_STORAGE + "/" + user_id + "/profile_photo" + "/" + System.currentTimeMillis());
        byte[] bytes = getBytesFromBitmap(image, 75);

        storageReference.putBytes(bytes).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) {
                return storageReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Uri downloadUri = task.getResult();
                addNewUser(user, downloadUri.toString());
            }
        });
    }

    private static byte[] getBytesFromBitmap(Bitmap bm, int quality){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, quality, stream);
        return stream.toByteArray();
    }

}
