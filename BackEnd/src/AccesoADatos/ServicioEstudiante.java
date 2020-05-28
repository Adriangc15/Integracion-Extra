/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import Entidades.Curso;
import Entidades.Estudiante;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author adrian
 */
public class ServicioEstudiante extends Servicio {

    /* 
        TODO: Reemplazar por los correctos SP 
     */
    private static final String INSERTAR_ESTUDIANTE = "";
    private static final String MODIFICAR_ESTUDIANTE = "";
    private static final String LISTAR_ESTUDIANTE = "";
    private static final String ELIMINAR_ESTUDIANTE = "";
    private static final String LISTAR_EST_CURSOS = "";

    private static final int ESTUDIANTE_ID = 1;
    private static final int ESTUDIANTE_NOMBRE = 2;
    private static final int ESTUDIANTE_APELLIDO = 3;
    private static final int ESTUDIANTE_EDAD = 4;

    private static final int CURSO_ID = 1;
    private static final int CURSO_DESCRIPCION = 2;
    private static final int CURSO_CREDITOS = 3;

    public ServicioEstudiante() {
        super();
    }

    public Estudiante insertarEstudiante(Estudiante estudiante) throws GlobalException, NoDataException {
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        CallableStatement pstmt = null;

        try {
            pstmt = this.conexion.prepareCall(INSERTAR_ESTUDIANTE);
            pstmt.setInt(ESTUDIANTE_ID, estudiante.getId());
            pstmt.setString(ESTUDIANTE_NOMBRE, estudiante.getNombre());
            pstmt.setString(ESTUDIANTE_APELLIDO, estudiante.getApellido());
            pstmt.setString(ESTUDIANTE_EDAD, estudiante.getEdad());

            boolean resultado = pstmt.execute();
            if (resultado) {
                throw new NoDataException("No se realizo la insercion");
            }
        } catch (SQLException exc) {
            throw new GlobalException("Llave duplicada");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                this.desconectar();
            } catch (SQLException exc) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        return estudiante;
    }

    public Estudiante modificarEstudiante(Estudiante estudiante) throws GlobalException, NoDataException {
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        PreparedStatement pstmt = null;

        try {
            pstmt = this.conexion.prepareStatement(MODIFICAR_ESTUDIANTE);
            pstmt.setInt(ESTUDIANTE_ID, estudiante.getId());
            pstmt.setString(ESTUDIANTE_NOMBRE, estudiante.getNombre());
            pstmt.setString(ESTUDIANTE_APELLIDO, estudiante.getApellido());
            pstmt.setString(ESTUDIANTE_EDAD, estudiante.getEdad());

            int resutltado = pstmt.executeUpdate();
            if (resutltado == 0) {
                throw new NoDataException("No se realizo la actualización");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                this.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        return estudiante;
    }

    public void eliminarEstudiante(int estudianteId) throws GlobalException, NoDataException {
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        PreparedStatement pstmt = null;

        try {
            pstmt = this.conexion.prepareStatement(ELIMINAR_ESTUDIANTE);
            pstmt.setInt(ESTUDIANTE_ID, estudianteId);

            int resultado = pstmt.executeUpdate();
            if (resultado == 0) {
                throw new NoDataException(String.format("No se pudo eliminar el estudiante id: %d", estudianteId));
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                this.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    public Collection listarEstudiantes() throws GlobalException, NoDataException {
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        ArrayList collection = new ArrayList();
        Estudiante estudiante = null;
        CallableStatement pstmt = null;

        try {
            pstmt = this.conexion.prepareCall(LISTAR_ESTUDIANTE);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                estudiante = new Estudiante(
                        rs.getInt(ESTUDIANTE_ID),
                        rs.getString(ESTUDIANTE_NOMBRE),
                        rs.getString(ESTUDIANTE_APELLIDO),
                        rs.getString(ESTUDIANTE_EDAD),
                        new ArrayList<>());
                collection.add(estudiante);
            }

        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                this.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }

        return collection;
    }

    public Collection listarEstCursos(int estudianteId) throws GlobalException, NoDataException {
        try {
            this.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        ArrayList collection = new ArrayList();
        Curso curso = null;
        CallableStatement pstmt = null;

        try {
            pstmt = this.conexion.prepareCall(LISTAR_EST_CURSOS);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, estudianteId);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                curso = new Curso(
                        rs.getInt(CURSO_ID),
                        rs.getString(CURSO_DESCRIPCION),
                        rs.getString(CURSO_CREDITOS));
                collection.add(curso);
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                this.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }

        return collection;

    }

}
