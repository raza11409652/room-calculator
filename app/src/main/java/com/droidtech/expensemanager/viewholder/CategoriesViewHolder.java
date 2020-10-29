package com.droidtech.expensemanager.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.droidtech.expensemanager.R;

public class CategoriesViewHolder  extends RecyclerView.ViewHolder {
    public  TextView type ;
    public View view ;
    public CategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        type = itemView.findViewById(R.id.type);
        view = itemView.findViewById(R.id.color) ;
    }
}
