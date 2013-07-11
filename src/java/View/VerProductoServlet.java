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
public class VerProductoServlet extends HttpServlet {

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

        String id = request.getParameter("idProducto").toString();


        if (usuario != null) {

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("No se pudo cargar el controlador");
            }

            String sql_buscaPr = "select * from productos where idProducto='" + id + "'";
            String sql_usuario = "select * from usuarios where usuario='" + usuario + "'";
            String sql_carrito = "select idListaProductos,productos.nombre from listaproductos INNER JOIN productos on listaproductos.idProducto=productos.idProducto where usuario='" + usuario + "'";


            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computadores?allowMultiQueries=true", "root", "asdf123");
                Statement statement = con.createStatement();
                Statement statement2 = con.createStatement();
                Statement statement3 = con.createStatement();

                //Statement statement2 = con.createStatement();
                ResultSet results = statement.executeQuery(sql_usuario);
                ResultSet results2 = statement2.executeQuery(sql_buscaPr);
                ResultSet results3 = statement3.executeQuery(sql_carrito);
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
                        + "$(document).ready(function() {\n"
                        + "\n"
                        + "	$('.image-popup-vertical-fit').magnificPopup({\n"
                        + "		type: 'image',\n"
                        + "		closeOnContentClick: true,\n"
                        + "		mainClass: 'mfp-img-mobile',\n"
                        + "		image: {\n"
                        + "			verticalFit: true\n"
                        + "		}\n"
                        + "		\n"
                        + "	});	\n"
                        + "\n"
                        + "});\n"
                        + "</script>\n"
                        + "\n"
                        + "\n"
                        + "<title>Ver Producto</title>\n"
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






                int cantidad = 0;

                while (results2.next()) {
                    cantidad++;
                    out.println("<div class=\"divDetalleProducto1\">\n"
                            + "     <center><br/>\n"
                            + "     <a class=\"image-popup-vertical-fit\" href=\"imagenes/" + results2.getString("rutaImagen") + "\" title=\"" + results2.getString("nombre") + "\">\n"
                            + "     <img src=\"imagenes/" + results2.getString("rutaImagen") + "\" class=\"imgDetallesProducto\"></img></a>\n"
                            + "     </center>\n"
                            + "    </div>");

                    out.println("<div class=\"divDetalleProducto2\">\n"
                            + "      \n"
                            + "      <span class=\"info1\">Codigo de producto: <font color=\"#333333\">" + results2.getInt("idProducto") + "</font>\n"
                            + "      </span>\n"
                            + "      <span class=\"info2\">Stock de producto: <font color=\"#333333\">" + results2.getInt("stock") + "</font>\n"
                            + "      </span>");


                    out.println("<div class=\"tituloProducto\">\n"
                            + "      <span>" + results2.getString("nombre") + "</span>\n"
                            + "      </div>\n"
                            + "      \n"
                            + "      <br/><br/><br/>\n"
                            + "      \n"
                            + "      <div class=\"detallePrecio\">\n"
                            + "      <span class=\"detallePrecio2\">Precio:\n"
                            + "      </span>\n"
                            + "\n"
                            + "      <span >$ " + df.format(results2.getInt("precio")) + ""
                            + "      </span>\n"
                            + "      </div>\n"
                            + "      <br/>");


                    out.println(" <div class=\"divFormCantidadPedido\">\n"
                            + "      <form action=\"agregardesdedetalle.do\" method=\"post\" name=\"formularioCantidad\">\n"
                            + "  \n"
                            + "  <label for=\"inputCantidad\" >Cantidad :</label>\n"
                            + "<input name=\"inputCantidad\"  type=\"text\" class=\"inputCantidad\" value=\"1\"/>\n"
                            + "<input name=\"idProductoDet\"  type=\"hidden\"  value=\"" + results2.getInt("idProducto") + "\"/><input name=\"usuarioenv\"  type=\"hidden\"  value=\"" + usuario + "\"/><br/><br/>\n"
                            + "<input name=\"submit\" type=\"submit\" value=\"Agregar al Carro\" class=\"botonAgregarCarro\"/> \n"
                            + "\n"
                            + "</form>\n"
                            + "      </div>\n"
                            + "      \n"
                            + "    </div>\n"
                            + "    <div class=\"divDetalleProducto3\">\n"
                            + "    <div class=\"contenidoProducto3\">" + results2.getString("descripcion") + "</div>\n"
                            + "    </div>");

                }

                //out.println("  <td>" + results.getFloat("precio") + "</td>");


                out.println("</div>");
                out.println("</body>");
                out.println("</html>");


