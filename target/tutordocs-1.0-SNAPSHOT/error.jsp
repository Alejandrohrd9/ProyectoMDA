
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error de conexión</h1>
        <%=request.getSession().getAttribute("name")%><br>
        <%=request.getSession().getAttribute("pass")%>
    </body>
</html>
