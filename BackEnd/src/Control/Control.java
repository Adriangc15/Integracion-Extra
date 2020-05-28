/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import Entidades.Estudiante;
import Modelo.ModeloEstudiante;
import java.util.Collection;

/**
 *
 * @author adrian
 */
public class Control {

    private static Control instance;
    private final ModeloEstudiante modelo;

    private Control() {
        this.modelo = ModeloEstudiante.getInstance();
    }

    public static Control getInstance() {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }

    private ModeloEstudiante getModelo() {
        return this.modelo;
    }

    public Estudiante insertarEstudiante(Estudiante estudiante) throws GlobalException, NoDataException {
        return this.getModelo().insertarEstudiante(estudiante);
    }

    public Collection listarEstudiantes() throws GlobalException, NoDataException {
        return this.getModelo().listarEstudiantes();
    }

    public void eliminarEstudiante(int estudianteId) throws GlobalException, NoDataException {
        this.getModelo().eliminarEstudiante(estudianteId);
    }

    public Estudiante modificarEstudiante(Estudiante estudiante) throws GlobalException, NoDataException {
        return this.getModelo().modificarEstudiante(estudiante);
    }

    public Collection listarEstCursos(int estudianteId) throws GlobalException, NoDataException {
        return this.getModelo().listarEstCursos(estudianteId);
    }

}
