<%-- 
    Document   : cuestionario
    Created on : 23-abr-2018, 11:52:02
    Author     : Nestor
--%>

<%@page import="com.mycompany.tutordocs.Exercise"%>
<%@page import="Db.CuestionariosManagement"%>
<%@page import="com.mycompany.tutordocs.Ejercicio"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.tutordocs.Group"%>
<%@page import="com.mycompany.tutordocs.User"%>
<%@page import="Db.GroupsManagement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <title>Cuestionario</title>
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
        <div class="container-fluid container_user">
            <div class="row align-items-start">
                <div class="col-2">
                    <div class="col-md-2 col-sm-3 sidebar">
                        <div class="user__card">
                            <div>
                                <img src="<%=user.getImage()%>" class="img-responsive rounded-circle" alt="Profile image" style="margin-top: 5px; object-fit: cover; width:65px; height:65px;">
                            </div>
                            <div class="user__card__nameuser"><a href="pages/userProfile.jsp"><%=user.username()%></a></div>
                        </div>
                        <ul class="nav nav-pills flex-column">
                            <li class="nav-item">
                                <a class="nav-link" href="pages/users.jsp">Cursos<span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="pages/users_group.jsp">Grupos</a>
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
                <div class="col-7">
                    <%
                        if (request.getParameter("id") == null || session.getAttribute("user") == null) {
                            request.getRequestDispatcher("pages/landingPage.jsp").forward(request, response);
                        }
                        String idGrupo = request.getParameter("id");
                        Group group = GroupsManagement.getGroupById(Integer.parseInt(idGrupo));

                        if (!group.isMember(user) && !GroupsManagement.getType(user.id()).equals("Estudiante")) {
                            request.getRequestDispatcher("pages/users.jsp").forward(request, response);
                        }
                        String titulo = request.getParameter("titulo");
                    %>
                    <h4><%=titulo%></h4>
                    <%  int idCuestionario = Integer.parseInt(request.getParameter("idCuestionario"));
                        List<Exercise> cuestionario = CuestionariosManagement.getEjerciciosByCuestionario(idCuestionario);
                        String name = "";
                        String correcta = "";
                        String puntuacion = "";
                        int num = 1;%>
                    <form action="FinishTestServlet">
                        <%for (Exercise e : cuestionario) {
                                name = "pregunta" + num;
                                correcta = "correcta" + num;
                                puntuacion = "puntuacion" + num;%>
                        <p><%=num + ". " + e.getPregunta() + "<br>" + "Puntúa como: " + e.getPuntuacion()%></p>
                        <input type="radio" name="<%=name%>" value="1"> <%=e.getOptionOne()%><br>
                        <input type="radio" name="<%=name%>" value="2"> <%=e.getOptionTwo()%><br>
                        <input type="radio" name="<%=name%>" value="3"> <%=e.getOptionThree()%><br>
                        <input type="hidden" name="<%=correcta%>" value="<%=e.getCorrect()%>">
                        <input type="hidden" name="<%=puntuacion%>" value="<%=e.getPuntuacion()%>">
                        <%num++;
                            }%>
                        <br>
                        <input type="hidden" name="num" value="<%=num - 1%>">
                        <input type="hidden" name="idGrupo" value="<%=idGrupo%>">
                        <input type="hidden" name="titulo" value="<%=titulo%>">
                        <input type="hidden" name="idCuestionario" value="<%=idCuestionario%>">
                        <input type="submit" class="btn btn-danger" value="Finalizar cuestionario">
                    </form>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
