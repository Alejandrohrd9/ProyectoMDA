<%-- 
    Document   : excercises
    Created on : 24-abr-2018, 10:56:01
    Author     : Yisus95
--%>

<%@page import="Db.Dropbox"%>
<%@page import="Db.DatabaseConnection"%>
<%@page import="Db.GroupsManagement"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.tutordocs.Group"%>
<%@page import="Db.ExcercisesManagement"%>
<%@page import="com.mycompany.tutordocs.Ejercicio"%>
<%@page import="com.mycompany.tutordocs.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <title>Excercises</title>
    </head>
    <body>

        <%
            User user = (User) session.getAttribute("user");
            %>
        
        <nav class="navbar navbar-light navbar__user fixed-top">
            <a class="navbar-brand" href="#">
                <img src="images/logo_inverse.png"  class="d-inline-block align-top" alt="">
            </a>
            <div>

            </div>
        </nav>
        <div class="container container_user">
            <div class="row">
                <div class="col-md-2">
                    <div class="col-md-2 col-sm-3 d-none d-sm-block sidebar">
                        <div class="user__card">
                            <div>
                                <img src="<%=user.getImage()%>" class="img-responsive rounded-circle" alt="Profile image" style="margin-top: 5px; object-fit: cover; width:65px; height:65px;">
                            </div>
                            <div class="user__card__nameuser"><a href="pages/userProfile.jsp"><%=user.username()%></a></div>
                        </div>
                        <ul class="nav nav-pills flex-column">
                            <li class="nav-item">
                                <a class="nav-link" href="users.jsp">Cursos<span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="users_group.jsp">Grupos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="pages/noticeBoard.jsp">Tablón</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="LogOutServlet">Cerrar Sesion</a>
                            </li>
                        </ul>
                    </div>               
                </div>
            </div>
        </div>

        <%
            

            String path = (String) request.getParameter("path");

            String linkExcercise = ExcercisesManagement.getLinkExcerciseFolder(Integer.parseInt(request.getParameter("idExcerciseFolder")));
            if (!user.getType().equals("Profesor")) {
                int id = user.id();
        %>
        <div class="col-md-1"></div>
        <div class="row setup-content" id="step-10">
            <div class="container-fluid w-50 ">
                <h3 class="font-weight-bold pl-0 my-4"><strong>Entrega de la tarea <%=request.getParameter("excercise")%></strong></h3>
                <h4>Enunciado de la tarea: 
                    <a href="<%=linkExcercise%>">
                        <%=linkExcercise.substring(linkExcercise.lastIndexOf("/") + 1, linkExcercise.lastIndexOf("?")).replace("%20", " ")%>
                    </a>
                </h4>
                <table id="example" class="table table-bordered">

                    <%
                        String link = ExcercisesManagement.getLink(id, Integer.parseInt(request.getParameter("idExcerciseFolder")));
                        if (!link.equals("No enviado")) {
                            String tarea = link.substring(link.lastIndexOf("/") + 1, link.lastIndexOf("?")).replace("%20", " ");
                    %>
                    <tr>
                        <td>
                            Tarea:
                        </td>
                        <td>
                            <a href="<%=link%>" ><%=tarea%></a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Calificación
                        </td>
                        <td>
                            <%=ExcercisesManagement.getCalification(id)%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Comentarios
                        </td>
                        <td>
                            <%=ExcercisesManagement.getComments(id)%>
                        </td>
                    </tr>
                    <tr>
                        <td >
                            Actualizar entrega
                        </td>
                        <td>
                            <form action="DropboxServlet?tarea=<%=tarea%>&userId=<%=id%>&idExcerciseFolder=<%=request.getParameter("idExcerciseFolder")%>&excercise=<%=request.getParameter("excercise")%>&groupId=<%=request.getParameter("groupId")%>&updateExcercise=true&uploadExcergroupIcise=true" enctype="multipart/form-data" method="post">
                                <input  name="groupName" value="<%=request.getParameter("path") + request.getParameter("excercise")%>" hidden>
                                <input type="file" required="required" name="file" id="file"/><br>
                                <input type="submit" value="Actualizar Entrega" />
                            </form>
                        </td>
                    </tr>

                    <%} else {%>
                    <tr>
                        <td>
                            Tarea:
                        </td>
                        <td>
                            No entregado
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Calificación
                        </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            Comentarios
                        </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td >
                            Enviar archivos
                        </td>
                        <td>
                            <form action="DropboxServlet?userId=<%=request.getParameter("userId")%>&groupId=<%=request.getParameter("groupId")%>&excerciseName=<%=request.getParameter("excercise")%>&idExcerciseFolder=<%=request.getParameter("idExcerciseFolder")%>&uploadExcercise=true" enctype="multipart/form-data" method="post">
                                <input  name="groupName" value="<%=request.getParameter("path") + request.getParameter("excercise")%>" hidden>
                                <input type="file" required="required" name="file" id="file"/><br>
                                <input type="submit" value="Subir entrega" />
                            </form>
                        </td>
                    </tr>
                    <%}%>

                </table>
            </div>
        </div>
        <%} else {
            String grupo = path.substring(0, path.indexOf("/"));
            Group group = new Group(Integer.parseInt(request.getParameter("groupId")), grupo, "");
            GroupsManagement.getMembers(group);
            List<String> members = group.getMembers();
            List<Ejercicio> excercises = ExcercisesManagement.getAllExcercisesByIdExcerciseFolder(Integer.parseInt(request.getParameter("idExcerciseFolder")));

        %>
        <div class="row setup-content" id="step-10">
            <div class="container-fluid w-50 ">
                <h3 class="font-weight-bold pl-0 my-4"><strong>Entrega de la tarea <%=request.getParameter("excercise")%></strong></h3>
                <h4>Enunciado de la tarea: 
                    <a href="<%=linkExcercise%>">
                        <%linkExcercise = linkExcercise.replace("%20", " ");%>
                        <%=linkExcercise.substring(linkExcercise.lastIndexOf("/") + 1, linkExcercise.lastIndexOf("?"))%>
                    </a>
                </h4>
                <%for (Ejercicio e : excercises) {
                        int idMember = e.getIdUser();
                        if (idMember == user.id()) {
                            continue;
                        }

                %>
                <form action="AddCorrectionServlet?userId=<%=idMember%>&idExcerciseFolder=<%=request.getParameter("idExcerciseFolder")%>&excercise=<%=request.getParameter("excercise")%>&path=<%=path%>&groupId=<%=request.getParameter("groupId")%>" method="post">
                    <table id="example" class="table table-bordered">
                        <tr>
                            <td>
                                <%=DatabaseConnection.getUser(idMember).username()%>
                            </td>
                            <td>
                                <%
                                    String linkMember = ExcercisesManagement.getLink(idMember, Integer.parseInt(request.getParameter("idExcerciseFolder")));
                                %>
                                <a href="<%=linkMember%>" ><%=linkMember.substring(linkMember.lastIndexOf("/") + 1, linkMember.lastIndexOf("?"))%></a>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                Calificación:
                            </td>
                            <td>
                                <input type="number" required="required" name="calification" value="<%=e.getCalification()%>">
                            </td>
                        </tr>
                        <tr>
                            <td >
                                Comentarios:
                            </td>
                            <td>
                                <a href="#" onclick="displayTextArea()">Comentarios</a><br>
                                <textarea name="comments" id="comments" cols="30" rows="7" style="display: none"><%=e.getComments()%></textarea><br>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                Acción:
                            </td>
                            <td>
                                <input type="submit" value="Enviar corrección" />
                            </td>
                        </tr>

                    </table>
                </form>
                <hr>
                <%}%>
            </div>
        </div>
        <%}%>
    </body>
</html>
<script>
    function displayTextArea() {
        var x = document.getElementById("comments");
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
</script>