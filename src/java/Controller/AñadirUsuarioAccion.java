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
public class AñadirUsuarioAccion extends org.apache.struts.action.Action {

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

        String sql_buscaUser = "select usuario,email,rut from usuarios";

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computadores?allowMultiQueries=true", "root", "asdf123");
            Statement statement = con.createStatement();
            ResultSet results = statement.executeQuery(sql_buscaUser);


            
            String inputUsuario = request.getParameter("inputUsuario").trim();
            String inputClave = request.getParameter("inputClave").trim();
            String inputClave2 = request.getParameter("inputClave2").trim();
            String inputRut = request.getParameter("inputRut");
            String inputNombreCompleto = request.getParameter("inputNombreCompleto");
            String inputDireccion = request.getParameter("inputDireccion");
            String comboRegiones = request.getParameter("comboRegiones");
            String comboComunas = request.getParameter("comboComunas");
            String inputTelefono = request.getParameter("inputTelefono");
            String inputEmail = request.getParameter("inputEmail").trim();



           
            if (inputUsuario == null || inputUsuario.trim().length() == 0) {
                errorMsgs.add("Por favor ingrese el Usuario.");
            }
            while (results.next()) {
                if (inputUsuario.equals(results.getString("usuario")) || inputEmail.equals(results.getString("email"))) {

                    errorMsgs.add("El Usuario o el Email ya existen.");
                }
                if (inputRut.equals(results.getString("rut"))) {

                    errorMsgs.add("El Rut ya existe.");
                }
            }

            if (inputClave == null || inputClave.trim().length() == 0) {
                errorMsgs.add("Por favor ingrese la Clave.");
            }
            if (inputClave2 == null || inputClave2.trim().length() == 0) {
                errorMsgs.add("Por favor confirme la Clave.");
            }
            if (!inputClave.equals(inputClave2)) {
                errorMsgs.add("Las Claves no coinciden.");
            }
            if (inputRut == null || inputRut.trim().length() == 0) {
                errorMsgs.add("Por favor ingrese Rut.");
            }
            if (inputNombreCompleto == null || inputNombreCompleto.trim().length() == 0) {
                errorMsgs.add("Por favor ingrese el Nombre.");
            }
            if (inputDireccion == null || inputDireccion.trim().length() == 0) {
                errorMsgs.add("Por favor ingrese la Direccion.");
            }
            if (comboRegiones == null || comboRegiones.trim().length() == 0) {
                errorMsgs.add("Por favor elija una Region.");
            }
            if (comboComunas == null || comboComunas.trim().length() == 0) {
                errorMsgs.add("Por favor elija una Comuna.");
            }
            if (inputTelefono == null || inputTelefono.trim().length() == 0) {
                errorMsgs.add("Por favor ingrese el Telefono.");
            }
            if (inputEmail == null || inputEmail.trim().length() == 0) {
                errorMsgs.add("Por favor ingrese el Email.");
            }


            if (!errorMsgs.isEmpty()) {
                request.setAttribute("errorMsgs", errorMsgs);
                return mapping.findForward("error"); 
            }

            
            //String sql_inserta = "insert into usuarios values('','" + inputUsuario + "','" + inputClave + "','" + inputRut + "','" + inputNombreCompleto + "','"+inputDireccion+"','"+comboRegiones+"','"+comboComunas+"','"+inputTelefono+"','"+inputEmail+"')";
            String sql_inserta = "insert into usuarios(usuario,clave,rut,nombreCompleto,direccion,region,comuna,telefono,email) values ( ? , ? , ? , ? , ? , ? , ? , ? , ?)";
            PreparedStatement statement2 = con.prepareStatement(sql_inserta);



            statement2.setString(1, inputUsuario);
            statement2.setString(2, inputClave);
            statement2.setString(3, inputRut);
            statement2.setString(4, inputNombreCompleto);
            statement2.setString(5, inputDireccion);
            statement2.setString(6, comboRegiones);
            statement2.setString(7, comboComunas);
            statement2.setString(8, inputTelefono);
            statement2.setString(9, inputEmail);



            statement2.executeUpdate();
            statement.close();
            statement2.close();
            con.close();


      
            request.setAttribute("inputUsuario", inputUsuario);
            request.setAttribute("inputClave", inputClave);
            request.setAttribute("inputClave2", inputClave2);
            request.setAttribute("inputRut", inputRut);
            request.setAttribute("inputNombreCompleto", inputNombreCompleto);
            request.setAttribute("inputDireccion", inputDireccion);
            request.setAttribute("comboRegiones", comboRegiones);
            request.setAttribute("comboComunas", comboComunas);
            request.setAttribute("inputTelefono", inputTelefono);
            request.setAttribute("inputEmail", inputEmail);


           
            return mapping.findForward("success");

        } catch (RuntimeException e) {
            errorMsgs.add("An unexpected error: " + e.getMessage());
            request.setAttribute("errorMsgs", errorMsgs);
            return mapping.findForward("error");
        }

    }
}
