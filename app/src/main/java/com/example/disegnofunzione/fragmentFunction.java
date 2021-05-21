package com.example.disegnofunzione;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentFunction#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentFunction extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "fx";
    private static final String ARG_PARAM2 = "color";

    // TODO: Rename and change types of parameters
    private String fx;
    private String color;

    public fragmentFunction() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param fx Parameter 1.
     * @param color Parameter 2.
     * @return A new instance of fragment fragmentFunction.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmentFunction newInstance(String fx, String color) {
        fragmentFunction fragment = new fragmentFunction();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, fx);
        args.putString(ARG_PARAM2, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fx = getArguments().getString(ARG_PARAM1);
            color = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_function, container, false);
    }
}