/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.InputStream;
import java.sql.Blob;
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
import javax.servlet.http.Part;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author TAAVO
 */
public class AñadirImagenAccion extends org.apache.struts.action.Action {

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

       
        //String nombreUserCar = request.getParameter("nombreUserCar").trim();

        String idPedido2 = request.getParameter("idPedido2");
        //String idPedido = "1";
        Part filePart = request.getPart("photo");
        String asdf = "asdf";

        //String sql_buscaStock = "select stock from productos where idProducto=" + idProductoCar;
        //String sql_buscaStockenListaCarrito = "select count(*) as contador from listaproductos where usuario='" + nombreUserCar + "' and idProducto=" + idProductoCar + "";


        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computadores?allowMultiQueries=true", "root", "asdf123");
            Statement statement = con.createStatement();


            String sql_inserta = "insert into comprobantes(imagen,idPedido) values ( ? , ? )";
            PreparedStatement statement2 = con.prepareStatement(sql_inserta);

            statement2.setBinaryStream(1, (InputStream) filePart);
            statement2.setString(2, idPedido2);

            statement2.executeUpdate();

            statement2.close();



            con.close();



            if (!errorMsgs.isEmpty()) {
                request.setAttribute("errorMsgs", errorMsgs);
                return mapping.findForward("error");
            }

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


            return mapping.findForward("success");


        } catch (RuntimeException e) {
            errorMsgs.add("An unexpected error: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            return mapping.findForward("error");
        }


    }
}
