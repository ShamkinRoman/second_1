package crudServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/*
This is class is extending UserServlet.
Class used methods UPDATE and DELETE in HTML form.
It use name submit form as parameter (action) for parents class.
 */
public class UsersServlet extends UserServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<title>" + getClass().getName() + "</title> " +
                "</head>" +
                "<body>" +
                "Something write :)  " + new Date().toString());
        sb.append("<br>" +
                "<table border='1'>" +
                "<tr> <th> id </th>" +
                "<th> name </th>" +
                "<th> login </th>" +
                "<th> e-mail</th> " +
                "<th> dateCreate </th>" +
                "<th> update </th> " +
                "<th> delete </th> </tr>"
        );
        for (User user : super.findAllMap().values()) {
            sb.append( "<form action'" + req.getContextPath() + "/' method = 'post'>" +
                    "<tr> <td>  <input type='text' name = 'id' value = '"  + user.getId() + "' readonly> </td>" +
                    "<td> " + user.getName() + " </td>" +
                    "<td> " + user.getLogin() + " </td>" +
                    "<td>" + user.getEmail() + "</td> " +
                    "<td> " + user.getDataCreate() + "</td>" +

                    "<td> <button type = 'submit' formmethod='post' formaction='" + req.getContextPath() + "/list' name ='update" + user.getId() + "' <i>update</i> </button></td> " +
                    "<td> <button type = 'submit' name ='delete" + user.getId() + "' <i>delete</i> </button></td> </tr> +" +
                    " </form>");


//                    "<td> <input type = 'submit' name ='action" + user.getId() + "' value = 'update'></td> " +
//                    "<td> <input type = 'submit' name ='action" + user.getId() + "' value = 'delete'> </td> </tr>");
        }
        sb.append("</table>" + "</body>" +
                "</html>");
        printWriter.append(sb);

//        for (User user : super.findAllMap().values()){
//            sb.append("<form action'" + req.getContextPath() + "/' method = 'post'>"+
//                    " id: <input type='text' name = 'id' value = '" + user.getId()+ "'/>" +
//                    " Name: <input type='text' name = 'name' value = '" + user.getName()+ "'/>" +
//                    " Login: <input type='text' name = 'login' value = '" +user.getLogin()+ "' />"+
//                    " e-mail: <input type='text' name = 'email' value = '" +user.getEmail() + "'/>"+
//                    "<input type = 'submit' name ='action' value= 'update'>"+
//                    "<input type = 'submit' name ='action' value= 'delete'>"+
//                    "</form>");
//            sb.append("<br>");
//        }
//        printWriter.append(sb);
//        printWriter.append("</body>" +
//                "</html>");
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("This my post method. <br>");

        Map<String, String[]> map = req.getParameterMap();

        for( String[] strings : map.values()) {
            for (String str : strings) {
                writer.append(" ");
                writer.append(str);
            }
        }

        writer.flush();

         }
}
