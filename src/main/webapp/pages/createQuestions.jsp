 <%-- 
    Document   : createQuestions
    Created on : 24-abr-2018, 20:54:52
    Author     : alejandrohd
--%>

<%@page import="Db.CuestionariosManagement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creando preguntas</title>
    </head>
    <body>
        <nav class="navbar navbar-light navbar__user fixed-top">
            <a class="navbar-brand" href="#">
                <img src="../images/logo_inverse.png"  class="d-inline-block align-top" alt="">
            </a>
            <div>

            </div>
        </nav>
        <div class="container container_user">
            <form action="../QuestionServlet">
                Pregunta:<input type="text" name="pregunta"> Puntuación:<input type="text" name="puntuacionP">
     
                <div class="form-check">
                    <input class="form-check-input" name="exampleRadios1" type="radio" value="1"><input type="text" name="r1">
                </div>
                <div class="form-check">
                    <input class="form-check-input" name="exampleRadios1" type="radio" value="2"><input type="text" name="r2">
                </div>
                <div class="form-check">
                    <input class="form-check-input" name="exampleRadios1" type="radio" value="3"><input type="text" name="r3">
                </div>
                <div class="form-group row">
                    <%if (request.getParameter("title") != null) {
                            session.setAttribute("nameQ", request.getParameter("title"));
                        }%>
                    <button type="submit" class="btn btn-primary" name="cancel">Cancelar</button>
                    <button type="submit" class="btn btn-primary" name="accept">Confirmar pregunta</button>
                    <button type="submit" class="btn btn-primary" name="finish">Terminar</button>
                </div>
            </form>
        </div>


        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    </body>
</html>
