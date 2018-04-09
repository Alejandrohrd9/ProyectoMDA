<%-- 
    Document   : groupView
    Created on : 03-Apr-2018, 11:02:38
    Author     : nacho
--%>

<%@page import="com.mycompany.tutordocs.Apuntes"%>
<%@page import="Db.ApuntesManagement"%>
<%@page import="com.mycompany.tutordocs.User"%>
<%@page import="Db.GroupsManagement"%>
<%@page import="com.mycompany.tutordocs.Group"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grupo</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>
    <body>

        <%
            Group group = GroupsManagement.getGroupById(Integer.parseInt(request.getParameter("id")));
            User user = (User) session.getAttribute("user");

            if (!group.isMember(user)) {
                request.getRequestDispatcher("groups.jsp").forward(request, response);
            }
        %>




        <div class="container">
            <div class="row">
                <div class="col-9">
                    <h4>Apuntes</h4>
                    <ul class="list-group">
                        <%for (Apuntes apunte : ApuntesManagement.getApuntesFromGroup(group.id())) {%>
                        <li class="list-group-item"><%=apunte.getTitle()%> - <%=apunte.getDate()%></li>
                            <%}%>
                    </ul>
                    <h4>Cuestionarios</h4>
                </div>
                <div class="col-3">
                    <h4>Miembros del grupo</h4>
                    <ul class="list-group">
                        <%for (String member : group.getMembers()) {%>
                        <li class="list-group-item"><%=member%></li>
                            <%}%>
                    </ul>
                    <div class="p-1">
                        <form action="DeleteGroup">
                            <input type="hidden" name="group" value="<%=group.id()%>">
                            <input type="hidden" name="user" value="<%=user.id()%>">
                            <button type="submit" class="btn btn-danger">Salir del grupo</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</html>
