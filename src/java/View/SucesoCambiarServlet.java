/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TAAVO
 */
public class SucesoCambiarServlet extends HttpServlet {

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
        
            String inputUsuario = (String) request.getAttribute("inputUsuario");
            String inputClave =(String) request.getAttribute("inputClave");
            String inputClave2 =(String) request.getAttribute("inputClave2");
            String inputRut =(String) request.getAttribute("inputRut");
            String inputNombreCompleto =(String) request.getAttribute("inputNombreCompleto");
            String inputDireccion =(String) request.getAttribute("inputDireccion");
            String comboRegiones =(String) request.getAttribute("comboRegiones");
            String comboComunas =(String) request.getAttribute("comboComunas");
            String inputTelefono = (String)request.getAttribute("inputTelefono");
            String inputEmail = (String)request.getAttribute("inputEmail");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        try {
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                    + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                    + "<head>\n"
                    + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                    + "<link type=\"text/css\" rel=\"stylesheet\" href=\"js/estiloProductos.css\" />\n"
                    + "\n"
                    + "<title>Suceso Cambiar Datos</title>\n"
                    + "\n"
                    + "</head>");

            out.println("<body class=\"fondo\">\n"
                    + "<center><br/>\n"
                    + "<div class=\"divRegistrarseSuceso\">\n"
                    + "<center>\n"
                    + "<div class=\"divRegistrarseSuceso2\">\n"
                    + "<form action=\"index.html\" method=\"post\" name=\"formularioRegistro\"><br/>\n"
                    + " <center><span>Ha Cambiado sus Datos</span></center>\n"
                    + " <br/>");

            out.println(" <table border=\"1px\" bordercolor=\"#E2E2E2\">\n"
                    + "<tr>\n"
                    + "<td>Usuario:</td>\n"
                    + "<td><div class=\"divSepararinputs\">"+inputUsuario+"</div></td>\n"
                    + "</tr>\n"
                    + "\n"
                    + "<input type=\"hidden\" name=\"inputOcultoUser\" value=\""+inputUsuario+"\"/>\n"
                    + "\n"
                    + "<tr>\n"
                    + "<td>Clave:</td>\n"
                    + "<td><div class=\"divSepararinputs\">"+inputClave+"</div></td>\n"
                    + "</tr>\n"
                    + "\n"
                    + " <tr> \n"
                    + " <td >RUT:</td>\n"
                    + " <td ><div class=\"divSepararinputs\">"+inputRut+"</div></td>\n"
                    + "</tr>\n"
                    + "\n"
                    + "\n"
                    + "<tr>\n"
                    + "<td>Nombre Completo:</td>\n"
                    + "<td><div class=\"divSepararinputs\">"+inputNombreCompleto+"</div></td>\n"
                    + "</tr>\n"
                    + "\n"
                    + "<tr>\n"
                    + "<td>Dirección:</td>\n"
                    + "<td><div class=\"divSepararinputs\">"+inputDireccion+"</div></td>\n"
                    + "</tr>\n"
                    + "\n"
                    + "<tr>\n"
                    + "<td>Region:</td>\n"
                    + "<td><div class=\"divSepararinputs\">"+comboRegiones+"</div></td>\n"
                    + "</tr>\n"
                    + "\n"
                    + "<tr>\n"
                    + "<td>Comuna:</td>\n"
                    + "<td><div class=\"divSepararinputs\">"+comboComunas+"</div></td>\n"
                    + "</tr>\n"
                    + "\n"
                    + "\n"
                    + "<tr> \n"
                    + " <td >Telefono:</td>\n"
                    + " <td ><div class=\"divSepararinputs\">"+inputTelefono+"</div></td>\n"
                    + "</tr>\n"
                    + "\n"
                    + "<tr>\n"
                    + "<td>E-mail:</td>\n"
                    + "<td><div class=\"divSepararinputs\">"+inputEmail+"</div></td>\n"
                    + "</tr>\n"
                    + "\n"
                    + "\n"
                    + "</table>\n"
                    + " <br/>  "
                    + "<input name=\"submit\" type=\"submit\" value=\"Ver productos\" class=\"botonAcceder\"/>  \n"
                    + "</form>\n"
                    + "\n"
                    + "</div>\n"
                    + "</center>\n"
                    + "</div>\n"
                    + "</center>\n"
                    + "\n"
                    + "</body>\n"
                    + "</html>");
            
            String ja = new String(inputUsuario);
               

                HttpSession session = request.getSession();
                session.putValue("varUsuario", ja);

        } finally {
            out.close();
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
