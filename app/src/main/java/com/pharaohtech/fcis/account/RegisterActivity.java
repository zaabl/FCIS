package com.pharaohtech.fcis.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pharaohtech.fcis.R;
import com.squareup.picasso.Picasso;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeBg();
        goTOLogin();
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
}
