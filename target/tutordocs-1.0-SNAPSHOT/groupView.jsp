<%-- 
    Document   : groupView
    Created on : 03-Apr-2018, 11:02:38
    Author     : nacho
--%>

<%@page import="com.mycompany.tutordocs.CarpetaEjercicio"%>
<%@page import="Db.ExcercisesManagement"%>
<%@page import="com.mycompany.tutordocs.Cuestionario"%>
<%@page import="Db.CuestionariosManagement"%>
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
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>

        <%
            Group group = GroupsManagement.getGroupById(Integer.parseInt(request.getParameter("id")));
            User user = (User) session.getAttribute("user");

            if (!group.isMember(user)) {
                request.getRequestDispatcher("groups.jsp").forward(request, response);
            }
        %>

        <nav class="navbar navbar-light navbar__user fixed-top">
            <a class="navbar-brand" href="#">
                <img src="images/logo_inverse.png"  class="d-inline-block align-top" alt="">
            </a>

        </nav>
        <div class="container-fluid container_user">
            <div class="row align-items-start">
                <div class="col-2">
                    <div class="col-md-2 col-sm-3 sidebar">
                        <div class="user__card">
                            <div>
                                <img class="user__card__img" src="images/person1.jpg">
                            </div>
                            <div class="user__card__nameuser"></div>
                        </div>
                        <ul class="nav nav-pills flex-column">
                            <li class="nav-item">
                                <a class="nav-link" href="users.jsp">Cursos<span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="users_group.jsp">Grupos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Analytics</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Cerrar Sesion</a>
                            </li>
                        </ul>
                    </div>               
                </div>

                <div class="col-7">
                    <h4>Apuntes</h4>
                    <ul class="list-group">
                        <%for (Apuntes apunte : ApuntesManagement.getApuntesFromGroup(group.id())) {%>
                        <li class="list-group-item"><a href="<%=apunte.getLink()%>"><%=apunte.getTitle()%></a> - <%=apunte.getDate()%></li>

                        <form action="DeleteNote">
                            <input type="hidden" name="id" value="<%=apunte.getId()%>">
                            <input type="submit" class="btn btn-danger" value="Borrar Apunte">
                        </form>
                        <%}%>
                    </ul>

                    <button id="buttonUpload" type="button" class="btn btn-outline-primary m-2" onclick="displayUpload()">Subir archivo</button>
                    <div style="display: none" id="uploadDiv" class="mb-2">
                        <form action="DropboxServlet?userId=<%=user.id()%>&groupId=<%=request.getParameter("id")%>" enctype="multipart/form-data" method="post">
                        <input type="hidden" name="groupName" value="<%=request.getParameter("groupName")%>" class="m-2">
                            <input type="file" required="required" name="file" id="file"class="btn btn-outline-dark m-2"/>
                            <button type="submit" value="Upload" class="btn btn-outline-dark m-2"/>Subir</button>
                        </form>
                    </div>       
                                        <h4>Cuestionarios</h4>
                    <ul class="list-group">
                        <%if (user.typeUser().equals("Profesor")) {%>
                        <form action="pages/createQuestionnaire.jsp">
                            <div class="form-group row">
                                <input type="hidden" name="group" value="<%=group.id()%>">
                                <button type="submit" class="btn btn-primary">Crear cuestionario</button>
                            </div>
                        </form>
                        <%} else {%>
                        <%for (Cuestionario cuestionario : CuestionariosManagement.getGroupCuestionarios(group.id())) {%>
                        <li class="list-group-item"><%=cuestionario.getTitle()%> - <%=cuestionario.getDate()%></li>
                        <form action="cuestionario.jsp">
                            <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                            <input type="hidden" name="idCuestionario" value="<%=cuestionario.getId()%>">
                            <input type="hidden" name="titulo" value="<%=cuestionario.getTitle()%>">
                            <input type="submit" class="btn btn-danger" value="Comenzar cuestionario">
                        </form>
                        <%}
                        }%>
                    </ul>

                    <h4>Ejercicios</h4>
                    <%
                        if (user.getType().equals("Profesor")) {
                    %>
                    <form action="DropboxServlet?groupId=<%=group.id()%>&userId=<%=user.id()%>&uploadExcercise=true&createExcercise=true" enctype="multipart/form-data" method="post">
                        <h1>Crear entrega</h1>
                        <input type="hidden" name="groupName" value="<%=request.getParameter("groupName")%>/Ejercicios">
                        Nombre de la carpeta: <input type = "text" name="excerciseName"><br>
                        <input type="file" required="required" name="file" id="file"/><br/>
                        <input type="submit" value="Upload" />
                    </form>
                    <%
                        }
                    %>
                    <ul class="list-group">
                        <%for (CarpetaEjercicio excercise : ExcercisesManagement.getExcercisesFolder(group.id())) {%>
                        <a href="excercises.jsp?idExcerciseFolder=<%=excercise.getId()%>&userId=<%=user.id()%>&excercise=<%=excercise.getTitle()%>&path=<%=request.getParameter("groupName")%>/Ejercicios/&groupId=<%=group.id()%>"><li class="list-group-item"><%=excercise.getTitle()%></li></a>
                                <%}%>
                    </ul>

                </div>
                    
                <div class="col-3">
                    <h4>Miembros del grupo</h4>
                    <ul class="list-group">
                        <%for (String member : group.getMembers()) {%>
                        <li class="list-group-item"><a href="pages/exploreProfile.jsp?target=<%=member%>"><%=member%></li>
                            <%}%>
                    </ul>
                    <div class="p-1">
                        <form action="DeleteGroup">
                            <input type="hidden" name="group" value="<%=group.id()%>">
                            <input type="hidden" name="user" value="<%=user.id()%>">
                            <button type="submit" class="btn btn-danger">Salir del grupo</button>
                        </form>
                        <a role="button" class="btn btn-outline-primary mt-1" href="pages/forum_display.jsp?id=<%=group.id()%>">Foro del grupo
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="container mt-3">
            <a href="pages/users_group.jsp">Volver</a>
        </div>
    </body>


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <script>
                        function displayUpload() {
                            jQuery('#uploadDiv').toggle();
                            if ($('#buttonUpload').text() === "Subir archivo") {
                                $('#buttonUpload').text("Cancelar");
                            } else if ($('#buttonUpload').text() === "Cancelar") {
                                $('#buttonUpload').text("Subir archivo");
                            }
                        }
    </script>    
</html>
