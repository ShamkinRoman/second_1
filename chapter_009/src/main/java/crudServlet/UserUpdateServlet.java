package crudServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * This is Servlet for Update user in STORAGE.
 */
public class UserUpdateServlet extends UserServlet {
    /**
     * This is method for building HTML page by StringBuilder, if exists USER in STORAGE.
     *
     * @param sb  StringBuilder for record and transfuse HTML page with User data.
     * @param req Request which contain data for finding User in Storage.
     * @return resulting HTML page.
     */
    private StringBuilder notEmptyUserBuilPage(StringBuilder sb, HttpServletRequest req) {
        User userUpdate = super.findAllMap().get(Integer.parseInt(req.getParameter("id")));
        sb.append("<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<title>" + getClass().getName() + "</title> " +
                "</head>" +
                "<body>" +
                " This is update form  " + new Date().toString() + "   ");
        sb.append("<form action='" + req.getContextPath() + "/list' method = 'get'> "+
                "<button type='submit'> ListUsers </button> </form>");
        sb.append("<br>" +
                "<table border='1'>" +
                "<tr> <th> id </th>" +
                "<th> name </th>" +
                "<th> login </th>" +
                "<th> e-mail</th> " +
                "<th> dateCreate </th>" +
                "<th> update </th> " +
                "</tr>");
        sb.append("<form action'" + req.getContextPath() + "/' method = 'post'>" +
                "<tr> <td>  <input type='text' name = 'id' value = '" + userUpdate.getId() + "'readonly> </td>" +
                "<td> <input type='text' name = 'name' value = '" + userUpdate.getName() + "' > </td>" +
                "<td> <input type='text' name = 'login' value = '" + userUpdate.getLogin() + "' > </td>" +
                "<td><input type='text' name = 'email' value = '" + userUpdate.getEmail() + "'></td> " +
                "<td> <input type='text' name = 'dataCreate' value = '" + userUpdate.getDataCreate() + "' readonly></td>" +
                "<td> <input type = 'submit' name ='action' value = 'update'> </td> </tr>" +
                " </form>");
        sb.append("</table>" + "</body>" + "</html>");
        return sb;
    }

    /**
     * This is method contain try - catch block, because user may be enter request without ID.
     *
     * @param req  Request from URL Client.
     * @param resp Response to Client.
     * @throws ServletException exception.
     * @throws IOException      exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder();
        try {
            User userUpdate = super.findAllMap().get(Integer.parseInt(req.getParameter("id")));
            if (userUpdate != null) {
                sb = notEmptyUserBuilPage(sb, req);
            } else {
                sb.append(" Not correct ID, user with this ID not found");
            }
        } catch (NumberFormatException nfe) {
            sb.append(" You can enter ID");
        }
        writer.append(sb);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        doGet(req, resp);
    }
}