                statement.close();
                results.close();
                statement2.close();
                results2.close();
                statement3.close();
                results3.close();
                con.close();

            } catch (SQLException e) {
                System.out.println("Ocurrio un error en la Base de Datos");

            } catch (NullPointerException j) {
            } finally {
                out.close();
            }

        } else {

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("No se pudo cargar el controlador");
            }

            String sql_buscaPr = "select * from productos where idProducto='" + id + "'";
            String sql_usuario = "select * from usuarios where usuario='" + usuario + "'";


            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computadores?allowMultiQueries=true", "root", "asdf123");

                Statement statement2 = con.createStatement();

                //Statement statement2 = con.createStatement();

                ResultSet results2 = statement2.executeQuery(sql_buscaPr);
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
                        + "$(document).ready(function() {\n"
                        + "\n"
                        + "	$('.image-popup-vertical-fit').magnificPopup({\n"
                        + "		type: 'image',\n"
                        + "		closeOnContentClick: true,\n"
                        + "		mainClass: 'mfp-img-mobile',\n"
                        + "		image: {\n"
                        + "			verticalFit: true\n"
                        + "		}\n"
                        + "		\n"
                        + "	});	\n"
                        + "\n"
                        + "});\n"
                        + "</script>\n"
                        + "\n"
                        + "\n"
                        + "<title>Untitled Document</title>\n"
                        + "\n"
                        + "</head>\n"
                        + "<body class=\"fondo\">\n"
                        + "\n"
                        + "<center>\n"
                        + "<div class=\"divLogo\">\n"
                        + "<center>\n"
                        + "<br/>\n"
                        + "<img src=\"imagenes/logo.png\"  />\n"
                        + "</center>\n"
                        + "</div>\n"
                        + "</center>"
                        + "<div class=\"divContenido\">\n"
                        + "\n"
                        + "<div class=\"divMenus\">\n"
                        + "\n"
                        + "  \n"
                        + "  <center>\n"
                        + "  <br/>");



                out.println("<div class=\"divUsuario\" onmouseover=\"this.style.backgroundColor='#E6E6E6';\" onmouseout=\"this.style.backgroundColor='#CECECE';\" >\n"
                        + "  <span>Mi Cuenta</span>\n"
                        + "  <p>Usuario Visitante</p>\n"
                        + "  <br/>\n"
                        + "\n"
                        + "  <form method=\"get\" action=\"registrarse.view\" name=\"formregistrarse\">\n"
                        + "  <INPUT TYPE= \"submit\" VALUE=\"Registrarse\" class=\"botonRegistrarse2\"> \n"
                        + "  </form>\n"
                        + "  <form method=\"get\" action=\"login.view\" name=\"formlogin\">\n"
                        + "  <INPUT TYPE= \"submit\" VALUE=\"Acceder\" class=\"botonAcceder3\"> \n"
                        + "  </form>\n"
                        + "  </div>\n"
                        + "  \n"
                        + "  \n"
                        + "<br/>");



                out.println("\n"
                        + "\n"
                        + "</center>\n"
                        + "\n"
                        + "</div>\n"
                        + "\n"
                        + "<div class=\"divContenedorProductos\">");









                while (results2.next()) {

                    out.println("<div class=\"divDetalleProducto1\">\n"
                            + "     <center><br/>\n"
                            + "     <a class=\"image-popup-vertical-fit\" href=\"imagenes/" + results2.getString("rutaImagen") + "\" title=\"" + results2.getString("nombre") + "\">\n"
                            + "     <img src=\"imagenes/" + results2.getString("rutaImagen") + "\" class=\"imgDetallesProducto\"></img></a>\n"
                            + "     </center>\n"
                            + "    </div>");

                    out.println("<div class=\"divDetalleProducto2\">\n"
                            + "      \n"
                            + "      <span class=\"info1\">Codigo de producto: <font color=\"#333333\">" + results2.getInt("idProducto") + "</font>\n"
                            + "      </span>\n"
                            + "      <span class=\"info2\">Stock de producto: <font color=\"#333333\">" + results2.getInt("stock") + "</font>\n"
                            + "      </span>");


                    out.println("<div class=\"tituloProducto\">\n"
                            + "      <span>" + results2.getString("nombre") + "</span>\n"
                            + "      </div>\n"
                            + "      \n"
                            + "      <br/><br/><br/>\n"
                            + "      \n"
                            + "      <div class=\"detallePrecio\">\n"
                            + "      <span class=\"detallePrecio2\">Precio:\n"
                            + "      </span>\n"
                            + "\n"
                            + "      <span >$ " + df.format(results2.getInt("precio")) + ""
                            + "      </span>\n"
                            + "      </div>\n"
                            + "      <br/>");


                    out.println(" "
                            + "      \n"
                            + "    </div>\n"
                            + "    <div class=\"divDetalleProducto3\">\n"
                            + "    <div class=\"contenidoProducto3\">" + results2.getString("descripcion") + "</div>\n"
                            + "    </div>");

                }

                //out.println("  <td>" + results.getFloat("precio") + "</td>");


                out.println("</div>");
                out.println("</body>");
                out.println("</html>");



                statement2.close();
                results2.close();
                con.close();

            } catch (SQLException e) {
                System.out.println("Ocurrio un error en la Base de Datos");

            } finally {
                out.close();
            }

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
