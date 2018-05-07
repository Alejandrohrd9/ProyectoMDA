<%-- 
    Document   : SeeMark
    Created on : 25-abr-2018, 16:25:22
    Author     : Nestor
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="com.mycompany.tutordocs.Group"%>
<%@page import="com.mycompany.tutordocs.User"%>
<%@page import="Db.GroupsManagement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Ver calificación</title>
    </head>
    <body>
    <%
        if(request.getParameter("idGrupo") ==null || session.getAttribute("user") == null){
            request.getRequestDispatcher("pages/landingPage.jsp").forward(request, response);
        }
        String idGrupo=request.getParameter("idGrupo");
        Group group = GroupsManagement.getGroupById(Integer.parseInt(idGrupo)); 
        User user = (User) session.getAttribute("user");

        if (!group.isMember(user) && !GroupsManagement.getType(user.id()).equals("Estudiante")) {
            request.getRequestDispatcher("pages/users.jsp").forward(request, response);
        }
        String titulo=(String)request.getAttribute("titulo");
        String nota=(String)request.getAttribute("nota"); 
        Double notaD=Double.parseDouble(nota);
        DecimalFormat df=new DecimalFormat("##.##");
    %>
        <h4>Calificación del cuestionario</h4>
        <table>
            <thead>
                <tr>
                    <th>Cuestionario</th>
                    <th>Calificación</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%=titulo%></td>
                    <td><%=df.format(notaD)%></td>
                </tr>
            </tbody>
        </table>
        <a href="groupView.jsp?id=<%=idGrupo%>">Volver</a>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
