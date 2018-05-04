package com.example.dmorales.silabusprueba.model;

public class Curso {
    private String nombre;
    private int tipo;
    private String profesor;

    public Curso(String nombre, int tipo, String profesor) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.profesor = profesor;
    }

    public Curso() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}
