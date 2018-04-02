<%-- 
    Document   : groups
    Created on : 02-Apr-2018, 11:12:40
    Author     : nacho
--%>

<%@page import="com.mycompany.tutordocs.User"%>
<%@page import="Db.GroupsManagement"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.tutordocs.Group"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MDA Groups</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>
    <body>

        <% List<Group> groups = GroupsManagement.getGroups();
            User user = (User) session.getAttribute("user");
        %>


        <ul>
            <%for (Group group : groups) {
                if (group.isMember(user)) {%>
                    <li><a href="#"><%=group.name()%></a></li>
                <%} else {%>
                    <li><%=group.name()%></li>
                    <form action="RegisterInGroup">
                        <input type="hidden" name="userId" value="<%=user.id()%>">
                        <input type="hidden" name="groupId" value="<%=group.id()%>">
                        <button type="submit">Matricularse</button>
                    </form>
                <%}%>
            <%}%>
        </ul>


        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    </body>
</html>
