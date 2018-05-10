<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Db.DatabaseConnection"%>
<%@page import="com.mycompany.tutordocs.Hilo"%>
<%@page import="Db.ForumManagement"%>
<%@page import="com.mycompany.tutordocs.Group"%>
<%@page import="Db.GroupsManagement"%>
<%@page import="com.mycompany.tutordocs.User"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/main.css">
        <title>Foro</title>
    </head>
    <body>
        <%
            User user = (User) request.getSession().getAttribute("user");
            Group group = GroupsManagement.getGroupById(Integer.parseInt(request.getParameter("id")));
            if (!group.isMember(user)) {
                request.getRequestDispatcher("groups.jsp").forward(request, response);
            }
        %>

        <nav class="navbar navbar-light navbar__user fixed-top">
            <a class="navbar-brand" href="#">
                <img src="../images/logo_inverse.png"  class="d-inline-block align-top" alt="">
            </a>

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
                                <a class="nav-link" href="../users.jsp">Cursos<span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="users_group.jsp">Grupos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="noticeBoard.jsp">Tablón</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="../LogOutServlet">Cerrar Sesion</a>
                            </li>
                        </ul>
                    </div>               
                </div>

                <div class="container col-10">
                    <h5>Temas</h5>

                    <button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#postModal">
                        Nuevo tema
                    </button><hr>

                    <ul class="list-group list-group-flush">
                        <%
                            for (Hilo hilo : ForumManagement.getHilos(group.id())) {
                        %>
                        <li class="list-group-item" style="background-color: #444444; "><a style="color:white;" href="HiloDisplay.jsp?name=<%=hilo.getTitle()%>&threadid=<%=hilo.getId()%>&groupid=<%=group.id()%>"><%=hilo.getTitle()%> - <%=DatabaseConnection.getUsername(hilo.getId_user())%> - <%=new SimpleDateFormat("dd/MM/yyyy").format(hilo.getDate())%></a><% if (hilo.getId_user() == user.id()) {%>
                            <form action="../DeleteHilo">
                                <input type="hidden" name="threadid" value="<%=hilo.getId()%>">
                                <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                            </form>
                            <%}%>
                        </li>
                        <%}%>
                    </ul>
                </div>

                <div class="modal fade" id="postModal" tabindex="-1" role="dialog" aria-labelledby="postModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 style="color: black" class="modal-title" id="postModalLongTitle">Nuevo tema</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="../CreateHilo" onsubmit="return validateNewThread(this)">
                                    <div class="form-group">
                                        <label for="postName">Título</label>
                                        <input type="text" class="form-control" name="title" id="inputTitle" aria-describedby="emailHelp" placeholder="Nombre del tema">
                                        <small id="titleHelp" class="form-text text-muted">Este es el título que aparecerá en la lista de hilos.</small>
                                    </div>
                                    <div class="form-group">
                                        <label for="body">Mensaje: </label>
                                        <textarea class="form-control" rows="5" name="body" id="body"></textarea>
                                        <small id="bodyHelp" class="form-text text-muted">Máximo 255 caracteres.</small>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                        <button type="submit" class="btn btn-primary">Crear</button>
                                    </div>
                                    <input type="hidden" name="group_id" value="<%=group.id()%>">
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
                </div>
                </div>
                <!-- Optional JavaScript -->
                <!-- jQuery first, then Popper.js, then Bootstrap JS -->
                <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>

                <script>
                                    function validateNewThread(form) {
                                        if (form.elements[0].value === "") {
                                            alert("Introduce un título para el tema");
                                            return false;
                                        }

                                        if (form.elements[1].value === "") {
                                            alert("Introduce el contenido del primer mensaje");
                                            return false;
                                        }

                                        if (form.elements[1].value.length > 255) {
                                            alert("Máximo 255 caracteres, has introducido " + form.elements[1].value.length);
                                            return false;
                                        }

                                        return true;
                                    }
                </script>
                </body>
                </html>