package com.example.dmorales.silabusprueba;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dmorales.silabusprueba.model.Curso;

public class CursoHolder extends RecyclerView.ViewHolder{
    TextView txtNombre;
    TextView txtTipo;
    TextView txtProfesor;

    public CursoHolder(View itemView) {
        super(itemView);
        txtNombre = (TextView) itemView.findViewById(R.id.item_curso_txtNombre);
        txtTipo = (TextView) itemView.findViewById(R.id.item_curso_txtTipo);
        txtProfesor = (TextView) itemView.findViewById(R.id.item_curso_txtProfesor);
    }
}
