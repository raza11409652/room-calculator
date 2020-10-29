package com.droidtech.expensemanager.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.droidtech.expensemanager.R;
import com.droidtech.expensemanager.abstarct.DroidExpenseManagerDb;
import com.droidtech.expensemanager.adapter.CategoriesAdapter;
import com.droidtech.expensemanager.listener.OnCategoryClick;
import com.droidtech.expensemanager.model.Category;
import com.droidtech.expensemanager.utils.AppExecutors;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CategoryManager extends AppCompatActivity implements OnCategoryClick {

    DroidExpenseManagerDb instance;
    //    AppExecutors appExecutors;
    RecyclerView categoriesView;
    CategoriesAdapter adapter;
    List<Category> list = new ArrayList<>();

    String TAG = CategoryManager.class.getSimpleName();
    FloatingActionButton addNew;
    RadioGroup typeSelection;
    BottomSheetDialog sheetDialog;
    String type = null;
    EditText categoryName ;
    Button save ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_manager);
        instance = DroidExpenseManagerDb.getInstance(this);
        categoriesView = findViewById(R.id.categories);
        adapter = new CategoriesAdapter(list, this, this);
        categoriesView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        categoriesView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());

        categoriesView.addItemDecoration(dividerItemDecoration);

      getCategories();

        addNew = findViewById(R.id.new_category);

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetDialog = new BottomSheetDialog(CategoryManager.this);
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.new_category_layout, null);
                typeSelection = view.findViewById(R.id.selection_type);
                categoryName = view.findViewById(R.id.type) ;
                save = view.findViewById(R.id.save) ;
                typeSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.expense:
//                                Log.d(TAG, "onCheckedChanged: expense selected");
                                type = "SUB";
                                break;
                            case R.id.income:
//                                Log.d(TAG, "onCheckedChanged: income selected");
                                type = "ADD";
                                break;
                        }
                    }
                });
                sheetDialog.setContentView(view);
                sheetDialog.show();

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name =categoryName.getText().toString() ;
                        if (TextUtils.isEmpty(name)){
                            return;
                        }
                        if (type == null){
                            return;
                        }
                        addNewCategory(name , type );
                    }
                });


            }
        });


    }

    private void getCategories() {
        AppExecutors.getsInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                list = instance.categoryDao().getAllCategory();
                adapter.updateCategories(list);
            }
        });
    }

    private void addNewCategory(final String name, final String type) {
        AppExecutors.getsInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                instance.categoryDao().insertCategory(new Category(name ,type , "Icon"));
                sheetDialog.dismiss();
//
//                getCategories();
            }
        });

    }

    @Override
    public void onLongPressCategory(Category category) {
        //        Log.d(TAG, "onLongPressCategory: " + category.getType());

    }

    @Override
    public void onClickCategory(final Category category) {
        Log.d(TAG, "onClickCategory: " + category.getType());
        AppExecutors.getsInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                instance.categoryDao().deleteCategory(category);
            }
        });

    }
}