/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author adrian
 */
public class Servicio {
    
    private static final String CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "LAB_INTEGRACION";
    private static final String DB_PWD = "root";
    
    protected Connection conexion = null;

    protected Servicio() {
        
    }
    
    protected void conectar() throws ClassNotFoundException, SQLException {
        Class.forName(CLASS_NAME);
        conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
    }
    
    protected void desconectar() throws SQLException{
        if(conexion != null && !conexion.isClosed()){
            conexion.close();
        }
    }
    
}
