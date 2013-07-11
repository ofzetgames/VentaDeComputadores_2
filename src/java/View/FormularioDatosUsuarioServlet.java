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
public class FormularioDatosUsuarioServlet extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String usuariof = (String) session.getAttribute("varUsuario");

        if (usuariof != null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("No se pudo cargar el controlador");
            }

            String sql_regiones = "select nombre from regiones";
            String sql_comunas = "select nombre from comunas";
            String sql_user = "select * from usuarios where usuario='" + usuariof + "'";


            try {

                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/computadores?allowMultiQueries=true", "root", "asdf123");
                Statement statement = con.createStatement();
                Statement statement2 = con.createStatement();
                Statement statement3 = con.createStatement();
                ResultSet results = statement.executeQuery(sql_regiones);
                ResultSet results2 = statement2.executeQuery(sql_comunas);
                ResultSet results3 = statement3.executeQuery(sql_user);


                out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                        + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                        + "<head>\n"
                        + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                        + "<link type=\"text/css\" rel=\"stylesheet\" href=\"js/estiloProductos.css\" />\n"
                        + "<link rel=\"stylesheet\" href=\"js/jquery-ui.css\" />\n"
                        + "<script src=\"js/jquery.min.js\"></script>\n"
                        + "<script src=\"js/jquery-ui.js\"></script><script>\n"
                        + "  $(function() {\n"
                        + "    $( \"#dialog-message\" ).dialog({\n"
                        + "   width: 600,   "
                        + "modal: true,\n"
                        + "      buttons: {\n"
                        + "        Ok: function() {\n"
                        + "          $( this ).dialog( \"close\" );\n"
                        + "        }\n"
                        + "      }\n"
                        + "    });\n"
                        + "  });\n"
                        + "  </script>");

                out.println("<style>\n"
                        + "            .custom-combobox {\n"
                        + "                position: relative;\n"
                        + "                display: inline-block;\n"
                        + "\n"
                        + "            }\n"
                        + "            .custom-combobox-toggle {\n"
                        + "                position: absolute;\n"
                        + "                top: 0;\n"
                        + "                bottom: 0;\n"
                        + "                margin-left: -1px;\n"
                        + "                padding: 0;\n"
                        + "                /* support: IE7 */\n"
                        + "                *height: 1.0em;\n"
                        + "                *top: 0.1em;\n"
                        + "            }\n"
                        + "            .custom-combobox-input {\n"
                        + "                margin: 0;\n"
                        + "                padding: 0.1em;\n"
                        + "            }\n"
                        + "        </style>");

                out.println("<script>\n"
                        + "            (function($) {\n"
                        + "                $.widget(\"custom.combobox\", {\n"
                        + "                    _create: function() {\n"
                        + "                        this.wrapper = $(\"<span>\")\n"
                        + "                                .addClass(\"custom-combobox\")\n"
                        + "                                .insertAfter(this.element);\n"
                        + "\n"
                        + "                        this.element.hide();\n"
                        + "                        this._createAutocomplete();\n"
                        + "                        this._createShowAllButton();\n"
                        + "                    },\n"
                        + "                    _createAutocomplete: function() {\n"
                        + "                        var selected = this.element.children(\":selected\"),\n"
                        + "                                value = selected.val() ? selected.text() : \"\";\n"
                        + "\n"
                        + "                        this.input = $(\"<input>\")\n"
                        + "                                .appendTo(this.wrapper)\n"
                        + "                                .val(value)\n"
                        + "                                .attr(\"title\", \"\")\n"
                        + "                                .addClass(\"custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left\")\n"
                        + "                                .autocomplete({\n"
                        + "                            delay: 0,\n"
                        + "                            minLength: 0,\n"
                        + "                            source: $.proxy(this, \"_source\")\n"
                        + "                        })\n"
                        + "                                .tooltip({\n"
                        + "                            tooltipClass: \"ui-state-highlight\"\n"
                        + "                        });\n"
                        + "\n"
                        + "                        this._on(this.input, {\n"
                        + "                            autocompleteselect: function(event, ui) {\n"
                        + "                                ui.item.option.selected = true;\n"
                        + "                                this._trigger(\"select\", event, {\n"
                        + "                                    item: ui.item.option\n"
                        + "                                });\n"
                        + "                            },\n"
                        + "                            autocompletechange: \"_removeIfInvalid\"\n"
                        + "                        });\n"
                        + "                    },\n"
                        + "                    _createShowAllButton: function() {\n"
                        + "                        var input = this.input,\n"
                        + "                                wasOpen = false;\n"
                        + "\n"
                        + "                        $(\"<a>\")\n"
                        + "                                .attr(\"tabIndex\", -1)\n"
                        + "                                .attr(\"title\", \"Mostrar Regiones\")\n"
                        + "                                .tooltip()\n"
                        + "                                .appendTo(this.wrapper)\n"
                        + "                                .button({\n"
                        + "                            icons: {\n"
                        + "                                primary: \"ui-icon-triangle-1-s\"\n"
                        + "                            },\n"
                        + "                            text: false\n"
                        + "                        })\n"
                        + "                                .removeClass(\"ui-corner-all\")\n"
                        + "                                .addClass(\"custom-combobox-toggle ui-corner-right\")\n"
                        + "                                .mousedown(function() {\n"
                        + "                            wasOpen = input.autocomplete(\"widget\").is(\":visible\");\n"
                        + "                        })\n"
                        + "                                .click(function() {\n"
                        + "                            input.focus();\n"
                        + "\n"
                        + "                            // Close if already visible\n"
                        + "                            if (wasOpen) {\n"
                        + "                                return;\n"
                        + "                            }\n"
                        + "\n"
                        + "                            // Pass empty string as value to search for, displaying all results\n"
                        + "                            input.autocomplete(\"search\", \"\");\n"
                        + "                        });\n"
                        + "                    },\n"
                        + "                    _source: function(request, response) {\n"
                        + "                        var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), \"i\");\n"
                        + "                        response(this.element.children(\"option\").map(function() {\n"
                        + "                            var text = $(this).text();\n"
                        + "                            if (this.value && (!request.term || matcher.test(text)))\n"
                        + "                                return {\n"
                        + "                                    label: text,\n"
                        + "                                    value: text,\n"
                        + "                                    option: this\n"
                        + "                                };\n"
                        + "                        }));\n"
                        + "                    },\n"
                        + "                    _removeIfInvalid: function(event, ui) {\n"
                        + "\n"
                        + "                        // Selected an item, nothing to do\n"
                        + "                        if (ui.item) {\n"
                        + "                            return;\n"
                        + "                        }\n"
                        + "\n"
                        + "                        // Search for a match (case-insensitive)\n"
                        + "                        var value = this.input.val(),\n"
                        + "                                valueLowerCase = value.toLowerCase(),\n"
                        + "                                valid = false;\n"
                        + "                        this.element.children(\"option\").each(function() {\n"
                        + "                            if ($(this).text().toLowerCase() === valueLowerCase) {\n"
                        + "                                this.selected = valid = true;\n"
                        + "                                return false;\n"
                        + "                            }\n"
                        + "                        });\n"
                        + "\n"
                        + "                        // Found a match, nothing to do\n"
                        + "                        if (valid) {\n"
                        + "                            return;\n"
                        + "                        }\n"
                        + "\n"
                        + "                        // Remove invalid value\n"
                        + "                        this.input\n"
                        + "                                .val(\"\")\n"
                        + "                                .attr(\"title\", value + \" didn't match any item\")\n"
                        + "                                .tooltip(\"open\");\n"
                        + "                        this.element.val(\"\");\n"
                        + "                        this._delay(function() {\n"
                        + "                            this.input.tooltip(\"close\").attr(\"title\", \"\");\n"
                        + "                        }, 2500);\n"
                        + "                        this.input.data(\"ui-autocomplete\").term = \"\";\n"
                        + "                    },\n"
                        + "                    _destroy: function() {\n"
                        + "                        this.wrapper.remove();\n"
                        + "                        this.element.show();\n"
                        + "                    }\n"
                        + "                });\n"
                        + "            })(jQuery);\n"
                        + "\n"
                        + "            $(function() {\n"
                        + "                $(\"#combobox\").combobox();\n"
                        + "                $(\"#toggle\").click(function() {\n"
                        + "                    $(\"#combobox\").toggle();\n"
                        + "                });\n"
                        + "            });\n"
                        + "        </script>\n"
                        + "        <script>\n"
                        + "            (function($) {\n"
                        + "                $.widget(\"custom.combobox\", {\n"
                        + "                    _create: function() {\n"
                        + "                        this.wrapper = $(\"<span>\")\n"
                        + "                                .addClass(\"custom-combobox\")\n"
                        + "                                .insertAfter(this.element);\n"
                        + "\n"
                        + "                        this.element.hide();\n"
                        + "                        this._createAutocomplete();\n"
                        + "                        this._createShowAllButton();\n"
                        + "                    },\n"
                        + "                    _createAutocomplete: function() {\n"
                        + "                        var selected = this.element.children(\":selected\"),\n"
                        + "                                value = selected.val() ? selected.text() : \"\";\n"
                        + "\n"
                        + "                        this.input = $(\"<input>\")\n"
                        + "                                .appendTo(this.wrapper)\n"
                        + "                                .val(value)\n"
                        + "                                .attr(\"title\", \"\")\n"
                        + "                                .addClass(\"custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left\")\n"
                        + "                                .autocomplete({\n"
                        + "                            delay: 0,\n"
                        + "                            minLength: 0,\n"
                        + "                            source: $.proxy(this, \"_source\")\n"
                        + "                        })\n"
                        + "                                .tooltip({\n"
                        + "                            tooltipClass: \"ui-state-highlight\"\n"
                        + "                        });\n"
                        + "\n"
                        + "                        this._on(this.input, {\n"
                        + "                            autocompleteselect: function(event, ui) {\n"
                        + "                                ui.item.option.selected = true;\n"
                        + "                                this._trigger(\"select\", event, {\n"
                        + "                                    item: ui.item.option\n"
                        + "                                });\n"
                        + "                            },\n"
                        + "                            autocompletechange: \"_removeIfInvalid\"\n"
                        + "                        });\n"
                        + "                    },\n"
                        + "                    _createShowAllButton: function() {\n"
                        + "                        var input = this.input,\n"
                        + "                                wasOpen = false;\n"
                        + "\n"
                        + "                        $(\"<a>\")\n"
                        + "                                .attr(\"tabIndex\", -1)\n"
                        + "                                .attr(\"title\", \"Mostrar Comunas\")\n"
                        + "                                .tooltip()\n"
                        + "                                .appendTo(this.wrapper)\n"
                        + "                                .button({\n"
                        + "                            icons: {\n"
                        + "                                primary: \"ui-icon-triangle-1-s\"\n"
                        + "                            },\n"
                        + "                            text: false\n"
                        + "                        })\n"
                        + "                                .removeClass(\"ui-corner-all\")\n"
                        + "                                .addClass(\"custom-combobox-toggle ui-corner-right\")\n"
                        + "                                .mousedown(function() {\n"
                        + "                            wasOpen = input.autocomplete(\"widget\").is(\":visible\");\n"
                        + "                        })\n"
                        + "                                .click(function() {\n"
                        + "                            input.focus();\n"
                        + "\n"
                        + "                            // Close if already visible\n"
                        + "                            if (wasOpen) {\n"
                        + "                                return;\n"
                        + "                            }\n"
                        + "\n"
                        + "                            // Pass empty string as value to search for, displaying all results\n"
                        + "                            input.autocomplete(\"search\", \"\");\n"
                        + "                        });\n"
                        + "                    },\n"
                        + "                    _source: function(request, response) {\n"
                        + "                        var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), \"i\");\n"
                        + "                        response(this.element.children(\"option\").map(function() {\n"
                        + "                            var text = $(this).text();\n"
                        + "                            if (this.value && (!request.term || matcher.test(text)))\n"
                        + "                                return {\n"
                        + "                                    label: text,\n"
                        + "                                    value: text,\n"
                        + "                                    option: this\n"
                        + "                                };\n"
                        + "                        }));\n"
                        + "                    },\n"
                        + "                    _removeIfInvalid: function(event, ui) {\n"
                        + "\n"
                        + "                        // Selected an item, nothing to do\n"
                        + "                        if (ui.item) {\n"
                        + "                            return;\n"
                        + "                        }\n"
                        + "\n"
                        + "                        // Search for a match (case-insensitive)\n"
                        + "                        var value = this.input.val(),\n"
                        + "                                valueLowerCase = value.toLowerCase(),\n"
                        + "                                valid = false;\n"
                        + "                        this.element.children(\"option\").each(function() {\n"
                        + "                            if ($(this).text().toLowerCase() === valueLowerCase) {\n"
                        + "                                this.selected = valid = true;\n"
                        + "                                return false;\n"
                        + "                            }\n"
                        + "                        });\n"
                        + "\n"
                        + "                        // Found a match, nothing to do\n"
                        + "                        if (valid) {\n"
                        + "                            return;\n"
                        + "                        }\n"
                        + "\n"
                        + "                        // Remove invalid value\n"
                        + "                        this.input\n"
                        + "                                .val(\"\")\n"
                        + "                                .attr(\"title\", value + \" didn't match any item\")\n"
                        + "                                .tooltip(\"open\");\n"
                        + "                        this.element.val(\"\");\n"
                        + "                        this._delay(function() {\n"
                        + "                            this.input.tooltip(\"close\").attr(\"title\", \"\");\n"
                        + "                        }, 2500);\n"
                        + "                        this.input.data(\"ui-autocomplete\").term = \"\";\n"
                        + "                    },\n"
                        + "                    _destroy: function() {\n"
                        + "                        this.wrapper.remove();\n"
                        + "                        this.element.show();\n"
                        + "                    }\n"
                        + "                });\n"
                        + "            })(jQuery);\n"
                        + "\n"
                        + "            $(function() {\n"
                        + "                $(\"#combobox2\").combobox();\n"
                        + "                $(\"#toggle2\").click(function() {\n"
                        + "                    $(\"#combobox2\").toggle();\n"
                        + "                });\n"
                        + "            });\n"
                        + "        </script>");

                out.println("<title>Formulario de Registro de Usuario</title>\n"
                        + "\n"
                        + "</head>\n"
                        + "\n"
                        + "\n"
                        + "<body class=\"fondo\">");

                //hasta el body

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

                String inputUsuario = request.getParameter("inputUsuario");
                String inputClave = request.getParameter("inputClave");
                String inputClave2 = request.getParameter("inputClave2");
                String inputRut = request.getParameter("inputRut");
                String inputNombreCompleto = request.getParameter("inputNombreCompleto");
                String inputDireccion = request.getParameter("inputDireccion");
                String comboRegiones = request.getParameter("comboRegiones");
                String comboComunas = request.getParameter("comboComunas");
                String inputTelefono = request.getParameter("inputTelefono");
                String inputEmail = request.getParameter("inputEmail");


                while (results3.next()) {




                    out.println("<center><br/>\n"
                            + "<div class=\"divRegistrarse\">\n"
                            + "<center>\n"
                            + "<div class=\"divRegistrarse2\">\n"
                            + "<form action=\"cambiardatos.do\" method=\"post\" name=\"formularioDatos\">");

                    out.print(" <table>\n"
                            + "<tr>\n"
                            + "<td><label for=\"inputUsuario\">Usuario:</label></td>\n"
                            + "<td><div class=\"divSepararinputs\"><input name=\"inputUsuario\" OnFocus=\"this.blur()\" type=\"text\" class=\"inputUseryPass\" ");
                    if (inputUsuario == null) {
                        inputUsuario = "";
                    }
                    out.println("value = '" + results3.getString("usuario") + "' /></div></td></tr>\n");


                    out.println("<tr>\n"
                            + "<td><label for=\"inputClave\">Clave:</label></td>\n"
                            + "<td><div class=\"divSepararinputs\"><input name=\"inputClave\"  type=\"password\" class=\"inputUseryPass\" ");

                    if (inputClave == null) {
                        inputClave = "";
                    }
                    out.println("value = '" + results3.getString("clave") + "' /></div></td></tr>\n");


                    out.println("<tr>\n"
                            + "<td><label for=\"inputClave2\">Reingrese Clave:</label></td>\n"
                            + "<td><div class=\"divSepararinputs\"><input name=\"inputClave2\"  type=\"password\" class=\"inputUseryPass\" ");

                    if (inputClave2 == null) {
                        inputClave2 = "";
                    }
                    out.println("value = '" + results3.getString("clave") + "' /></div></td></tr>\n");


                    out.println(" <tr> \n"
                            + " <td >RUT:</td>\n"
                            + " <td ><div class=\"divSepararinputs\"><input  type=\"text\" name=\"inputRut\" class=\"inputUseryPass\" ");

                    if (inputRut == null) {
                        inputRut = "";
                    }
                    out.println("value = '" + results3.getString("rut") + "' /></div></td></tr>\n");


                    out.println("<tr>\n"
                            + "<td><label for=\"inputNombreCompleto\">Nombre Completo:</label></td>\n"
                            + "<td><div class=\"divSepararinputs\"><input name=\"inputNombreCompleto\" size=\"40px\" type=\"text\" class=\"inputUseryPass\" ");

                    if (inputNombreCompleto == null) {
                        inputNombreCompleto = "";
                    }
                    out.println("value = '" + results3.getString("nombreCompleto") + "' /></div></td></tr>\n");


                    out.println("<tr>\n"
                            + "<td><label for=\"inputDireccion\">Dirección:</label></td>\n"
                            + "<td><div class=\"divSepararinputs\"><input name=\"inputDireccion\" size=\"40px\" type=\"text\" class=\"inputUseryPass\" ");
                    if (inputDireccion == null) {
                        inputDireccion = "";
                    }
                    out.println("value = '" + results3.getString("direccion") + "' /></div></td></tr>\n");

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
                    ///////////////Regiones/////////////////////////////////////////

                    out.println("<tr>\n"
                            + "<td><label for=\"comboRegiones\">Region:</label></td>\n"
                            + "<td><div class=\"divSepararinputs\"><SELECT NAME=\"comboRegiones\" SIZE=1 id=\"combobox\" class=\"inputUseryPass\">\n"
                            + "<option value=\"" + results3.getString("region") + "\" selected>" + results3.getString("region") + "</option>");
                    while (results.next()) {

                        out.println("<option value=\"" + results.getString("nombre") + "\">" + results.getString("nombre") + "</option>");

                    }
                    out.println("</SELECT></div></td>\n"
                            + "</tr>");



                    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
                    ///////////////Comunas/////////////////////////////////////////

                    out.println("<tr>\n"
                            + "<td><label for=\"comboComunas\">Comuna:</label></td>\n"
                            + "<td><div class=\"divSepararinputs\"><SELECT NAME=\"comboComunas\" SIZE=1 id=\"combobox2\" class=\"inputUseryPass\">\n"
                            + "<option value=\"" + results3.getString("comuna") + "\" selected>" + results3.getString("comuna") + "</option>");
                    while (results2.next()) {

                        out.println("<option value=\"" + results2.getString("nombre") + "\">" + results2.getString("nombre") + "</option>");

                    }
                    out.println("</SELECT></div></td>\n"
                            + "</tr>");


                    out.println("<tr> \n"
                            + " <td >Telefono:</td>\n"
                            + " <td ><div class=\"divSepararinputs\"><input type=\"text\" name=\"inputTelefono\" class=\"inputUseryPass\" ");
                    if (inputTelefono == null) {
                        inputTelefono = "";
                    }
                    out.println("value = '" + results3.getString("telefono") + "' /></div></td></tr>\n");


                    out.println("<tr>\n"
                            + "<td><label for=\"inputEmail\">E-mail:</label></td>\n"
                            + "<td><div class=\"divSepararinputs\"><input name=\"inputEmail\" size=\"40px\" type=\"text\" class=\"inputUseryPass\" ");
                    if (inputEmail == null) {
                        inputEmail = "";
                    }
                    out.println("value = '" + results3.getString("email") + "' /></div></td></tr>\n");


                    out.println("</table>\n"
                            + " <br/>  \n"
                            + "<input name=\"submit\" type=\"submit\" value=\"Guardar\" class=\"botonAcceder2\"/>  \n"
                            + "</form>");

                }

                out.println("</div>\n"
                        + "</center>\n"
                        + "</div>\n"
                        + "</center>\n"
                        + "\n"
                        + "</body>\n"
                        + "</html>");

                statement.close();
                results.close();
                statement3.close();
                results3.close();
                statement2.close();
                results2.close();
                con.close();

            } catch (SQLException e) {
                System.out.println("Ocurrio un error en la Base de Datos");

            } finally {
                out.close();
            }
        } else {

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out2 = response.getWriter();

            out2.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
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
