/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author TAAVO
 */
public class CambiarEstadoVendAccion extends org.apache.struts.action.Action {

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

        List errorMsgs = new LinkedList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            errorMsgs.add("No se pudo cargar el controlador");
        }

        

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computadores?allowMultiQueries=true", "root", "asdf123");
            Statement statement = con.createStatement();
            


            
            String idPedido = request.getParameter("idPedido").trim();
            String comboEstado = request.getParameter("comboEstado").trim();



          

            if (!errorMsgs.isEmpty()) {
                request.setAttribute("errorMsgs", errorMsgs);
                return mapping.findForward("error"); 
            }

            
            //String sql_inserta = "insert into usuarios values('','" + inputUsuario + "','" + inputClave + "','" + inputRut + "','" + inputNombreCompleto + "','"+inputDireccion+"','"+comboRegiones+"','"+comboComunas+"','"+inputTelefono+"','"+inputEmail+"')";
            /*String sql_inserta = "UPDATE usuarios SET clave='?', rut='?', nombreCompleto='?', direccion='?', region='?', comuna='?',telefono='?', email='?' WHERE usuario='"+inputUsuario+"'";*/
            String sql_inserta = "update pedidos set estado='"+comboEstado+"' where idPedido="+idPedido;
            PreparedStatement statement2 = con.prepareStatement(sql_inserta);

            /*
             * statement2.setString(1, inputClave);
             * statement2.setString(2, inputRut);
             * statement2.setString(3, inputNombreCompleto);
             * statement2.setString(4, inputDireccion);
             * statement2.setString(5, comboRegiones);
             * statement2.setString(6, comboComunas);
             * statement2.setString(7, inputTelefono);
             * statement2.setString(8, inputEmail);*/


            statement2.executeUpdate();
            statement.close();
            statement2.close();
            con.close();


     
            return mapping.findForward("success");

        } catch (RuntimeException e) {
            errorMsgs.add("An unexpected error: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            return mapping.findForward("error");
        }

    }
}
