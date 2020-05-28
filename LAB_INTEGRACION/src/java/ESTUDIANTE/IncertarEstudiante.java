/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ESTUDIANTE;

import Control.Control;
import Entidades.Estudiante;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author jose1
 */
@WebServlet(name = "IncertarEstudiante", urlPatterns = {"/IncertarEstudiante"})
public class IncertarEstudiante extends HttpServlet {

    
    Estudiante estidiante;
    private Control control = Control.getInstance();
    private String estudianteJsonString;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        
        StringBuilder jsonBuff = new StringBuilder();
        String line = null;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null)
            jsonBuff.append(line);
        
        JSONObject jsonObject =  new JSONObject(jsonBuff.toString());
        estidiante = gson.fromJson(jsonObject.toString(), Estudiante.class);
        
        estidiante = gson.fromJson(jsonObject.toString(), Estudiante.class);
        estudianteJsonString = gson.toJson(estidiante);
        
         try {
            control.insertarEstudiante(estidiante);
        } catch (Exception e) {
        }
         try {
            out.println(estudianteJsonString);
        } finally {
            out.close();
        }
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
