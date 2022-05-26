package com.example.examen_03_progmoviles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RegDeportivoDbAdapter {
    private RegDeportivoDbHelper registroDbHelper;
    RegDeportivoContract.RegistroEntry registroEntry = new RegDeportivoContract.RegistroEntry();

    public RegDeportivoDbAdapter(Context context) {
        registroDbHelper = new RegDeportivoDbHelper(context);
    }

    public long insertaRegistro(RegistroDeportivo registroDeportivo) {
        long id=0;
        try {
            // Se obtiene el repositorio de datos en modo escritura
            SQLiteDatabase db = registroDbHelper.getWritableDatabase();
            // Se crea un nuevo mapa de valores, donde los nombres de las columnas son las claves
            ContentValues cv = new ContentValues();
            cv.put(registroEntry.NOMBRE_EQUIPO, registroDeportivo.getNombreEquipo().toString());
            cv.put(registroEntry.NOMBRE_CAPITAN, registroDeportivo.getNombreCapitan().toString());
            cv.put(registroEntry.TELEFONO_CAPITAN, registroDeportivo.getTelefonoCapitan().toString());
            cv.put(registroEntry.CATEGORIA, registroDeportivo.getCategoria().toString());
            cv.put(registroEntry.UNIFORME_COLOR, registroDeportivo.getUniformeColor().toString());

            id = db.insert(RegDeportivoContract.RegistroEntry.TABLE_NAME, null, cv);
        } catch (Exception e) {
            e.toString();
        }
        return id;
    }

    public ArrayList<RegistroDeportivo> obtenRegistro() {
        SQLiteDatabase db = registroDbHelper.getWritableDatabase();
        String []columnas = {
                registroEntry.NOMBRE_EQUIPO,
                registroEntry.NOMBRE_CAPITAN,
                registroEntry.TELEFONO_CAPITAN,
                registroEntry.CATEGORIA,
                registroEntry.UNIFORME_COLOR,
        };

        Cursor cursor = db.query(registroEntry.TABLE_NAME, columnas, null, null,
                null, null, null);

        ArrayList<RegistroDeportivo> registroDeportivo = new ArrayList<RegistroDeportivo>();
        RegistroDeportivo registro;
        while (cursor.moveToNext()) {
            registro = new RegistroDeportivo();
            registro.setNombreEquipo(cursor.getString(cursor.getColumnIndexOrThrow(registroEntry.NOMBRE_EQUIPO)));
            registro.setNombreCapitan(cursor.getString(cursor.getColumnIndexOrThrow(registroEntry.NOMBRE_CAPITAN)));
            registro.setTelefonoCapitan(cursor.getString(cursor.getColumnIndexOrThrow(registroEntry.TELEFONO_CAPITAN)));
            registro.setCategoria(cursor.getString(cursor.getColumnIndexOrThrow(registroEntry.CATEGORIA)));
            registro.setUniformeColor(cursor.getString(cursor.getColumnIndexOrThrow(registroEntry.UNIFORME_COLOR)));
            registroDeportivo.add(registro);
        }
        cursor.close();
        return registroDeportivo;
    }

    public int modificaRegistro(RegistroDeportivo registroDeportivo) {
        SQLiteDatabase db = registroDbHelper.getWritableDatabase();
        int count = 0;
        try {
            // Nuevo valor para una columna
            String nombre = registroDeportivo.getNombreEquipo().toString();
            ContentValues cv = new ContentValues();
            cv.put(registroEntry.NOMBRE_CAPITAN, registroDeportivo.getNombreCapitan().toString());
            cv.put(registroEntry.TELEFONO_CAPITAN, registroDeportivo.getTelefonoCapitan().toString());
            cv.put(registroEntry.UNIFORME_COLOR, registroDeportivo.getUniformeColor().toString());

            // Fila a actualizar, según el título
            String selection = registroEntry.NOMBRE_EQUIPO + " LIKE ?";
            String[] selectionArgs = {nombre};

            // El valor que se muestra del método update() es la cantidad
            // de filas afectadas en la base de datos
            count = db.update(
                    registroEntry.TABLE_NAME,
                    cv,
                    selection,
                    selectionArgs);
        } catch (Exception e) {
            e.toString();
        }
        return count;
    }

    public boolean eliminaRegistro(String nombre) {
        SQLiteDatabase db = registroDbHelper.getWritableDatabase();
        boolean proceso = false;
        try {
            // Defina la parte 'dónde' se hará la consulta.
            String selection = registroEntry.NOMBRE_EQUIPO + " LIKE ?";
            // Se especifican los argumentos en el orden de los marcadores de la posición
            String[] selectionArgs = {nombre};
            // Se escribe la sentencia SQL
            int deletedRows = db.delete(registroEntry.TABLE_NAME, selection, selectionArgs);
            // El valor que se muestra del método delete() indica el número de filas que se
            // borraron de la base de datos.
            if (deletedRows > 0) {
                proceso = true;
            }
        } catch (Exception e) {
            e.toString();
        }
        return proceso;
    }

}
