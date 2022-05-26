package com.example.examen_03_progmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1, btn2, btn3, btn4;
    RegDeportivoDbAdapter regDeportivoDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        escuchaBotones();
        // Para obtener una vista de la bd aun si no se inserto un nuevo registro
        // Bien esto se puede hacer en 'VerRegistro", sin embargo, quise dejar algo para
        // ver como es el envio de objetos entre actividades
        regDeportivoDbAdapter = new RegDeportivoDbAdapter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:   // Nuevo reistro
                Intent intent = new Intent(v.getContext(), NuevoRegistro.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn2:   // Ver registro
                ArrayList<RegistroDeportivo> myList = regDeportivoDbAdapter.obtenRegistro();
                Intent intent2 = new Intent(v.getContext(), VerRegistro.class);
                intent2.putExtra("Lista", myList);
                startActivityForResult(intent2, 0);
                break;
            case R.id.btn3:   // Actualiza registro
                Intent intent3 = new Intent(v.getContext(), ActualizaRegistro.class);
                startActivityForResult(intent3, 0);
                break;
            case R.id.btn4:   // Elimina registro
                Intent intent4 = new Intent(v.getContext(), EliminaRegistro.class);
                startActivityForResult(intent4, 0);
                break;
            default:
                break;
        }
    }

    public void escuchaBotones() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

}
