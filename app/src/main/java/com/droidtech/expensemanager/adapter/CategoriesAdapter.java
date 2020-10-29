package com.droidtech.expensemanager.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.droidtech.expensemanager.R;
import com.droidtech.expensemanager.listener.OnCategoryClick;
import com.droidtech.expensemanager.model.Category;
import com.droidtech.expensemanager.viewholder.CategoriesViewHolder;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {
    List<Category> list;

    Context context;

    OnCategoryClick categoryClick;

    public CategoriesAdapter(List<Category> list, Context context, OnCategoryClick categoryClick) {
        this.list = list;
        this.context = context;
        this.categoryClick = categoryClick;
    }

    public CategoriesAdapter(List<Category> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_category, parent, false);
        return new CategoriesViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, final int position) {

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Log.d("TAG", "onLongClick: " + list.get(position).getType());
//                categoryClick.onLongPressCategory(list.get(position));
//                return true;
//            }
//        });
        String type = list.get(position).getType();
        Log.d("TAG", "onBindViewHolder: " + type);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClick.onClickCategory(list.get(position));
            }
        });
        holder.type.setText(list.get(position).getName());

        if (type.equals("ADD")) {
            holder.view.setBackgroundColor(context.getColor(R.color.green_theme));

        } else if (type.equals("SUB")) {
            holder.view.setBackgroundColor(context.getColor(R.color.colorAccent));


        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateCategories(List<Category> categories) {
        list = categories;
        notifyDataSetChanged();
    }
}
