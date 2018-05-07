/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Db.CuestionariosManagement;
import com.mycompany.tutordocs.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nestor
 */
@WebServlet(name = "FinishTestServlet", urlPatterns = {"/FinishTestServlet"})
public class FinishTestServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            double resultParcial=0.0;
            double result=0.0;
            int puntuacion,respuesta,correcta;
            String pre="";
            String co="";
            String punt="";
            String idGrupo=(String)request.getParameter("idGrupo");
            HttpSession session=request.getSession();
            User user=(User) session.getAttribute("user");
            String titulo=request.getParameter("titulo");
            int idCuestionario=Integer.parseInt(request.getParameter("idCuestionario"));
            try (PrintWriter out = response.getWriter()) {
                
                //preguntas=Integer.parseInt((String)request.getParameter("num"));
                int num= Integer.parseInt((String)request.getParameter("num"));
                int preguntas=0;
                
                for (int i = 1; i <= num; i++) {
                    pre="pregunta"+i;
                    co="correcta"+i;
                    punt="puntuacion"+i;
                    if((String)request.getParameter(pre)==null){
                        continue;
                    }
                    respuesta=Integer.parseInt((String)request.getParameter(pre));
                    correcta=Integer.parseInt((String)request.getParameter(co));
                    puntuacion=Integer.parseInt((String)request.getParameter(punt));
                    preguntas+=puntuacion;
                    
                    
                    if(respuesta==correcta){
                        resultParcial+=puntuacion;
                    }
                }
                resultParcial=resultParcial/preguntas;
                result=resultParcial*10;
                CuestionariosManagement.insertRegistro(idCuestionario, user.id(), result);
                request.setAttribute("idGrupo", idGrupo);
                request.setAttribute("nota", String.valueOf(result));
                request.setAttribute("titulo", titulo);
                RequestDispatcher dispatch=request.getRequestDispatcher("SeeMark.jsp");
                if(dispatch != null){
                    dispatch.forward(request,response);
                }
            }
        }   catch (SQLException ex) {
            Logger.getLogger(FinishTestServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FinishTestServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FinishTestServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FinishTestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
