/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TAAVO
 */
public class MisComprasServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected Connection con;
    //protected Connection con2;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera el objeto sesion (Para usar las preferencias)
        HttpSession session = request.getSession();

        String usuario = (String) session.getAttribute("varUsuario");

        //String id = request.getParameter("idProducto").toString();


        if (usuario != null) {

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("No se pudo cargar el controlador");
            }

            String sql_usuario = "select * from usuarios where usuario='" + usuario + "'";
            String sql_carrito = "select idListaProductos,productos.nombre,productos.precio,productos.idProducto from listaproductos INNER JOIN productos on listaproductos.idProducto=productos.idProducto where usuario='" + usuario + "'";
            String sql_miscompras = "select pedidos.idPedido,sum(precio),pedidos.fecha,pedidos.estado from listacompras inner join (pedidos,productos) on listacompras.idPedido=pedidos.idPedido and listacompras.idProducto=productos.idProducto where pedidos.usuario='" + usuario + "' group by pedidos.idPedido";


            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computadores?allowMultiQueries=true", "root", "asdf123");
                Statement statement = con.createStatement();
                Statement statement3 = con.createStatement();
                Statement statement4 = con.createStatement();


                //Statement statement2 = con.createStatement();
                ResultSet results = statement.executeQuery(sql_usuario);
                ResultSet results3 = statement3.executeQuery(sql_carrito);
                ResultSet results4 = statement4.executeQuery(sql_miscompras);



                //ResultSet results2 = statement2.executeQuery(sql_usuario);

                DecimalFormat df = new DecimalFormat("#,###");



                out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                        + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                        + "<head>\n"
                        + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                        + "<link rel=\"stylesheet\" href=\"js/magnific-popup.css\"> \n"
                        + "<link type=\"text/css\" rel=\"stylesheet\" href=\"js/estiloProductos.css\"> \n"
                        + "<script src=\"js/jquery.min.js\"></script> \n"
                        + "<script src=\"js/jquery.magnific-popup.js\"></script> \n"
                        + "\n"
                        + "<script>\n"
                        + "\n"
                        + "$(document).ready(function(){\n"
                        + " $(\"#oculto\").hide(); \n"
                        + " $(\"#ocultoTienda\").hide();\n"
                        + " $(\"#ocultoVisa\").hide();\n"
                        + "$(\"#mostrarPago\").click(function(){\n"
                        + "    $(\"#oculto\").show(1000);\n"
                        + "  });\n"
                        + "$(\"#elegirTienda\").click(function(){\n"
                        + "    $(\"#ocultoTienda\").show(1000);\n"
                        + "	$(\"#ocultoVisa\").hide();\n"
                        + "  });\n"
                        + "$(\"#elegirVisa\").click(function(){\n"
                        + "    $(\"#ocultoVisa\").show(1000);\n"
                        + "	$(\"#ocultoTienda\").hide();\n"
                        + "  });\n"
                        + "  \n"
                        + "});\n"
                        + "\n"
                        + "</script>"
                        + "<script>\n"
                        + "\n"
                        + "$(document).ready(function(){\n"
                        + "\n"
                        + "var d = new Date();\n"
                        + "\n"
                        + "var month = d.getMonth()+1;\n"
                        + "var day = d.getDate();\n"
                        + "\n"
                        + "var output = (day<10 ? '0' : '') + day + '/' +\n"
                        + "    (month<10 ? '0' : '') + month + '/' + d.getFullYear();\n"
                        + "\n"
                        + "$('#d').val(output);\n"
                        + "\n"
                        + "});\n"
                        + "\n"
                        + "</script>\n"
                        + "\n"
                        + "\n"
                        + "<title>Caja</title>\n"
                        + "\n"
                        + "</head>\n"
                        + "<body class=\"fondo\">\n"
                        + "\n"
                        + "\n"
                        + "");


                List errorMsgs = (List) request.getAttribute("errorMsgs");
                if (errorMsgs != null) {
                    out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"js/estiloProductos.css\" /><script src=\"js/jquery.min.js\"></script><script src=\"js/jquery-1.9.1.js\"></script><script src=\"js/jquery-ui.js\"></script><link rel=\"stylesheet\" href=\"js/jquery-ui.css\" />\n"
                            + "  $(function() {\n"
                            + "    $( \"#dialog-message\" ).dialog({\n"
                            + "     width: 600, "
                            + "modal: true,\n"
                            + "      buttons: {\n"
                            + "        Ok: function() {\n"
                            + "          window.location = 'index.html';"
                            + "        }\n"
                            + "      }\n"
                            + "    });\n"
                            + "  });\n"
                            + "  </script>");
                    out.println("<div id=\"dialog-message\" title=\"Mensaje de Error\">");
                    out.println("<font color='red'>Por Favor corrija los siguientes errores:");
                    out.println("<ul>");
                    Iterator items = errorMsgs.iterator();
                    while (items.hasNext()) {
                        String message = (String) items.next();
                        out.println("  <li>" + message + "</li>");
                    }
                    out.println("</ul>");
                    out.println("</font></div>");
                }
                
                
                out.println("<center>\n"
                        + "<div class=\"divLogo\">\n"
                        + "<center>\n"
                        + "<br/>\n"
                        + "<img src=\"imagenes/logo.png\"  />\n"
                        + "</center>\n"
                        + "</div>\n"
                        + "</center><br/>");


                out.println("<div class=\"divContenido\">\n"
                        + "\n"
                        + "<div class=\"divMenus\">\n"
                        + "\n"
                        + "  \n"
                        + "  <center>\n"
                        + "  <br/>");



                out.println("<div class=\"divPasarCaja\" onmouseover=\"this.style.backgroundColor='#E6E6E6';\" onmouseout=\"this.style.backgroundColor='#CECECE';\" >\n"
                        + "<a href=\"index.html\" class=\"linkcaja\"><p onMouseOver=\"this.style.color='red'; \" onMouseOut=\"this.style.color='black';\">Ver Productos</p></a>\n"
                        + "</div>\n"
                        + "  <br/>");



                while (results.next()) {
                    out.println("<div class=\"divUsuario\" onmouseover=\"this.style.backgroundColor='#E6E6E6';\" onmouseout=\"this.style.backgroundColor='#CECECE';\" >\n"
                            + "    <span>Mi Cuenta</span>\n"
                            + "  <p style=\"color:#000\">" + results.getString("nombreCompleto") + "</p>\n"
                            + "  \n"
                            + "<center>\n"
                            + "<div class=\"divbotonesmenuuser\">\n"
                            + "  <form method=\"get\" action=\"misdatos.view\" name=\"formmisdatos\">\n"
                            + "  <INPUT TYPE= \"submit\" VALUE=\"Mis Datos\" class=\"botonMenuUser1\" onmouseover=\"this.className='botonMenuUser2'\" onmouseout=\"this.className='botonMenuUser1'\"> \n"
                            + "  </form>\n"
                            + "  \n"
                            + "  <form method=\"get\" action=\"miscompras.view\" name=\"formmiscompras\">\n"
                            + "  <INPUT TYPE= \"submit\" VALUE=\"Mis Compras\" class=\"botonMenuUser1\" onmouseover=\"this.className='botonMenuUser2'\" onmouseout=\"this.className='botonMenuUser1'\">\n"
                            + "  </form>\n"
                            + "  \n"
                            + "   \n"
                            + "");

                    if (results.getString("rango").equals("r")) {
                        out.println("<form method=\"get\" action=\"tablarepartidor.view\" name=\"formmiscompras\">\n"
                                + "  <INPUT TYPE= \"submit\" VALUE=\"Repartición\" class=\"botonMenuUser1\" onmouseover=\"this.className='botonMenuUser2'\" onmouseout=\"this.className='botonMenuUser1'\">\n"
                                + "  </form>");
                    }
                    if (results.getString("rango").equals("v")) {
                        out.println("<form method=\"get\" action=\"tablavendedor.view\" name=\"formmiscompras\">\n"
                                + "  <INPUT TYPE= \"submit\" VALUE=\"Ventas\" class=\"botonMenuUser1\" onmouseover=\"this.className='botonMenuUser2'\" onmouseout=\"this.className='botonMenuUser1'\">\n"
                                + "  </form>");
                    }


                    out.println("<form method=\"get\" action=\"salir.jsp\" name=\"formmiscompras\">\n"
                            + "  <INPUT TYPE= \"submit\" VALUE=\"Salir\" class=\"botonMenuUser1\" onmouseover=\"this.className='botonMenuUser2'\" onmouseout=\"this.className='botonMenuUser1'\">\n"
                            + "  </form></div>\n"
                            + "  </center>\n"
                            + "  </div>\n"
                            + "<br/>");


                }

                out.println("<div class=\"divCarrito\" onmouseover=\"this.style.backgroundColor='#E6E6E6';\" onmouseout=\"this.style.backgroundColor='#CECECE';\" >\n"
                        + " <span>Mi Carrito</span>\n"
                        + " <br/><br/>\n"
                        + " <table class=\"tablacarrito\" border=\"1\" bordercolor=\"#B2B2B2\">\n"
                        + "  <thead>\n"
                        + "    <td>Nombre</td>\n"
                        + "    \n"
                        + "    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
                        + "  </thead>\n"
                        + "  <tbody>");


                while (results3.next()) {
                    out.println("<tr>\n"
                            + "  <td><font color=\"#000000\">" + results3.getString("nombre") + "</font></td><td>   <form method=\"post\" action=\"borrarDetalle.do\"><input type=\"hidden\" name=\"idlista\" value=\"" + results3.getInt("idListaProductos") + "\" /><input class=\"botonEliminar\" type=\"submit\" value=\"X\" /></form></td>\n"
                            + "  </tr>");
                }




                out.println("</tbody>  \n"
                        + "</table>\n"
                        + "<br/>\n"
                        + "</div></br>");




                out.println("<div class=\"divPasarCaja\" onmouseover=\"this.style.backgroundColor='#E6E6E6';\" onmouseout=\"this.style.backgroundColor='#CECECE';\" >\n"
                        + "<a href=\"caja.view\" class=\"linkcaja\"><p onMouseOver=\"this.style.color='red'; \" onMouseOut=\"this.style.color='black';\">Pasar a Caja</p></a>\n"
                        + "</div>\n"
                        + "<br/>");


                out.println("</center>\n"
                        + "\n"
                        + "</div>\n"
                        + "\n"
                        + "<div class=\"divContenedorProductos\">");




                out.println(" <div class=\"divTablaCaja\">\n"
                        + "    \n"
                        + "    <center><h2 class=\"exitoletra\">Lista de pedidos</h2></center> \n"
                        + "    <table width=\"812\" class=\"tablaCaja\" bordercolor=\"#333333\" border=\"2px\">\n"
                        + "    <thead >\n"
                        + "    <th width=\"94\" class=\"tablaCajathead\">Id Pedido</th>\n"
                        + "    <th width=\"152\" class=\"tablaCajathead\">Fecha</th>\n"
                        + "    <th width=\"152\" class=\"tablaCajathead\">Estado</th>\n"
                        + "    <th width=\"131\" class=\"tablaCajathead\">Total</th>\n"
                        + "    <th width=\"125\" class=\"tablaCajathead\">Ver Pedido</th> \n"
                        + "     <th width=\"116\" class=\"tablaCajathead\">Pagar</th> \n"
                        + " <tbody>\n"
                        + "    ");


                while (results4.next()) {

                    out.println("<tr><td class=\"tablaCajatbody\">" + results4.getString("idPedido") + "</td>\n"
                            + "    <td class=\"tablaCajatbody\">" + results4.getString("fecha") + "</td>\n"
                            + "    <td class=\"tablaCajatbody\">" + results4.getString("estado") + "</td><td class=\"tablaCajatbody\">$" + df.format(results4.getInt("sum(precio)")) + "</td>\n"
                            + "    <td class=\"tablaCajatbody\"><form action=\"verpedido.view\" method=\"get\" name=\"formVerpedido\"><input type=\"hidden\" name=\"idPedido\" value=\"" + results4.getString("idPedido") + "\"><input type=\"submit\"  class=\"botonVerPedido\" value=\"Ver\"></form></td>\n"
                            + "    <td class=\"tablaCajatbody\"><form action=\"pagarpedido.view\" method=\"get\" name=\"formpagarpedido\"><input type=\"hidden\" name=\"idPedido\" value=\"" + results4.getString("idPedido") + "\"><input type=\"submit\"  class=\"botonVerPedido\" value=\"Pagar\"></form></td>\n"
                            + "    </tr>");



                }






                out.println("</tbody>\n"
                        + "    </table>\n"
                        + "    </div>");





                out.println("\n"
                        + "     \n"
                        + "    \n"
                        + "\n"
                        + "\n"
                        + "\n"
                        + "</div>\n"
                        + "\n"
                        + "</div>\n"
                        + "</body>\n"
                        + "</html>");





                //out.println("  <td>" + results.getFloat("precio") + "</td>");


                statement.close();

                results.close();

                statement3.close();
                results3.close();
                statement4.close();
                results4.close();



                con.close();

            } catch (SQLException e) {
                System.out.println("Ocurrio un error en la Base de Datos");

            } finally {
                out.close();
            }

        } else {

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                    + "<head>\n"
                    + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                    + "<title>Error</title>\n"
                    + "<style>\n"
                    + ".letra\n"
                    + "{\n"
                    + "	font-size:50px;\n"
                    + "	color:#333;\n"
                    + "}\n"
                    + ".letra2\n"
                    + "{\n"
                    + "	font-size:20px;\n"
                    + "	color:#333;\n"
                    + "}\n"
                    + ".letra3\n"
                    + "{\n"
                    + "	font-size:20px;\n"
                    + "	color:#333;\n"
                    + "}\n"
                    + "</style>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "<h1 class=\"letra\">Error</h1><br/>\n"
                    + "<h1 class=\"letra2\">La pagina solicitada no existe o no tiene los permisos suficientes.</h1>\n"
                    + "<br/>\n"
                    + "<br/>\n"
                    + "<a href=\"index.html\" class=\"letra3\">>> Ir a la pagina de inicio</a>\n"
                    + "\n"
                    + "</body>\n"
                    + "</html>");
        }


    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
