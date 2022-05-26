package com.example.examen_03_progmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class ActualizaRegistro extends AppCompatActivity implements View.OnClickListener {
    RegDeportivoDbAdapter regDeportivoDbAdapter;
    RegistroDeportivo registro;
    private EditText nombreEquipo, nombreCapitan, telefonoCapitan, uniformeColor;;
    private Button btn9, btn10;
    public static String[] colores = {"Negro", "Rosa", "Azul claro", "Azul fuerte", "Morado", "Blanco", "Rojo", "Amarillo", "Gris", "Cafe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualiza_registro);
        escuchaBotones();
        obtieneRegistroInterfaz();
        AutoCompleteTextView opColor = (AutoCompleteTextView) findViewById(R.id.uniformeColor);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(ActualizaRegistro.this, android.R.layout.simple_dropdown_item_1line, colores);
        opColor.setAdapter(adaptador);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn9:   // Actualizo el registro de la bd
                if (validaRegistro()) {
                    actualizaRegistro();
                }
                break;
            case R.id.btn10:   // Regreso a menu
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                break;
            default:
                break;
        }
    }

    public void escuchaBotones() {
        btn9 = (Button) findViewById(R.id.btn9);
        btn10 = (Button) findViewById(R.id.btn10);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
    }

    public void obtieneRegistroInterfaz() {
        nombreEquipo = (EditText) findViewById(R.id.nombreEquipo);
        nombreCapitan = (EditText) findViewById(R.id.nombreCapitan);
        telefonoCapitan = (EditText) findViewById(R.id.telefonoCapitan);
        uniformeColor = (EditText) findViewById(R.id.uniformeColor);
    }

    public void actualizaRegistro() {
        regDeportivoDbAdapter = new RegDeportivoDbAdapter(this);
        registro = new RegistroDeportivo();
        registro.setNombreEquipo(nombreEquipo.getText().toString());
        registro.setNombreCapitan(nombreCapitan.getText().toString());
        registro.setTelefonoCapitan(telefonoCapitan.getText().toString());
        registro.setUniformeColor(uniformeColor.getText().toString());
        if (regDeportivoDbAdapter.modificaRegistro(registro) > 0) {
            Toast.makeText(this, "Registro actualizado con exito!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "No se pudo realizar la actualización", Toast.LENGTH_SHORT).show();
        }
        nombreEquipo.setText("");
        nombreCapitan.setText("");
        telefonoCapitan.setText("");
        uniformeColor.setText("");
    }

    public boolean validaEquipo(String equipo) {
        Pattern expReg = Pattern.compile("^[A-Za-z ]+$");
        if (!expReg.matcher(equipo).matches()) {
            this.nombreEquipo.setError("Nombre inválido");
            return false;
        }
        else {
            this.nombreEquipo.setError(null);
        }
        return true;
    }

    public boolean validaCapitan(String capitan) {
        Pattern expReg = Pattern.compile("^[A-Za-z ]+$");
        if (!expReg.matcher(capitan).matches()) {
            this.nombreCapitan.setError("Nombre inválido");
            return false;
        }
        else {
            this.nombreCapitan.setError(null);
        }
        return true;
    }

    public boolean validaColor(String color) {
        Pattern expReg = Pattern.compile("^[A-Za-z ]+$");
        if (!expReg.matcher(color).matches()) {
            this.uniformeColor.setError("Color inválido");
            return false;
        }
        else {
            this.uniformeColor.setError(null);
        }
        return true;
    }

    public boolean validaRegistro() {
        String equipo = nombreEquipo.getText().toString();
        String capitan = nombreCapitan.getText().toString();
        String color = uniformeColor.getText().toString();
        if (validaEquipo(equipo) && validaCapitan(capitan) && validaColor(color)) {
            return true;
        }
        else {
            return false;
        }
    }

}
