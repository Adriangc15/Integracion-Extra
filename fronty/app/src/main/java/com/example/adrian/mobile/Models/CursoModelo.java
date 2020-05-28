package com.example.adrian.mobile.Models;

public class CursoModelo {

    private int id;
    private String descripcion;
    private String creditos;

    public CursoModelo(){
        this.id = 0;
        this.creditos = "";
        this.descripcion = "";
    }

    public CursoModelo(int id, String descripcion, String creditos){
        this.id = id;
        this.descripcion = descripcion;
        this.creditos = creditos;
    }

    public int getId(){return this.id;}
    public String getDescripcion(){return this.descripcion;}
    public String getCreditos(){return this.creditos;}

    public void setId(int id){this.id = id;}
    public void setDescripcion(String descripcion){this.descripcion = descripcion;}
    public void setCreditos(String creditos){this.creditos = creditos;}
}