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
public class BorrarProductoDesdeCajaAccion extends org.apache.struts.action.Action {

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

        String idLista = request.getParameter("idLista");

        String sql_Eliminar = "delete from listaproductos where idListaProductos="+idLista;


        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computadores?allowMultiQueries=true", "root", "asdf123");
            //ResultSet results = statement.executeQuery(sql_Eliminar);
            PreparedStatement statement = con.prepareStatement(sql_Eliminar);

           


            statement.executeUpdate();

            statement.close();
            con.close();

            return mapping.findForward("success");



            /* 
            
             if (inputClave == null || inputClave.trim().length() == 0) {
             errorMsgs.add("Por favor ingrese la Clave.");
             }
            


             if (!errorMsgs.isEmpty()) {
             request.setAttribute("errorMsgs", errorMsgs);
             return mapping.findForward("error"); 
             }
             */

            //String sql_inserta = "insert into usuarios values('','" + inputUsuario + "','" + inputClave + "','" + inputRut + "','" + inputNombreCompleto + "','"+inputDireccion+"','"+comboRegiones+"','"+comboComunas+"','"+inputTelefono+"','"+inputEmail+"')";
            /*            String sql_inserta = "insert into listaproductos(idProducto,usuario) values ( ? , ? )";
             * PreparedStatement statement2 = con.prepareStatement(sql_inserta);
             * 
             * 
             * 
             * statement2.setInt(1, idProductoCar);
             * statement2.setString(2, nombreUserCar);
             * 
             * 
             * 
             * statement2.executeUpdate();
             * statement.close();
             * statement2.close();
             * con.close();
             * 
             * 
             * /*
             * request.setAttribute("inputUsuario", inputUsuario);
             * request.setAttribute("inputClave", inputClave);
             */
            /*
             * return mapping.findForward("success"); */

        } catch (RuntimeException e) {
            errorMsgs.add("An unexpected error: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            return mapping.findForward("error");
        }

    }
}
