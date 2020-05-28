package com.example.adrian.mobile.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class EstudianteModel implements Serializable{

        private int id;
        private String nombre;
        private String apellido;
        private String edad;
        private Collection<CursoModelo> cursos;

        public EstudianteModel(){
            this.id = 0;
            this.nombre = "";
            this.apellido = "";
            this.edad = "";
            this.cursos = new ArrayList<>();
        }

        public EstudianteModel(int id, String nombre, String apellido, String edad, Collection<CursoModelo> cursos){
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.edad = edad;
            this.cursos = cursos;
        }

        public int getId(){return this.id;}
        public String getNombre(){return this.nombre;}
        public String getApellido(){return this.apellido;}
        public String getEdad(){return this.edad;}
        public Collection<CursoModelo> getCursos(){return this.cursos;}

        public void setId(int id){this.id = id;}
        public void setNombre(String nombre){this.nombre = nombre;}
        public void setApellido(String apellido){this.apellido = apellido;}
        public void setEdad(String edad){this.edad = edad;}
        public void setCursos(Collection<CursoModelo> cursos){this.cursos = cursos;}

    }

