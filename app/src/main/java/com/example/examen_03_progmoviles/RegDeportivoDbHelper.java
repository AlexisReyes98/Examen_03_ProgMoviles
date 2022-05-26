package com.example.examen_03_progmoviles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RegDeportivoDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Registro.db";
    RegDeportivoContract.RegistroEntry registroEntry = new RegDeportivoContract.RegistroEntry();
    private final String CREATE_REGISTRO =
            "CREATE TABLE " + registroEntry.TABLE_NAME + " (" +
                    registroEntry.NOMBRE_EQUIPO + " TEXT PRIMARY KEY," +
                    registroEntry.NOMBRE_CAPITAN + " TEXT," +
                    registroEntry.TELEFONO_CAPITAN + " Text, " +
                    registroEntry.CATEGORIA + " TEXT, " +
                    registroEntry.UNIFORME_COLOR + " TEXT)";

    private final String DELETE_REGISTRO =
            "DROP TABLE IF EXISTS " + registroEntry.TABLE_NAME;

    public RegDeportivoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_REGISTRO);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_REGISTRO);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
