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
public class VerCajaServlet extends HttpServlet {

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
            String sql_regiones = "select nombre from regiones";
            String sql_comunas = "select nombre from comunas";

            String sql_usuario = "select * from usuarios where usuario='" + usuario + "'";
            String sql_carrito = "select idListaProductos,productos.nombre,productos.precio,productos.idProducto from listaproductos INNER JOIN productos on listaproductos.idProducto=productos.idProducto where usuario='" + usuario + "'";



            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computadores?allowMultiQueries=true", "root", "asdf123");
                Statement statement = con.createStatement();
                Statement statement3 = con.createStatement();
                Statement statement4 = con.createStatement();
                Statement statement5 = con.createStatement();
                Statement statement6 = con.createStatement();
                Statement statement7 = con.createStatement();
                Statement statement8 = con.createStatement();
                Statement statement9 = con.createStatement();
                Statement statement10 = con.createStatement();
                Statement statement11 = con.createStatement();
                Statement statement12 = con.createStatement();
                Statement statement13 = con.createStatement();
                Statement statement14 = con.createStatement();
                //Statement statement2 = con.createStatement();
                ResultSet results = statement.executeQuery(sql_usuario);
                ResultSet results3 = statement3.executeQuery(sql_carrito);
                ResultSet results4 = statement4.executeQuery(sql_usuario);
                ResultSet results5 = statement5.executeQuery(sql_usuario);
                ResultSet results6 = statement6.executeQuery(sql_regiones);
                ResultSet results7 = statement7.executeQuery(sql_comunas);
                ResultSet results8 = statement8.executeQuery(sql_usuario);

                ResultSet results9 = statement9.executeQuery(sql_usuario);
                ResultSet results10 = statement10.executeQuery(sql_regiones);
                ResultSet results11 = statement11.executeQuery(sql_comunas);
                ResultSet results12 = statement12.executeQuery(sql_usuario);
                ResultSet results13 = statement13.executeQuery(sql_usuario);
                ResultSet results14 = statement14.executeQuery(sql_usuario);

                //ResultSet results2 = statement2.executeQuery(sql_usuario);

                DecimalFormat df = new DecimalFormat("#,###");



                out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                        + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                        + "<head>\n"
                        + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                        + "<link rel=\"stylesheet\" href=\"js/magnific-popup.css\"> \n"
                        + "<link type=\"text/css\" rel=\"stylesheet\" href=\"js/estiloProductos.css\"> \n"
                        + "<script src=\"js/jquery.min.js\"></script> \n"
                        + " \n"
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
                        + "var hora = d.getHours();"
                        + "var minutos = d.getMinutes();"
                        + "var output = (day<10 ? '0' : '') + day + '/' +\n"
                        + "    (month<10 ? '0' : '') + month + '/' + d.getFullYear() + ' ' + (hora<10 ? '0' : '') + hora + ':' + (minutos<10 ? '0' : '') + minutos;\n"
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
                    out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"js/estiloProductos.css\" /><script src=\"js/jquery.min.js\"></script><script src=\"js/jquery-1.9.1.js\"></script><script src=\"js/jquery-ui.js\"></script><link rel=\"stylesheet\" href=\"js/jquery-ui.css\" /><script>\n"
                            + "  $(function() {\n"
                            + "    $( \"#dialog-message\" ).dialog({\n"
                            + "     width: 600, "
                            + "modal: true,\n"
                            + "      buttons: {\n"
                            + "        Ok: function() {\n"
                            + "          window.location = 'caja.view';"
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

                out.println("<div class=\"divPasarCaja\" onmouseover=\"this.style.backgroundColor='#E6E6E6';\" onmouseout=\"this.style.backgroundColor='#CECECE';\" >\n"
                        + "<a href=\"index.html\" class=\"linkcaja\"><p onMouseOver=\"this.style.color='red'; \" onMouseOut=\"this.style.color='black';\">Ver Productos</p></a>\n"
                        + "</div>\n"
                        + "  <br/>");


                out.println("</center>\n"
                        + "\n"
                        + "</div>\n"
                        + "\n"
                        + "<div class=\"divContenedorProductos\">");




                out.println("<div class=\"divTablaCaja\">\n"
                        + "    <table width=\"812\" class=\"tablaCaja\" bordercolor=\"#333333\" border=\"2px\">\n"
                        + "    <thead >\n"
                        + "    <th width=\"111\" class=\"tablaCajathead\">Id Producto</th>\n"
                        + "    <th width=\"393\" class=\"tablaCajathead\">Nombre Producto</th>\n"
                        + "    <th width=\"136\" class=\"tablaCajathead\">Precio</th>\n"
                        + "    \n"
                        + "    <th width=\"142\" class=\"tablaCajathead\">Eliminar</th></thead>\n"
                        + "    \n"
                        + "    \n"
                        + "    \n"
                        + "    <tbody>");




