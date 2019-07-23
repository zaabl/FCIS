package com.pharaohtech.fcis.account;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.pharaohtech.fcis.controls.FirebaseMethods;
import com.pharaohtech.fcis.models.User;
import com.pharaohtech.fcis.R;
import com.squareup.picasso.Picasso;

public class RegisterActivity extends AppCompatActivity {

    private int PReqCode = 1;
    private int REQUESCODE = 1;
    private Uri pickedImgUri;
    private CircularImageView imgUserPhoto;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mProgressBar = (ProgressBar) findViewById(R.id.regProgressBar);
        mProgressBar.setVisibility(View.GONE);
        initializeBg();
        goTOLogin();
        onImageClick();
        registerClick();
    }

    //-------------------------------------------Background-----------------------------------------
    //==============================================================================================

    private void initializeBg(){
        ImageView mLoginBg = findViewById(R.id.registerBg);
        Picasso.get().load(R.drawable.back).into(mLoginBg);
    }

    //-------------------------------------------Navigation-----------------------------------------
    //==============================================================================================

    public void goTOLogin() {

        TextView goToLoginBtn = (TextView) findViewById(R.id.goToLogInBtn);
        goToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

    }

    //-------------------------------------------Registering----------------------------------------
    //==============================================================================================

    private void registerClick(){
        final EditText mEmail = (EditText) findViewById(R.id.regMail);
        final EditText mPassword = (EditText) findViewById(R.id.regPassword);
        final EditText mpassword2 = (EditText) findViewById(R.id.regPasswordConfirm);
        final EditText mName = (EditText) findViewById(R.id.regName);
        final EditText mSeatNumber = (EditText) findViewById(R.id.regSeatNum);
        Button btnRegister = (Button) findViewById(R.id.regBtn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String password2 = mpassword2.getText().toString();
                String name = mName.getText().toString();
                String seatNumber = mSeatNumber.getText().toString();
                if(checkInputs(email, password, password2,name, seatNumber) && checkPasswordConfirm(password, password2) && checkProfilePhoto(pickedImgUri)){
                    mProgressBar.setVisibility(View.VISIBLE);
//                    onDataChangeAuth = true;
                    User user = new User("toBe", email, name, "toBe", "user", seatNumber);
                    FirebaseMethods firebaseMethods = new FirebaseMethods(RegisterActivity.this);
                    firebaseMethods.registerNewEmail(user, password, ((BitmapDrawable) imgUserPhoto.getDrawable()).getBitmap());
                    finish();
                }
            }
        });
    }

    //-------------------------------------------Checking-------------------------------------------
    //==============================================================================================

    private boolean checkPasswordConfirm(String password, String password2){
        if(!password.equals(password2)){
            Toast.makeText(RegisterActivity.this, R.string.registerToastPasswordMatch, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkInputs(String email, String password, String password2, String username, String seatNumber){
        if(email.equals("") || password.equals("") || password2.equals("")|| seatNumber.equals("")|| username.equals("")){
            Toast.makeText(RegisterActivity.this, R.string.registerToastEmpty, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean checkProfilePhoto(Uri image){
        if(image == null){
            Toast.makeText(RegisterActivity.this, R.string.registerToastPhoto, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //-------------------------------------------ProfilePicture-------------------------------------
    //==============================================================================================

    private void onImageClick(){
        imgUserPhoto = findViewById(R.id.regUserPhoto);

        imgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 22) {


                    checkAndRequestForPermission();


                } else {

                    openGallery();

                }


            }
        });
    }

    private void openGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESCODE);
    }

    private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(RegisterActivity.this.getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(RegisterActivity.this, R.string.registerPermission, Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);
            }

        } else {
            openGallery();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {
            pickedImgUri = data.getData();
            Picasso.get().load(pickedImgUri).resize(0,512).into(imgUserPhoto);


        }
    }
}
