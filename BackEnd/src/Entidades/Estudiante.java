/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author adrian
 */
public class Estudiante {
    
    private int id;
    private String nombre;
    private String apellido;
    private String edad;
    private Collection<Curso> cursos;
    
    public Estudiante(){
        this.id = 0;
        this.nombre = "";
        this.apellido = "";
        this.edad = "";
        this.cursos = new ArrayList<>();
    }
    
    public Estudiante(int id, String nombre, String apellido, String edad, Collection<Curso> cursos){
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
    public Collection<Curso> getCursos(){return this.cursos;}
    
    public void setId(int id){this.id = id;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setApellido(String apellido){this.apellido = apellido;}
    public void setEdad(String edad){this.edad = edad;}
    public void setCursos(Collection<Curso> cursos){this.cursos = cursos;}
    
}
