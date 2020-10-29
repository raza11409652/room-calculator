package com.droidtech.expensemanager.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.droidtech.expensemanager.R;
import com.droidtech.expensemanager.listener.OnNumberPadClick;
import com.droidtech.expensemanager.model.Number;
import com.droidtech.expensemanager.viewholder.NumberPadViewHolder;

import java.util.ArrayList;

public class NumberPadAdapter extends RecyclerView.Adapter<NumberPadViewHolder> {
    ArrayList<Number> list;
    Context context;
    OnNumberPadClick onNumberPadClick;

    public NumberPadAdapter(ArrayList<Number> list, Context context, OnNumberPadClick onNumberPadClick) {
        this.list = list;
        this.context = context;
        this.onNumberPadClick = onNumberPadClick;
    }

//    public NumberPadAdapter(ArrayList<Number> list, Context context) {
//        this.list = list;
//        this.context = context;
//    }

    @NonNull
    @Override
    public NumberPadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_number_item, parent, false);
        return new NumberPadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberPadViewHolder holder, final int position) {

        holder.item.setText(list.get(position).getValue());
        if (list.get(position).isDigit()) {
            holder.itemView.setBackground(context.getDrawable(R.drawable.digit_back));
        } else {
            holder.itemView.setBackground(context.getDrawable(R.drawable.non_digit_back));
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: " + list.get(position).isDigit());
                onNumberPadClick.onNumberClick(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("TAG", "getItemCount: " + list.size());
        return list.size();
    }
}
