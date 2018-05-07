package Servlets;

import Db.ApuntesManagement;
import Db.Dropbox;
import Db.ExcercisesManagement;
import com.dropbox.core.DbxException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Yisus95
 */
@WebServlet(name = "DropboxServlet", urlPatterns = {"/DropboxServlet"})
public class DropboxServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws com.dropbox.core.DbxException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DbxException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String path = null;
        String excerciseName = null;
        String redirect = "";

        boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
        if (!isMultipartContent) {
            return;
        }

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> fields = upload.parseRequest(request);
            Iterator<FileItem> it = fields.iterator();
            if (!it.hasNext()) {
                return;
            }
            while (it.hasNext()) {
                FileItem fileItem = it.next();

                boolean isFormField = fileItem.isFormField();
                if (isFormField) {

                    if (fileItem.getFieldName().equals("groupName")) {
                        path = fileItem.getString();
                        path = path.replace(" ", "_");
                    } else {
                        if (fileItem.getFieldName().equals("excerciseName")) {
                            excerciseName = fileItem.getString();
                            excerciseName = excerciseName.replace(" ", "_");
                        }
                    }
                } else {
                    Dropbox dp = new Dropbox();
                    String s = fileItem.getName();

                    if (request.getParameter("uploadExcercise") == null) {
                        redirect = "groupView.jsp?id="+request.getParameter("groupId")+"&groupName="+path.substring(0,path.length());
                        path = "/" + path + "/" + s;
                        dp.insertFile(path, fileItem.getInputStream(), request, response);
                        ApuntesManagement.insertApunte(Integer.parseInt(request.getParameter("groupId")), Integer.parseInt(request.getParameter("userId")),
                                path, fileItem.getName(), dp.generateUrl(path));
                    } else {
                        if (request.getParameter("updateExcercise") != null) {
                            redirect = "excercises.jsp?idExcerciseFolder="+request.getParameter("idExcerciseFolder")+
                                        "&userId="+request.getParameter("userId")+
                                        "&excercise="+request.getParameter("excercise")+
                                        "&path="+path.substring(0,path.lastIndexOf("/")+1)+
                                        "&groupId="+request.getParameter("groupId");
                            dp.deleteFile("/"+path+"/"+request.getParameter("tarea"));
                            path = "/" + path + "/" + s;
                            dp.insertFile(path, fileItem.getInputStream(), request, response);
                            ExcercisesManagement.updateExcercise(dp.generateUrl(path), Integer.parseInt(request.getParameter("idExcerciseFolder")), " ", " ", Integer.parseInt(request.getParameter("userId")));
                        } else {
                            if (request.getParameter("createExcercise") == null) {
                                redirect = "excercises.jsp?idExcerciseFolder="+request.getParameter("idExcerciseFolder")+
                                        "&userId="+request.getParameter("userId")+
                                        "&excercise="+request.getParameter("excerciseName")+
                                        "&path="+path.substring(0,path.lastIndexOf("/")+1)+
                                        "&groupId="+request.getParameter("groupId");
                                path = "/" + path + "/" + s;
                                dp.insertFile(path, fileItem.getInputStream(), request, response);
                                ExcercisesManagement.insertExcercise(dp.generateUrl(path), Integer.parseInt(request.getParameter("idExcerciseFolder")), " ", " ", Integer.parseInt(request.getParameter("userId")));
                            } else {
                                redirect = "groupView.jsp?id="+request.getParameter("groupId")+"&groupName="+path.substring(0,path.length()-1);
                                dp.createFolder(path + "/" + excerciseName);
                                path = "/" + path + "/"  + excerciseName + "/" + s;
                                dp.insertFile(path, fileItem.getInputStream(), request, response);
                                ExcercisesManagement.insertExcerciseFolder(excerciseName, dp.generateUrl(path),
                                        Integer.parseInt(request.getParameter("groupId")), Integer.parseInt(request.getParameter("userId")));
                            }
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
        }

        response.sendRedirect(redirect);
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
        try {
            processRequest(request, response);
        } catch (DbxException ex) {
            Logger.getLogger(DropboxServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DropboxServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (DbxException ex) {
            Logger.getLogger(DropboxServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DropboxServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
