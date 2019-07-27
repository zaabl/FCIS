package com.pharaohtech.fcis.questions;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.pharaohtech.fcis.R;
import com.pharaohtech.fcis.announcements.AddPostFragment;
import com.pharaohtech.fcis.announcements.PostsAdapter;
import com.pharaohtech.fcis.models.Post;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionListFragment extends Fragment {


    FloatingActionButton floatingActionButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference questionListRef;
    private PostsAdapter adapter;

    public QuestionListFragment(String subject) {
        // Required empty public constructor
        questionListRef = db.collection(subject);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView(view);

        floatingActionButton =
                (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        showActionButton();
        addAnnouncement(view);
    }

    private void setUpRecyclerView(View view) {
        Query query = questionListRef.orderBy("timestamp", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Post> options = new FirestoreRecyclerOptions.Builder<Post>()
                .setQuery(query, Post.class)
                .build();

        adapter = new PostsAdapter(options);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewHome);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
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

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionButton.hide();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Fragment fragment = new AddPostFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.mainFrame, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }, 150);

            }
        });
    }

}
