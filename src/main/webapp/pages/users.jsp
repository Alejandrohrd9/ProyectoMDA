<%-- 
    Document   : users
    Created on : 03-abr-2018, 11:12:52
    Author     : Cristian
--%>

<%@page import="com.mycompany.tutordocs.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/main.css">

        <title>Cursos</title>
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
            <div class="row">
                <div class="col-md-2">
                    <div class="col-md-2 col-sm-3 d-none d-sm-block sidebar">
                        <div class="user__card">
                            <div>
                                <img class="user__card__img" src="../images/person1.jpg">
                            </div>
                            <div class="user__card__nameuser"><%=user.username()%></div>
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
                    <h3>Lista de Cursos</h3>
                    <div class="jumbotron">
                        <h3 >Curso 1</h3>
                        <p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
                        <a class="btn btn-primary btn-md" href="#" role="button">Inscribete</a>
                    </div>
                    <div class="jumbotron">
                        <h3 >Curso 2</h3>
                        <p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
                        <a class="btn btn-primary btn-md" href="#" role="button">Inscribete</a>
                    </div>
                </div>
                <div class="col-md-1"></div>
                </main>
            </div>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
