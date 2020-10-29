package com.droidtech.expensemanager.abstarct;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.droidtech.expensemanager.daos.CategoryDao;
import com.droidtech.expensemanager.model.Category;

@Database(entities = Category.class, version = 1, exportSchema = false)
public abstract class DroidExpenseManagerDb extends RoomDatabase {

    private static final String dbName = "droid_em_db";
    private static DroidExpenseManagerDb instance;


    public static synchronized DroidExpenseManagerDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DroidExpenseManagerDb.class, dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    public  abstract CategoryDao categoryDao () ;
}
