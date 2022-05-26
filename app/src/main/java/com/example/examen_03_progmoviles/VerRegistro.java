package com.example.examen_03_progmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class VerRegistro extends AppCompatActivity {
    private ListView lista;
    private ArrayList<RegistroDeportivo> myList;
    //ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_registro);
        compInterfaz();
        myList = (ArrayList<RegistroDeportivo>) getIntent().getSerializableExtra("Lista");
        Button btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myList.isEmpty() == true) {
                    listaVacia();
                }
                else  {
                    imprimeLista();
                }
            }
        });
        regresaMain();
    }

    public void compInterfaz() {
        lista = (ListView) findViewById(R.id.lista);
    }

    public void imprimeLista() {
        //listAdapter = new ListAdapter(this, R.layout.item_row, myList);
        // Para poder cargar los datos en el ListView se tiene que definir un objeto de tipo ArrayAdapter
        // El cual debe coincidir con el tipo de dato de la lista original, que es de tipo RegistroDeportivo
        // En los parentesis se coloca el contexto actual que es VerRegistro
        // Seguido por el tipo de lista que se va a presentar, en este caso una lista simple
        // y al final se pasa la lista original
        ArrayAdapter<RegistroDeportivo> adaptador = new ArrayAdapter<RegistroDeportivo>(VerRegistro.this, android.R.layout.simple_list_item_1, myList);
        // El adaptador es lo que permitira pasar el contenido al ListView
        lista.setAdapter(adaptador);
    }

    public void listaVacia() {
        Toast.makeText(this, "La lista actual esta vacía...", Toast.LENGTH_SHORT).show();
    }

    // Para digamos practicar, implemente una clase que referencia a la interfaz View.OnClickListener
    // Como se vio en clase para manejar Eventos
    public void regresaMain() {
        Button btn8 = (Button) findViewById(R.id.btn8);
        btn8.setOnClickListener(new MiClick());
    }

    class MiClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivityForResult(intent, 0);
        }
    }

}
