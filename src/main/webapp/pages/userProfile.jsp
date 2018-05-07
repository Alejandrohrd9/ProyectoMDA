
<%@page import="com.mycompany.tutordocs.User"%>
<%@page import="Db.GroupsManagement"%>
<%@page import="com.mycompany.tutordocs.Group"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/main.css">

        <title>Grupos</title>
    </head>
    <body>
        <% List<Group> groups = GroupsManagement.getGroups();
            User user = (User) session.getAttribute("user");
        %>
        <nav class="navbar navbar-light navbar__user fixed-top">
            <a class="navbar-brand" href="#">
                <img src="../images/logo_inverse.png"  class="d-inline-block align-top" alt="">
            </a>

        </nav>
        <div class="container container_user">
            <div class="row">
                <div class="col-md-2">
                    <div class="col-md-2 col-sm-3 d-none d-sm-block sidebar">
                        <div class="user__card">
                            <div>
                                <img src="<%=user.getImage()%>" class="img-responsive rounded-circle" alt="Profile image" style="margin-top: 5px; object-fit: cover; width:65px; height:65px;">
                            </div>
                            <div class="user__card__nameuser"><a href="userProfile.jsp"><%=user.username()%></a></div>
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

                <div class="col-md-7">
                    <div id="profileInfo">
                        <h3>Tu perfil</h3><hr>

                        <h5>Nombre de usuario: <%=user.username()%></h5>
                        <h5>Nombre: <%=user.getName()%></h5>
                        <h5>Apellido: <%=user.getSurname()%></h5>
                        <h5>Tipo de usuario: <%=user.getType()%></h5>
                        <h5>Email: <%=user.getEmail()%></h5>


                    </div>

                    <div id="editProfile" style="display: none">
                        <form action="../UpdateProfile" accept-charset="UTF-8">
                            <div class="form-group">
                                <label for="inputName">Nombre</label>
                                <input type="text" class="form-control" id="name" name="name" aria-describedby="nameHelp" value="<%=user.getName()%>">
                                <small id="nameHelp" class="form-text text-muted">Tu nombre</small>
                            </div>

                            <div class="form-group">
                                <label for="inputName">Apellido</label>
                                <input type="text" class="form-control" id="surname" name="surname" aria-describedby="nameHelp" value="<%=user.getSurname()%>">
                                <small id="surnameHelp" class="form-text text-muted">Tu apellido</small>
                            </div>

                            <div class="form-group">
                                <label for="inputName">Email</label>
                                <input type="text" class="form-control" id="email" name="email" aria-describedby="emailHelp" value="<%=user.getEmail()%>">
                                <small id="emailHelp" class="form-text text-muted">Tu correo electrónico</small>
                            </div>  

                            <div class="form-group">
                                <label for="inputImage">Imagen de perfil</label>
                                <input type="text" class="form-control" id="image" name="image" aria-describedby="emailHelp" value="<%=user.getImage()%>">
                                <small id="emailHelp" class="form-text text-muted">Tu imagen de perfil, añade un enlace de una imagen.</small>
                            </div>  

                            <button type="submit" class="btn btn-primary">Guardar</button>

                        </form>
                    </div>

                    <div class="mt-2 text-center" id="editProfileButtonContainer">
                        <button type="button" class="btn btn-secondary" id="editProfileButton" onClick="toggleEdit()">Editar</button>
                    </div>
                </div>  
                <div class="col-md-1"></div>
                </main>
            </div>
        </div>


        <script>
            function toggleEdit() {
                jQuery('#editProfile').toggle();
                jQuery('#profileInfo').toggle();
                if ($('#editProfile').is(':visible')) {
                    jQuery('#editProfileButton').text("Cancelar");
                } else {
                    jQuery('#editProfileButton').text("Editar");
                }
            }
        </script>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
