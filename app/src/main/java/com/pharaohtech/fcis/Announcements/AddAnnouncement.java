package com.pharaohtech.fcis.Announcements;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pharaohtech.fcis.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAnnouncement extends Fragment {

    private FloatingActionButton floatingActionButton;

    public AddAnnouncement() {
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
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.addFloatingActionButton);
        showActionButton();
        addAnnouncement(view);
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

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Posted Successfuly", Toast.LENGTH_SHORT).show();
                floatingActionButton.hide();
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
