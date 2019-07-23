package com.pharaohtech.fcis.account;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.pharaohtech.fcis.R;
import com.squareup.picasso.Picasso;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initializeBg();
    }



    //-------------------------------------------Background-----------------------------------------
    //==============================================================================================

    private void initializeBg(){
        ImageView forgetBg = findViewById(R.id.forgetBg);
        Picasso.get().load(R.drawable.back).into(forgetBg);
    }
}
