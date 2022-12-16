package com.example.chatapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.chatapp.outils.MySQliteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

public class LocalData {
    private MySQliteOpenHelper accDB;
    private String baseName ="chatBD1";
    private int version = 1 ;
    private SQLiteDatabase db ;
    private String tableName;

    LocalData(String mytableName , Context context){
     accDB = new MySQliteOpenHelper(mytableName,context,baseName,null,version);
     this.tableName=mytableName;
    }

    public void insertMessage(Message message){
        db=accDB.getWritableDatabase();
        String query="insert into "+tableName+"(messageDate, name , message)values (\""+message.getDate()+"\",\""+message.getName()+"\",\""+message.getMsg()+"\")";
        db.execSQL(query);
    }

    public ArrayList<Message> getMessages(){
        db=accDB.getReadableDatabase();

        String req = "select * from "+tableName;
        Cursor cursor=db.rawQuery(req,null);
        cursor.moveToFirst();
        ArrayList<Message> msg = new ArrayList<Message>();
        if(!cursor.isBeforeFirst()){
            while (!cursor.isAfterLast()){
                msg.add(new Message(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
                cursor.moveToNext();
            }
        }

        return msg;
    }



}
