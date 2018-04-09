<%-- 
    Document   : landingPage.jsp
    Created on : 02-abr-2018, 11:22:11
    Author     : Cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/main.css">


        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <!-- Image and text -->
            <div class=""></div>
            <nav class="navbar navbar-light col-md-11">
                <a class="navbar-brand" href="#">
                    <img src="../images/logo.png"  class="d-inline-block align-top" alt="">
                </a>
                <div>
                    <button type="button" class="btn btn-outline-primary" data-toggle="modal" data-target="#LoginModal">Iniciar sesión</button>
                    <div class="modal fade" id="LoginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Iniciar sesión</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form action="../LoginServlet">
                                    <div class="modal-body">
                                        <div>
                                            <label>Email o usuario</label>
                                            <input class="form-control input-sm" type="text" name="user">
                                        </div>
                                        <div>
                                            <label>Contraseña</label>
                                            <input class="form-control input-sm" type="password" name="pass">
                                        </div>

                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                        <button type="submit" class="btn btn-primary">Iniciar sesión</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#SingupModal" >Registrarse</button>
                    <div class="modal fade" id="SingupModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="SingupModalLabel">Registrarse</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form method="post" action="../RegisterServlet">
                                        <div class="form-group">
                                            <label>Nombre</label>
                                            <input class="form-control input-sm" type="text" name="nombre">
                                        </div>
                                        <div class="form-group">
                                            <label>Apellidos</label>
                                            <input class="form-control input-sm" class="form-control input-sm" type="text" name="apellidos">
                                        </div>

                                        <div class="form-group">
                                            <label>Email</label>
                                            <input class="form-control input-sm" type="email" name="email">
                                        </div>

                                        <div class="form-group">
                                            <label>Nombre de usuario</label>
                                            <input class="form-control input-sm" type="text" name="usuario">
                                        </div>

                                        <div class="form-group">
                                            <label>Contraseña</label>
                                            <input class="form-control input-sm" type="password" name="contrasena">
                                        </div>

                                        <div class="form-group">
                                            <label>Tipo de usuario</label>
                                            <input type="radio" name="radio" value="Profesor">Profesor
                                            <input type="radio" name="radio" value="Estudiante">Estudiante
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                            <button type="submit" class="btn btn-primary">Registrarse</button>
                                        </div>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </nav>
        </header>
        <section>
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="../images/bg.png" alt="First slide">
                        <div class="carousel-caption d-none d-md-block">
                            <h1>Make bla bla</h1>
                            <p>...</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="../images/bg2.png" alt="Second slide">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>...</h5>
                            <p>...</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="../images/bg3.jpg" alt="Third slide">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>Study with us</h5>
                            <p>we have the best courses on the Internet</p>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </section>
        <main>
            <div class="container">
                <h2>¿Quienes somos?</h2>
                <section class="row">
                    <div class="col-md-2">
                    </div>
                    <div class="col-md-3">
                        <img src="../images/book.png" alt="">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing 
                            elit. Iure ad non quidem dolorem, recusandae 
                            perspiciatis optio, quibusdam porro saepe libero 
                            eos dignissimos, repudiandae fuga dolore cum voluptas 
                            nulla labore eaque.</p>
                    </div>
                    <div class="col-md-2">
                    </div>
                    <div class="col-md-3">
                        <img src="../images/portatil.png" alt="">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing 
                            elit. Iure ad non quidem dolorem, recusandae 
                            perspiciatis optio, quibusdam porro saepe libero 
                            eos dignissimos, repudiandae fuga dolore cum voluptas 
                            nulla labore eaque.</p>
                    </div>
                    <div class="col-md-2">
                    </div>
                </section>
            </div>

            <section class="sectionInformation">
                <div class="container">
                    <h2>Nuestros servicios</h2>
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-3 wrapper">
                            <img src="../images/CURSO.png" alt="">
                            <p>10000</p>
                            <p>Cursos</p>
                        </div>
                        <div class="col-md-2">
                        </div>
                        <div class="col-md-3 wrapper">
                            <img src="../images/DOCUMENTOS.png" alt="">
                            <p>10000</p>
                            <p>Documentos</p>
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                </div>
            </section>
            <section class="sectionSignUp wrapper">
                <h2>Registrate Ya!</h2>
                <button type="button" class="btn btn-primary">Sing up Now</button> 
            </section>
        </main>
        <footer>
            © Copyright 2018 - All Rights Reserved 
        </footer>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
