/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.io.PrintWriter;
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
public class HomeServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String usuarion = (String) session.getAttribute("varUsuario");

        PrintWriter out = response.getWriter();

        if (usuarion == null) {
            try {

                out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                        + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                        + "<head>\n"
                        + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                        + "<link type=\"text/css\" rel=\"stylesheet\" href=\"js/estiloProductos.css\" /><script src=\"js/jquery.min.js\"></script><script src=\"js/jquery-1.9.1.js\"></script><script src=\"js/jquery-ui.js\"></script><link rel=\"stylesheet\" href=\"js/jquery-ui.css\" />\n"
                        + "<title>Acceder</title>\n"
                        + "\n"
                        + "<script>\n"
                        + "  $(function() {\n"
                        + "    $( \"#dialog-message\" ).dialog({\n"
                        + "     width: 600, "
                        + "modal: true,\n"
                        + "      buttons: {\n"
                        + "        Ok: function() {\n"
                        + "          $( this ).dialog( \"close\" );\n"
                        + "        }\n"
                        + "      }\n"
                        + "    });\n"
                        + "  });\n"
                        + "  </script></head><body class=\"fondo\">");

                List errorMsgs = (List) request.getAttribute("errorMsgs");
                if (errorMsgs != null) {
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


                out.println("\n"
                        + "<div class=\"divLogin\">\n"
                        + "<center>\n"
                        + "<br/>\n"
                        + "<div class=\"divLogin2\">");



                String usuario = request.getParameter("usuario");
                String clave = request.getParameter("clave");




                out.println("<form action=\"logear.do\" method=\"post\" name=\"formularioLogeo\">\n"
                        + "  <br/><br/>");
                out.println("  <label for=\"inputUsuario\" >Usuario</label>\n"
                        + "<input name=\"usuario\"  type=\"text\" class=\"inputUseryPass\" ");
                if (usuario == null) {
                    usuario = "";
                }
                out.println("value = '" + usuario + "' /> <br/><br/>");

                out.println("<label for=\"inputClave\" >Clave</label>\n"
                        + "    <input name=\"clave\" type=\"password\" class=\"inputUseryPass\" ");
                if (clave == null) {
                    clave = "";
                }
                out.println("value = '" + clave + "' /> <br/><br/><br/>");

                out.println("<input type=\"button\" onclick=\" location.href='registrarse.view' \" value=\"Registrarse\" name=\"botonRegistrarse\" class=\"botonRegistrarse\"/>");

                out.println("<input name=\"submit\" type=\"submit\" value=\"Acceder\" class=\"botonAcceder\"/>");
                out.println("</form>"
                        + "\n"
                        + "</div>\n"
                        + "</center>\n"
                        + "</div>\n"
                        + "\n"
                        + "</body>");

                out.println("</html>");


                /*
                 out.println("<html>");
                 out.println("<head>");
                 out.println("<title>Aplicacion Dvds</title>");
                 out.println("<body bgcolor='white'>");

                 out.println("<h1>Aplicación Dvds</h1>");

                 out.println("<ul>");
                 out.println("  <li><a href='"
                 + response.encodeURL("listado_productos.view") + "'>Listado Productos</a></li>");
                 out.println("  <li><a href='"
                 + response.encodeURL("add_dvd.view") + "'>Agregar un Dvd a la colección</a></li>");
                 out.println("  <li><a href='"
                 + response.encodeURL("set_prefs.view") + "'>Conjunto de Preferencias del Usuario</a></li>");
                 out.println("</ul>");

                 out.println("</body>");
                 out.println("</html>"); */
            } finally {
                out.close();
            }
        } else {
            out.println("<script>alert('Ya estas Logeado!')</script>");
            out.println("<body>\n"
                    + "<script type=\"text/javascript\">\n"
                    + "window.location=\"listado_productos.view\";\n"
                    + "</script>\n"
                    + "</body>");
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
