package com.droidtech.expensemanager.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.droidtech.expensemanager.R;

public class NumberPadViewHolder extends RecyclerView.ViewHolder {
  public   TextView item ;
    public NumberPadViewHolder(@NonNull View itemView) {
        super(itemView);
        item = itemView.findViewById(R.id.item);
    }
}
