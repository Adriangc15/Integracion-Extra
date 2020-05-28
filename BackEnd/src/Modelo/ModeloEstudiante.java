/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import AccesoADatos.ServicioEstudiante;
import Entidades.Estudiante;
import java.util.Collection;

/**
 *
 * @author adrian
 */
public class ModeloEstudiante {
    
    private static ModeloEstudiante instance;
    private final ServicioEstudiante servicio;
    
    private ModeloEstudiante(){
        servicio = new ServicioEstudiante();
    }
    
    public static ModeloEstudiante getInstance(){
        if(instance == null){
            instance = new ModeloEstudiante();
        }
        return instance;
    }
    
    private ServicioEstudiante getServicio(){return this.servicio;}
    
    public Estudiante insertarEstudiante(Estudiante estudiante) throws GlobalException, NoDataException{
        return this.getServicio().insertarEstudiante(estudiante);
    }
    
    public Collection listarEstudiantes() throws GlobalException, NoDataException{
        return this.getServicio().listarEstudiantes();
    }
    
    public void eliminarEstudiante(int estudianteId) throws GlobalException, NoDataException{
        this.getServicio().eliminarEstudiante(estudianteId);
    }
    
    public Estudiante modificarEstudiante(Estudiante estudiante) throws GlobalException, NoDataException{
        return this.getServicio().modificarEstudiante(estudiante);
    }
    
     public Collection listarEstCursos(int estudianteId) throws GlobalException, NoDataException {
         return this.getServicio().listarEstCursos(estudianteId);
     }
    
}
