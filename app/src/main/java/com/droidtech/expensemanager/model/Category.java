package com.droidtech.expensemanager.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Category")
public class Category {

    @PrimaryKey(autoGenerate = true)
    private  int id  ;
    @ColumnInfo(name = "name")
    private  String name ;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "icon")
    private String icon ;

    public Category( String name, String type, String icon) {
//        this.id = id;
        this.name = name;
        this.type = type;
        this.icon = icon;
    }


//    @Ignore
//    public Category(int id, String type, String icon) {
//        this.id = id;
//        this.type = type;
//        this.icon = icon;
//    }

    @Ignore
    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
