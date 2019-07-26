package com.pharaohtech.fcis.account;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.pharaohtech.fcis.controls.FirebaseMethods;
import com.pharaohtech.fcis.features.Main;
import com.pharaohtech.fcis.R;
import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mProgressBar = findViewById(R.id.logProgressBar);
        mAuth = FirebaseAuth.getInstance();
        initializeBg();
        goToReg();
        goToForget();
        login();
    }

    //-------------------------------------------Background-----------------------------------------
    //==============================================================================================

    private void initializeBg(){
        ImageView mLoginBg = findViewById(R.id.loginBg);
        Picasso.get().load(R.drawable.back).into(mLoginBg);
    }

    //-------------------------------------------Navigation-----------------------------------------
    //==============================================================================================

    public void goToReg() {

        TextView goToRegBtn = (TextView) findViewById(R.id.goToRegBtn);
        goToRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void goToForget(){
        TextView goToForgetBtn = (TextView) findViewById(R.id.goToForget);
        goToForgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //--------------------------------------------Login---------------------------------------------
    //==============================================================================================

    private void login(){
        Button btnLogin = (Button) findViewById(R.id.logBtn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mMail = findViewById(R.id.logMail);
                EditText mPassword = findViewById(R.id.logPass);
                String email = mMail.getText().toString();
                String password = mPassword.getText().toString();

                if(isStringNull(email) && isStringNull(password)){

                    Toast.makeText(LoginActivity.this,R.string.loginToastFill,Toast.LENGTH_SHORT).show();
                }else{
                    mProgressBar.setVisibility(View.VISIBLE);
                    FirebaseMethods firebaseMethods = new FirebaseMethods(LoginActivity.this);
                    firebaseMethods.login(email, password);
                    mProgressBar.setVisibility(View.GONE);
                    if (mAuth.getCurrentUser() != null) {
                        Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, Main.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private boolean isStringNull(String string){

        if(string.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

}
