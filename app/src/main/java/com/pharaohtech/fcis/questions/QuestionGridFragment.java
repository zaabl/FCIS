package com.pharaohtech.fcis.questions;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.pharaohtech.fcis.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionGridFragment extends Fragment {

    private GridView gridView;
    private String[] subjects = {"Math", "OOP", "Ethics", "Algorithms", "AI", "Electronics"};
    int[] subjectImages = {R.drawable.math, R.drawable.oop, R.drawable.ethics, R.drawable.algo, R.drawable.ai,
    R.drawable.electronics};

    public QuestionGridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questionsGrid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = view.findViewById(R.id.subjectGridView);

        QuestionGridAdapter adapter = new QuestionGridAdapter(getContext(), subjects, subjectImages);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch(position)
//                {
//                    case 0:
//                        // Math fragment
//                        break;
//                    case 1:
//                        // OOp
//                        break;
//                    case 2:
//                        //Ethics
//                        break;
//                    case 3:
//                        //Algo
//                        break;
//                    case 4:
//                        // AI
//                        break;;
//                    case 5:
//                        //Electronics
//                        break;;
//                }

            }
        });
    }
}
