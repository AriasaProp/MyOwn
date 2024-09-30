package com.ariasaproject.myown.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.ariasaproject.myown.R;
import com.ariasaproject.myown.views.ShapeSelectorView;

public class ShapeFragment extends Fragment {
    private AppCompatButton btnSelect;
    private ShapeSelectorView shapeSelector;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_shape, container, false);
        shapeSelector = (ShapeSelectorView) v.findViewById(R.id.shapeSelector);
        btnSelect = (AppCompatButton) v.findViewById(R.id.btnSelect);
btnSelect.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(
                                        getContext(),
                                        "You selected: " + shapeSelector.getSelectedShape(),
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
