/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Db.CuestionariosManagement;
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
 * @author alejandrohd
 */
@WebServlet(name = "QuestionServlet", urlPatterns = {"/QuestionServlet"})
public class QuestionServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String id;
            String accept = request.getParameter("accept");
            String finish = request.getParameter("finish");
            String cancel = request.getParameter("cancel");
            HttpSession session = request.getSession();
            
            if (accept != null) {
                id = CuestionariosManagement.idQuestionnaire((String) request.getSession().getAttribute("nameQ"));
                String question = request.getParameter("pregunta");
                if (request.getParameter("pregunta") != null && request.getParameter("r1") != null && request.getParameter("r2") != null && request.getParameter("r3") != null && request.getParameter("puntuacionP") != null) {
                    CuestionariosManagement.insertQuestion(question, request.getParameter("r1"), request.getParameter("r2"), request.getParameter("r3"), Integer.parseInt(request.getParameter("exampleRadios1")), id, Integer.parseInt(request.getParameter("puntuacionP")));
                }
                response.sendRedirect("pages/createQuestions.jsp");
            }

            if (finish != null) {
                id = CuestionariosManagement.idQuestionnaire((String) request.getSession().getAttribute("nameQ"));
                if (request.getParameter("pregunta") != null && request.getParameter("r1") != null && request.getParameter("r2") != null && request.getParameter("r3") != null && request.getParameter("puntuacionP") != null) {
                    CuestionariosManagement.insertQuestion(request.getParameter("pregunta"), request.getParameter("r1"), request.getParameter("r2"), request.getParameter("r3"), Integer.parseInt(request.getParameter("exampleRadios1")), id, Integer.parseInt(request.getParameter("puntuacionP")));
                }
                //request.setAttribute("id", session.getAttribute("groupid"));
                RequestDispatcher dispatch = request.getRequestDispatcher("groups.jsp");
                if (dispatch != null) {
                    dispatch.forward(request, response);
                }
            }

            if (cancel != null) {
                id = CuestionariosManagement.idQuestionnaire((String) request.getSession().getAttribute("nameQ"));
                CuestionariosManagement.removeQuestion(id);
                CuestionariosManagement.removeQuestionnaire(Integer.parseInt(id));
                RequestDispatcher dispatch = request.getRequestDispatcher("groupView.jsp");
                if (dispatch != null) {
                    dispatch.forward(request, response);
                }
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(QuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
