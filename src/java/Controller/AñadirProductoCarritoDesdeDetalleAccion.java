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
public class AñadirProductoCarritoDesdeDetalleAccion extends org.apache.struts.action.Action {

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

        int idProductoDet = Integer.parseInt(request.getParameter("idProductoDet"));
        String usuarioenv = request.getParameter("usuarioenv").trim();
        int inputCantidad = Integer.parseInt(request.getParameter("inputCantidad"));

        String sql_buscaStock = "select stock from productos where idProducto=" + idProductoDet;
        String sql_buscaStockenListaCarrito = "select count(*) as contador from listaproductos where usuario='" + usuarioenv + "' and idProducto=" + idProductoDet + "";


        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computadores?allowMultiQueries=true", "root", "asdf123");
            Statement statement = con.createStatement();

            Statement statement3 = con.createStatement();

            ResultSet results = statement.executeQuery(sql_buscaStock);

            ResultSet results3 = statement3.executeQuery(sql_buscaStockenListaCarrito);


            while (results3.next() && results.next()) {
                
                    if (results.getInt("stock") >= results3.getInt("contador")+inputCantidad) {

                        for (int i = 0; i < inputCantidad; i++)
                        {
                        String sql_inserta = "insert into listaproductos(idProducto,usuario) values ( ? , ? )";
                        PreparedStatement statement2 = con.prepareStatement(sql_inserta);

                        statement2.setInt(1, idProductoDet);
                        statement2.setString(2, usuarioenv);

                        statement2.executeUpdate();

                        statement2.close();
                        }

                        return mapping.findForward("success");
                    }
                    if (results.getInt("stock") < results3.getInt("contador")+inputCantidad) {

                        errorMsgs.add("La cantidad ingresada es incorrecta !.");
                        
                    }

                


            }



            statement.close();

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

        } catch (RuntimeException e) {
            errorMsgs.add("An unexpected error: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            return mapping.findForward("error");
        }
        return null;

    }
}
