
package Servlets;

import Db.ApuntesManagement;
import Db.Dropbox;
import com.dropbox.core.DbxException;
import java.io.IOException;
import static java.lang.System.out;
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
        String contentype = request.getContentType();
        String path = null;

        boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
        if (!isMultipartContent) {
            out.println("You are not trying to upload<br/>");
            return;
        }
        out.println("You are trying to upload<br/>");

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
                    path = "/" + fileItem.getString() + "/";
                    path = path.replace(" ", "_");
                } else {
                    Dropbox dp = new Dropbox();
                    dp.insertFile(path + fileItem.getName(),
                            fileItem.getInputStream(), request, response);
                    
                    ApuntesManagement.insertApunte(Integer.parseInt(request.getParameter("groupId")), Integer.parseInt(request.getParameter("userId")), path + fileItem.getName(), fileItem.getName());
                    
                    String s = fileItem.getString();
                    String name = fileItem.getName();
                    String content = fileItem.getContentType();
                    long size = fileItem.getSize();
                    String toString = fileItem.toString();
                }
            }
        } catch (FileUploadException e) {
        }


        response.sendRedirect("pages/users_group.jsp");
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
