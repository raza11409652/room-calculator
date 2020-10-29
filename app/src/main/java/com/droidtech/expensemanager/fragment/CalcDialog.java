package com.droidtech.expensemanager.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.droidtech.expensemanager.R;
import com.droidtech.expensemanager.activity.CategorySelector;
import com.droidtech.expensemanager.adapter.NumberPadAdapter;
import com.droidtech.expensemanager.listener.OnNumberPadClick;
import com.droidtech.expensemanager.model.Number;
import com.droidtech.expensemanager.utils.Constant;
import com.droidtech.expensemanager.utils.StringHandler;

import java.util.ArrayList;
import java.util.Stack;

public class CalcDialog extends AppCompatDialogFragment implements OnNumberPadClick {
    RecyclerView numberPad;
    ArrayList<Number> numbers = new ArrayList<>();
    NumberPadAdapter adapter;
    TextView expressionView;
    String expression = "";
    ImageButton editBack;
    Stack<String> expressionIndividual;
    String TAG = CalcDialog.class.getSimpleName();
    Button clear, equal, save;
    AppCompatTextView result;
    String res = null;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void setAdapter() {
        adapter = new NumberPadAdapter(numbers, getContext(), this);
        adapter.notifyDataSetChanged();
        numberPad.setAdapter(adapter);
        numberPad.setLayoutManager(new GridLayoutManager(getContext(), 4));

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.calc_dialog, null);
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        editBack = view.findViewById(R.id.clear_back);
        clear = view.findViewById(R.id.clear_all);
        equal = view.findViewById(R.id.equate);
        result = view.findViewById(R.id.result_view);
        save = view.findViewById(R.id.save);
        editBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: back clear");

                expressBackSlash();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " +res);
                if (res != null) {
                    Intent main = new Intent(getContext(), CategorySelector.class);
                    dialog.dismiss();
                    getActivity().startActivityForResult(main, Constant.ACT_CAT);
                }else{
                    return;
                }


            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Clear stack
                 */
                expressionIndividual.clear();
                calculateExpression();
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + expression);
                try {
                    res = StringHandler.evaluate(expression);
                    Log.d(TAG, "onClick: " + res);
                    Constant.totalResult = res;
                    result.setText(res);


//                   getFragmentManager().findFragmentByTag(Constant.CALC_FRAGMENT)
//                           .startActivityForResult(main , 120);
////                   getFragmentManager().popBackStack();


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "onClick: " + e.getMessage());
                }
            }
        });

        expressionIndividual = new Stack<>();
        expressionView = view.findViewById(R.id.expression);
        numberPad = view
                .findViewById(R.id.number_pad);
        numberPad.setHasFixedSize(true);

        numbers = new ArrayList<>();

        String pad[] = {
                "9", "8", "7", "/",
                "4", "5", "6", "x",
                "1", "2", "3", "-",
                "00", "0", "100", "+"

        };
//        Log.d("TAG ", "onCreateDialog: " + pad.length);
        for (int i = 0; i < pad.length; i++) {

            if (i == 3 || i == 7 || i == 11 || i == 15) {
                Number number = new Number(pad[i], false);
                numbers.add(number);
            } else {
                Number number = new Number(pad[i], true);
                numbers.add(number);
            }
//            Number number = new Number(pad[i] , true);
//            numbers.add(number) ;

        }
        setAdapter();


        return dialog;

    }

    private void expressBackSlash() {
        if (expressionIndividual.isEmpty() == false) {
            expressionIndividual.pop();
            calculateExpression();
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            dialog.getWindow().se;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onNumberClick(Number number) {
        Log.d("TAG ", "onNumberClick: " + number.getValue());
        expressionIndividual.push(number.getValue());
        calculateExpression();


    }

    public void calculateExpression() {
        expression = "";
        for (int i = 0; i < expressionIndividual.size(); i++) {
            expression = expression + expressionIndividual.elementAt(i);
        }
        Log.d(TAG, "calculateExpression: " + expressionIndividual);

        Log.d(TAG, "calculateExpression: " + expression);
        expressionView.setText(expression);

    }
}