                int total = 0;

                while (results3.next()) {

                    // String sql_buscaStockenListaCarrito = "select count(*) as contador from listaproductos where usuario='" + usuario + "' and idProducto=" + results3.getString("idProducto") + "";
                    // ResultSet results2 = statement2.executeQuery(sql_buscaStockenListaCarrito);

                    total = total + results3.getInt("precio");

                    out.println("<tr onmouseover=\"this.style.backgroundColor='#E6E6E6';\" onmouseout=\"this.style.backgroundColor='#CECECE';\" ><td class=\"tablaCajatbody\"  >" + results3.getInt("idProducto") + "</td>\n"
                            + "    \n"
                            + "    <td class=\"tablaCajatbody\">" + results3.getString("nombre") + "</td>\n"
                            + "   \n"
                            + "    \n"
                            + "    <td class=\"tablaCajatbody\">$" + df.format(results3.getInt("precio")) + "</td>\n"
                            + "    \n"
                            + "    <td class=\"tablaCajatbody\"><form method=\"post\" name=\"formEliminarconCantidad\" action=\"eliminarConCantidad.do\">\n"
                            + "    <input type=\"hidden\" name=\"idLista\" value=\"" + results3.getInt("idListaProductos") + "\" />\n"
                            + "    <input type=\"submit\" value=\"Eliminar\" name=\"botonEliminar\" class=\"botonEliminarCaja\" />\n"
                            + "    </form></td>\n"
                            + "    </tr>");





                    //   statement2.close();
                    //results2.close();
                }

                out.println("</tbody>\n"
                        + "    </table>");


                out.println("<div class=\"precioTotal\">Precio Total: $ <span class=\"precioTotal2\">" + df.format(total) + "</span>\n"
                        + "    <br/>\n"
                        + "    <input type=\"button\" value=\"Pagar\" class=\"botonPagar\" id=\"mostrarPago\"> \n"
                        + "    </div>");

                while (results4.next()) {

                    out.println("<div class=\"datosUsuario\">\n"
                            + "    <span>Nombre: " + results4.getString("nombreCompleto") + "</span><br/>\n"
                            + "    <span>Rut: " + results4.getString("rut") + "</span><br/>\n"
                            + "    <span>Direccion: " + results4.getString("direccion") + "</span><br/>\n"
                            + "    <span>Region: " + results4.getString("region") + "</span><br/>\n"
                            + "    <span>Comuna: " + results4.getString("comuna") + "</span><br/>\n"
                            + "    <span>Telefono: " + results4.getString("telefono") + "</span><br/>\n"
                            + "    <span>Email: " + results4.getString("email") + "</span>\n"
                            + "    </div>");

                }

                out.println("</div>");

                out.println("<div id=\"oculto\" class=\"divTablaCaja\">\n"
                        + "   <div class=\"tablaMetodos\"><center><h2>Elija metodo de pago</h2>\n"
                        + "   \n"
                        + "   \n"
                        + "   <input type=\"button\" value=\"Transferencia Bancaria\" class=\"botonMetodo2\" id=\"elegirTienda\"></center>\n"
                        + "   \n"
                        + "   </div>\n"
                        + "   </div><div id=\"ocultoTienda\" class=\"divTablaCaja\">");


                out.println(" \n"
                        + "<center><br/><h2>Metodo transferencia bancaria</h2><form name=\"formPago\" action=\"pagarbanco.do\" method=\"post\">\n"
                        + "  <br/>\n"
                        + "    </br>\n"
                        + "    <span>Aqui los datos de la cuenta bancaria y las instrucciones</span><br/><br/>\n"
                        + "  <input type=\"hidden\" name=\"fecha\" id=\"d\"/>\n"
                        + "  <input type=\"hidden\" name=\"usuariooculto\" value=\""+usuario+"\" />\n"
                        + "    <input type=\"submit\" name=\"direccionEnvio\" value=\"Aceptar\" class=\"botonEnviar\" />\n"
                        + "  \n"
                        + "</form>\n"
                        + "\n"
                        + "<br/></center>\n"
                        + "</div>");




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
                statement5.close();
                results5.close();
                statement6.close();
                results6.close();
                statement7.close();
                results7.close();
                statement8.close();
                results8.close();
                statement9.close();
                results9.close();
                statement10.close();
                results10.close();
                statement11.close();
                results11.close();
                statement12.close();
                results12.close();
                statement13.close();
                results13.close();
                statement14.close();
                results14.close();

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
