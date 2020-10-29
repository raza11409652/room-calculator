package com.droidtech.expensemanager.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.droidtech.expensemanager.R;
import com.droidtech.expensemanager.abstarct.DroidExpenseManagerDb;
import com.droidtech.expensemanager.adapter.CategoriesAdapter;
import com.droidtech.expensemanager.model.Category;
import com.droidtech.expensemanager.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;


public class CategorySelector extends AppCompatActivity {
    Toolbar toolbar;
    DroidExpenseManagerDb instance;
    List<Category> categories = new ArrayList<>();
    String TAG = CategorySelector.class.getSimpleName();
    RecyclerView categoriesView;
    CategoriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selector);
        toolbar = findViewById(R.id.toolbar);
//        final Drawable upArrow = getResources().getDrawable(R.drawable.back_arrow);
//        upArrow.setColorFilter(Color.parseColor("#222222"), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("");
        instance = DroidExpenseManagerDb.getInstance(this);
        categoriesView = findViewById(R.id.category_list_view);
        categoriesView.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categories, this);
        categoriesView.setAdapter(adapter);
        categoriesView.setLayoutManager(new LinearLayoutManager(this));


        AppExecutors.getsInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                categories = instance.categoryDao().getAllCategory();
                Log.d(TAG, "run: " + categories.size());
                adapter.updateCategories(categories);

            }
        });

        //        Log.d(TAG, "onCreate: " + categories.size());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                //finish();
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}