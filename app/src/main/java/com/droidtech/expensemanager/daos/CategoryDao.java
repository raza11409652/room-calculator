package com.droidtech.expensemanager.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.droidtech.expensemanager.model.Category;

import java.util.List;
@Dao
public interface CategoryDao {

    @Query("Select * from Category")
    List<Category> getAllCategory();

    @Insert
    void insertCategory(Category category);

    @Update
    void updateCategory(Category category);

    @Delete
    void deleteCategory(Category category);

}
