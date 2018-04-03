<%-- 
    Document   : header
    Created on : 02-abr-2018, 10:56:59
    Author     : alejandrohd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TutorDocs</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js" integrity="sha384-feJI7QwhOS+hwpX2zkaeJQjeiwlhOP+SdQDqhgvvo1DsjtiSQByFdThsxO669S2D" crossorigin="anonymous"></script>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-sm">
            <% if (session.getAttribute("username") == null){%>
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#"> Iniciar sesión</a>
                        <div class="dropdown-menu">
                            <form method="post" action="LoginServlet">
                                <div class="dropdown-item">
                                    <label>Email o usuario:</label>
                                    <input class="form-control input-sm" type="text" name="user">
                                    <input type="hidden" name="emailHidden">
                                </div>
                                <div class="dropdown-item">
                                    <label>Contraseña:</label>
                                    <input class="form-control input-sm" type="password" name="pass">
                                    <input type="hidden" name="emailHidden">
                                </div>
                            </form>
                        </div>
                    </li>
                    
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#">¡Registrarse! </a>
                        <div class="dropdown-menu">
                            <form method="post" action="RegisterServlet">
                                <div class="dropdown-item">
                                    <label>Nombre:</label>
                                    <input class="form-control input-sm" type="text" name="nombre">
                                    <input type="hidden" name="">
                                </div>
                                <div class="dropdown-item">
                                    <label>Apellidos:</label>
                                    <input class="form-control input-sm" class="form-control input-sm" type="text" name="apellidos">
                                    <input type="hidden" name="">
                                </div>

                                <div class="dropdown-item">
                                    <label>Email:</label>
                                    <input class="form-control input-sm" type="email" name="email">
                                    <input type="hidden" name="">
                                </div>

                                <div class="dropdown-item">
                                    <label>Nombre de usuario:</label>
                                    <input class="form-control input-sm" type="text" name="usuario">
                                    <input type="hidden" name="">
                                </div>

                                <div class="dropdown-item">
                                    <label>Contraseña:</label>
                                    <input class="form-control input-sm" type="password" name="contraseña">
                                    <input type="hidden" name="">
                                </div>

                                <div class="dropdown-item">
                                    <label>Tipo de usuario:</label>
                                    <input type="radio" name="radio" value="Profesor">Profesor
                                    <input type="radio" name="radio" value="Estudiante">Estudiante
                                    <input type="hidden" name="">
                                </div>
                            </form>
                        </div>    
                    </li>
            
            <%}else{%>
                <form action="LogOutServlet">
                    <label>¡Bievenido, <%=session.getAttribute("username")%>!</label>
                    <input type="submit" value="Cerrar sesión">
                </form>
            <%}%>
            </ul>
        </nav>
        </header>
    </body>
</html>
