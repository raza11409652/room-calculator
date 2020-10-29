package com.droidtech.expensemanager.listener;

import com.droidtech.expensemanager.model.Category;

public interface OnCategoryClick {
    void onLongPressCategory(Category category) ;
    void onClickCategory(Category category);
}
