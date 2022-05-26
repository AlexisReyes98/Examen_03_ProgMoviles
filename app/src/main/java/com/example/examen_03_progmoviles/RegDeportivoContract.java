package com.example.examen_03_progmoviles;

import android.provider.BaseColumns;

public final class RegDeportivoContract {
    // Constructor privado para evitar la instancia de la clase contrato
    private RegDeportivoContract() { }

    public static class RegistroEntry implements BaseColumns {
        public static final String TABLE_NAME = "Registro";
        public static final String NOMBRE_EQUIPO = "nombreEquipo";
        public static final String NOMBRE_CAPITAN = "nombreCapitan";
        public static final String TELEFONO_CAPITAN = "telefonoCapitan";
        public static final String CATEGORIA = "categoria";
        public static final String UNIFORME_COLOR = "uniformeColor";
    }

}
