package com.ariasaproject.myown.fragments;

import android.content.SharedPreferences;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ariasaproject.myown.R;

public class DashboardFragment extends Fragment {
		RecyclerView itemList_container;
		Adapter adpt;
		

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
        ViewGroup v = (ViewGroup)inflater.inflate(R.layout.fragment_dashboard, container, false);
        itemList_container = (RecyclerView) v.findViewById(R.id.itemViews);
        itemList_container.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adpt =
                new Adapter<DashboardItemHolder>() {
                    final LayoutInflater inflater = LayoutInflater.from(getActivity());

                    @Override
                    public DashboardItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        View itemView = inflater.inflate(R.layout.fragment_dashboard_item, parent, false);
                        return new DashboardItemHolder(itemView);
                    }

                    @Override
                    public void onBindViewHolder(DashboardItemHolder h, int p) {
                        
                    }

                    @Override
                    public int getItemCount() {
                        return 10;
                    }
                };
        itemList_container.setAdapter(adpt);
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
        
        
        
        //SharedPreferences data = getActivity().getPreferences(Context.MODE_PRIVATE);
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
    
    private class DashboardItemHolder extends RecyclerView.ViewHolder {
        public AppCompatImageView icn;
        public AppCompatTextView lb;

        public DashboardItemHolder(View itemView) {
            super(itemView);
            icn = (AppCompatImageView) itemView.findViewById(R.id.item_icon);
            lb = (AppCompatTextView) itemView.findViewById(R.id.item_label);
        }
    }
}