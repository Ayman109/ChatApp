package com.example.chatapp.outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQliteOpenHelper extends SQLiteOpenHelper {
    String query;
    public MySQliteOpenHelper(String tableName,@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        query="CREATE TABLE "+tableName+"(messageDate TEXT PRIMARY KEY , name TEXT , message TEXT)";

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    onCreate(sqLiteDatabase);
    }
}
