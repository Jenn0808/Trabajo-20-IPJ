package com.example.trabajo20;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.jetbrains.annotations.Nullable;
public class DBHealper extends SQLiteOpenHelper{
    public  DBHealper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE recordatorio( tarea TEXT PRIMARY KEY,  descripcion TEXT, dia TEXT, mes TEXT, nota INTERGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS recordatorio");
        db.execSQL("CREATE TABLE recordatorio(registro TEXT PRIMARY KEY, descripcion TEXT, dia TEXT, mes TEXT, nota INTERGER)");
    }
}