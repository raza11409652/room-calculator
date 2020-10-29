package com.droidtech.expensemanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.droidtech.expensemanager.R;
import com.droidtech.expensemanager.abstarct.DroidExpenseManagerDb;
import com.droidtech.expensemanager.fragment.BottomNavDrawer;
import com.droidtech.expensemanager.fragment.CalcDialog;
import com.droidtech.expensemanager.model.Category;
import com.droidtech.expensemanager.utils.AppExecutors;
import com.droidtech.expensemanager.utils.Constant;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Home extends AppCompatActivity {
    BottomAppBar bottomAppBar;
    String TAG = Home.class.getSimpleName();
    CoordinatorLayout parent;

    BottomSheetDialogFragment navMenu;
    FloatingActionButton addNewTransaction;


    CalcDialog dialog;
    DroidExpenseManagerDb instance ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /**
         * Todo : init views
         */
        bottomAppBar = findViewById(R.id.bottom_app_bar);

        addNewTransaction = findViewById(R.id.fab);

        parent = findViewById(R.id.parent);
        navMenu = BottomNavDrawer.newInstance();


        dialog = new CalcDialog();
//        dialog.show(getSupportFragmentManager() , "");


        bottomAppBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG, "onClick:  menu Item clicked");
                navMenu.show(getSupportFragmentManager(), TAG);
            }
        });

        addNewTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(getSupportFragmentManager(), Constant.CALC_FRAGMENT);
            }
        });

//        instance = DroidExpenseManagerDb.getInstance(this);
//        AppExecutors.getsInstance().diskIO().execute(new Runnable() {
//            @Override
//            public void run() {
//                instance.categoryDao().insertCategory(new Category("Travel" , "icon"));
//            }
//        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: " + resultCode);
        Log.d(TAG, "onActivityResult: " + requestCode);
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constant.ACT_CAT:
                    Log.d(TAG, "onActivityResult: " + data);

                    break;
            }


        } else {

            Log.d(TAG, "onActivityResult: process is terminated by user");
        }

    }
}