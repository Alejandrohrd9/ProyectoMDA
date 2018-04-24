<%-- 
    Document   : theme
    Created on : 13-abr-2018, 14:07:58
    Author     : Cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Db.ThemeManagement"%>
<%@page import="com.mycompany.tutordocs.User"%>
<%@page import="Db.DatabaseConnection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.tutordocs.Message"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/main.css">
        <title>Tablón de Anuncios</title>
    </head>
    <body>
        <%
            User userSession = (User) session.getAttribute("user");
        %>
        <nav class="navbar navbar-light navbar__user fixed-top">
            <a class="navbar-brand" href="#">
                <img src="../images/logo_inverse.png"  class="d-inline-block align-top" alt="">
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
                                <img class="user__card__img" src="../images/person1.jpg">
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
                                <a class="nav-link" href="noticeBoard.jsp">Tablón</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Cerrar Sesion</a>
                            </li>
                        </ul>
                    </div>               
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-7">
                    <h3>
                        <%
                            out.println(request.getParameter("name"));
                            //Buscar idtheme, por lo que deberia hacerlo en la url de boards
                        %>
                    </h3>
                    <%
                        int idtheme = Integer.parseInt(request.getParameter("idtheme"));
                        List<Message> messages = ThemeManagement.getMessagesByTheme(idtheme);
                        for (Message message : messages) {
                    %>
                    <div class="jumbotron">
                        <h4><%
                            User user = DatabaseConnection.getUser(message.getIdCreator());
                            out.println(user.username());%>
                        </h4>
                        <div class="modal-body modal-body--bgwhite">
                            <%
                                out.println(message.getData());
                            %>
                        </div>
                        <%
                            if (message.getIdCreator() == userSession.id()) {
                        %>
                        <div class="btn-groups">
                            <form action="../RemoveMessageServlet"> 
                                <input type="hidden" name="idmessage" value="<%out.print(message.getIdMessage());%>">
                                <input type="submit" class="btn btn-danger btn-sm"  value="Eliminar">
                            </form>       
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#myModal">
                                Modificar
                            </button>
                            <!-- Modal -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title" id="myModalLabel">Mensaje</h4>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        </div>
                                        <form action="../UpdateMessageServlet">
                                            <div class="modal-body">
                                                <input type="hidden" name="idmessage" value="<%out.print(message.getIdMessage());%>"/>
                                                <input class="form-control" name="messageModified" value="<%out.print(message.getData());%>"></input>
                                            </div>
                                            <div class="modal-footer">
                                                <input type="submit" class="btn btn-primary" value="Guardar Cambios"></input>
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div> 
                    <%
                        }
                    %>
                    <form action="../AddMessageServlet">
                        <textarea  class="form-control" name="textMessage" placeholder="Introduzca un mensaje"></textarea>  
                        <input type="hidden" name="idtheme" value="<%out.print(request.getParameter("idtheme"));%>">
                        <input type="submit" class="btn btn-primary form-control" value="Enviar">
                    </form>
                </div>
            </div>
            <script defer src="https://use.fontawesome.com/releases/v5.0.9/js/all.js" integrity="sha384-8iPTk2s/jMVj81dnzb/iFR2sdA7u06vHJyyLlAd4snFpCl/SnyUjRrbdJsw1pGIl" crossorigin="anonymous"></script>
            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
            <script src="../js/index.js"></script>
    </body>
</html>

