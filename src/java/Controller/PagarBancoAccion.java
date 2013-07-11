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
public class PagarBancoAccion extends org.apache.struts.action.Action {

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

            Statement statement2 = con.createStatement();

            Statement statement7 = con.createStatement();


            //  String numeroTarjeta = request.getParameter("numeroTarjeta").trim();
            String usuario = request.getParameter("usuariooculto").trim();
            String fecha = request.getParameter("fecha").trim();

            String sql_listaProductos = "select * from listaproductos where usuario='" + usuario + "'";

            ResultSet results2 = statement2.executeQuery(sql_listaProductos);



            /*
             if (!errorMsgs.isEmpty()) {
             request.setAttribute("errorMsgs", errorMsgs);
             return mapping.findForward("error");  //código cambiado
             }*/

            //   ResultSet tabla = statement.executeQuery("select * from numerosvalidos");


            String sql_crearPedido = "insert into pedidos(usuario,fecha) values ( ? , ? )";
            PreparedStatement statement6 = con.prepareStatement(sql_crearPedido);



            statement6.setString(1, usuario);
            statement6.setString(2, fecha);

            statement6.executeUpdate();




            String sql_PedirIdOrden = "SELECT * FROM pedidos ORDER BY idPedido DESC LIMIT 1";

            ResultSet results7 = statement7.executeQuery(sql_PedirIdOrden);





            while (results2.next() && results7.next()) {
                String idListaProductos = results2.getString("idListaProductos");
                String idProducto = results2.getString("idProducto");
                String idUsuario = results2.getString("usuario");
                String idPedido = results7.getString("idPedido");

                String sql_CopiarLista = "insert into listacompras(idProducto,usuario,fecha,idPedido) values ( ? , ? , ? , ?)";
                PreparedStatement statement3 = con.prepareStatement(sql_CopiarLista);


                statement3.setString(1, idProducto);
                statement3.setString(2, idUsuario);
                statement3.setString(3, fecha);
                statement3.setString(4, idPedido);

                statement3.executeUpdate();

                String sql_bajarStock = "UPDATE productos SET stock=stock-1 where idProducto='" + idProducto + "'";
                PreparedStatement statement4 = con.prepareStatement(sql_bajarStock);
                statement4.executeUpdate();

                String sql_BorrarLista = "delete from listaproductos where idListaProductos=" + idListaProductos;
                PreparedStatement statement5 = con.prepareStatement(sql_BorrarLista);
                statement5.executeUpdate();


            }

            results2.close();
            statement.close();
            statement2.close();
            results7.close();
            statement7.close();

            con.close();




            if (!errorMsgs.isEmpty()) {
                request.setAttribute("errorMsgs", errorMsgs);
                return mapping.findForward("error");
            }





            return mapping.findForward("success");

        } catch (RuntimeException e) {
            errorMsgs.add("An unexpected error: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            return mapping.findForward("error");
        } catch (SQLException e) {
            errorMsgs.add("Ocurrio un error en la Base de Datos" + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            return mapping.findForward("error");
        }


    }
}
