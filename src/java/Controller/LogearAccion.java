/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author TAAVO
 */
public class LogearAccion extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    protected Connection con;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        List errorMsgs = new LinkedList();
        
        

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            errorMsgs.add("No se pudo cargar el controlador");
        }

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost/computadores", "root", "asdf123");
            Statement statement = con.createStatement();
            String usuario = request.getParameter("usuario").trim();
            String clave = request.getParameter("clave").trim();

            if ((usuario == null) || (usuario.trim().length() == 0)) {
                errorMsgs.add("Por favor ingrese el Usuario.");
            }

            // Verificación del formulario
            if (clave == null || clave.trim().length() == 0) {
                errorMsgs.add("Por favor ingrese la Clave.");
            }

/*
            if (!errorMsgs.isEmpty()) {
                request.setAttribute("errorMsgs", errorMsgs);
                return mapping.findForward("error");  //código cambiado
            }*/

            
            int b = 0;
            ResultSet tabla = statement.executeQuery("select usuario,clave from usuarios where usuario='" + usuario + "'");

            while (tabla.next())
            {
                String us = tabla.getString("usuario");
                String pa = tabla.getString("clave");
                if (us.equals(usuario) && pa.equals(clave)) {
                    b = 1;//si se cumple es 1
                } else {
                    b = 0; //sino es 0
                }

            }
            
            if (b == 1) {
                String ja = new String(usuario);
               

                HttpSession session = request.getSession();
                session.putValue("varUsuario", ja);
                con.close();
                return mapping.findForward("success");
                

                
            } else {

                con.close();
                errorMsgs.add("Datos Incorrectos");
                
               
         
            }
            
            if (!errorMsgs.isEmpty()) {
                request.setAttribute("errorMsgs", errorMsgs);
                return mapping.findForward("error");  
            }

            
            
            


          
        } catch (RuntimeException e) {
            errorMsgs.add("An unexpected error: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            return mapping.findForward("error");
        } catch (SQLException e) {
            errorMsgs.add("Ocurrio un error en la Base de Datos" + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            return mapping.findForward("error");
        }
        return null;

    }
}
