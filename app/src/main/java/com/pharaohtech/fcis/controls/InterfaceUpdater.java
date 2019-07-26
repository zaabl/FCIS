package com.pharaohtech.fcis.controls;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.pharaohtech.fcis.R;
import com.squareup.picasso.Picasso;

public class InterfaceUpdater {
//
//    public Activity activity;
//
//    public InterfaceUpdater(Activity activity) {
//        this.activity = activity;
//    }

    static public void updateDrawerWidget(String name, String email, String photo, TextView drawerName, TextView drawerEmail, CircularImageView drawerPic){
        drawerName.setText("hello");
        drawerEmail.setText(email);
        Picasso.get().load(photo).into(drawerPic);

    }

}
