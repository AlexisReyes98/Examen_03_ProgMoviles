package com.example.examen_03_progmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class EliminaRegistro extends AppCompatActivity implements View.OnClickListener {
    RegDeportivoDbAdapter regDeportivoDbAdapter;
    private EditText nombreEquipo;
    private Button btn11, btn12;
    private TextView textView13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elimina_registro);
        escuchaBotones();
        obtieneRegistroInterfaz();
        regDeportivoDbAdapter = new RegDeportivoDbAdapter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn11:   // Elimino registro
                if (validaEquipo()) {
                    eliminaRegistro();
                }
                break;
            case R.id.btn12:   // Regreso a menu
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                break;
            default:
                break;
        }
    }

    public void escuchaBotones() {
        btn11 = (Button) findViewById(R.id.btn11);
        btn12 = (Button) findViewById(R.id.btn12);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
    }

    public void obtieneRegistroInterfaz() {
        nombreEquipo = (EditText) findViewById(R.id.nombreEquipo);
        textView13 = (TextView) findViewById(R.id.textView13);
    }

    public void eliminaRegistro() {
        String nombre = nombreEquipo.getText().toString();
        if (regDeportivoDbAdapter.eliminaRegistro(nombre)) {
            textView13.setText("Equipo ( "+nombre+" ) eliminado con éxito!");
        }
        else {
            textView13.setText("No se pudo eliminar el equipo :(");
        }
        nombreEquipo.setText("");
    }

    public boolean validaEquipo() {
        String equipo = nombreEquipo.getText().toString();
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

}
