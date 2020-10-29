package com.droidtech.expensemanager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.droidtech.expensemanager.R;
import com.droidtech.expensemanager.activity.CategoryManager;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;


public class BottomNavDrawer extends BottomSheetDialogFragment {
    NavigationView bottomNav;

    String TAG = BottomNavDrawer.class.getSimpleName();


    public BottomNavDrawer() {
        // Required empty public constructor
    }

    public static BottomNavDrawer newInstance() {
        return new BottomNavDrawer();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNav = view.findViewById(R.id.nav_menu);


        bottomNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.category:
                        Log.d(TAG, "onNavigationItemSelected: category selected");
//                        getFragmentManager()
//                        getFragmentManager().

                        Intent intent = new Intent(getContext(), CategoryManager.class);
                        updateUi(intent);
                        break;
                    case R.id.trash:
                        Log.d(TAG, "onNavigationItemSelected: trash clicked");
                        break;
                }
                return false;
            }
        });
    }

    private void updateUi(Intent intent) {
        startActivity(intent);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_nav_drawer, container, false);
    }
}