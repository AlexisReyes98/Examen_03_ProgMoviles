package com.example.examen_03_progmoviles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<RegistroDeportivo> {
    private ArrayList<RegistroDeportivo> myList;
    private Context context;
    private int resource;

    public ListAdapter(@NonNull Context context, int resource, ArrayList<RegistroDeportivo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.myList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(resource, null);
        }
        RegistroDeportivo registro = myList.get(position);

        ImageView imagen = view.findViewById(R.id.img);
        //imagen.setImageResource(registro.getImage());

        TextView texto = view.findViewById(R.id.textView14);
        texto.setText(registro.toString());

        return view;
    }
}
