package com.pharaohtech.fcis.announcements;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.pharaohtech.fcis.R;
import com.pharaohtech.fcis.controls.FirebaseMethods;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPostFragment extends Fragment {

    private FloatingActionButton floatingActionButton;

    public AddPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_announcement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initwidgets(view);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.addFloatingActionButton);
        showActionButton();
        addAnnouncement(view);
    }

    private void initwidgets(View view){
        CircularImageView image = view.findViewById(R.id.addAnnImage);
        TextView name = view.findViewById(R.id.addAnnName);
        Picasso.get().load(FirebaseMethods.loginUser.getProfile_photo()).into(image);
        name.setText(FirebaseMethods.loginUser.getDisplay_name());
    }

    private void showActionButton(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                floatingActionButton.show();
            }
        }, 150);
    }

    private void addAnnouncement(View view){
        final FloatingActionButton floatingActionButton =
                (FloatingActionButton) view.findViewById(R.id.addFloatingActionButton);
        final EditText captionText = view.findViewById(R.id.addAnnCaption);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionButton.hide();
                FirebaseMethods firebaseMethods = new FirebaseMethods(getContext());
                firebaseMethods.post(FirebaseMethods.loginUser.getDisplay_name(), FirebaseMethods.loginUser.getProfile_photo(), captionText.getText().toString(), FirebaseMethods.loginUser.getUser_id(), System.currentTimeMillis());
                //Delay for animation
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                }, 150);
            }
        });
    }
}
