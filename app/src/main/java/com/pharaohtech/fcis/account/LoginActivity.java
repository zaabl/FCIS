package com.pharaohtech.fcis.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pharaohtech.fcis.Features.Main;
import com.pharaohtech.fcis.R;
import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeBg();
        goToReg();
        goToForget();
        login();
    }

    private void login(){
        Button btnLogin = (Button) findViewById(R.id.logBtn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Main.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
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

}
