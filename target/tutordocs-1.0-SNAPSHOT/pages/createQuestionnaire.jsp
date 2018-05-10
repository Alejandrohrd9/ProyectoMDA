<%-- 
    Document   : createQuestionnaire
    Created on : 23-abr-2018, 10:53:24
    Author     : alejandrohd
--%>

<%@page import="com.mycompany.tutordocs.User"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="Db.CuestionariosManagement"%>
<%@page import="Db.GroupsManagement"%>
<%@page import="com.mycompany.tutordocs.Group"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MDA Create Questionnaire</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/main.css">
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");

        %>
        <nav class="navbar navbar-light navbar__user fixed-top">
            <a class="navbar-brand" href="#">
                <img src="../images/logo_inverse.png"  class="d-inline-block align-top" alt="">
            </a>
            <div>

            </div>
        </nav>
        <div class="container container_user">
            <%                if (request.getParameter("qTitle") == null) {
            %>
            <form>
                <label for="so2">Nombre del cuestionario:</label>
                <input type="text" name="qTitle">
                <input type="hidden" name="groupId" value="<%=request.getParameter("group")%>"
                       <input type="hidden" name="titleQ" value="<%=request.getParameter("qTitle")%>">
                <button type="submit" name="generateQ" class="btn btn-primary">Generar cuestionario</button>
            </form>

            <%}
                if (request.getParameter("generateQ") != null) {
                    Group group = GroupsManagement.getGroupById(Integer.parseInt(request.getParameter("groupId")));
                    session.setAttribute("groupid", group.id());
                    Date date = new Date();
                    Timestamp timestamp = new Timestamp(date.getTime());
                    CuestionariosManagement.insertCuestionario(request.getParameter("qTitle"), timestamp, user.id(), Integer.parseInt(request.getParameter("groupId")));
                    response.sendRedirect("createQuestions.jsp?title=" + request.getParameter("qTitle"));

                }
            %>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    </body>
</html>
