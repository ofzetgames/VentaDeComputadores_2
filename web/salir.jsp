<%-- 
    Document   : salir
    Created on : 21-jun-2013, 23:17:28
    Author     : TAAVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%session.invalidate();

     out.println("<script>alert('Cerrando sesion')</script>");
     out.println("<meta http-equiv='refresh' content='0;url=index.html'");

%>
    </body>
</html>
