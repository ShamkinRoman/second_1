package crudServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class UserCreateServlet extends UserServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder("<table>");

        for( User user : super.findAll() ) {
            sb.append("<tr> "+ user +  " </tr>");
            sb.append("<br>");

        }

        sb.append("</table>");

        printWriter.append("<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<title>" + getClass().getName() + "</title> " +
                "</head>" +
                "<body>" +
                "Что то написано :)  " + new Date().toString() +
                "<form action'" + req.getContextPath() + "/' method = 'post'>"+
                " Name: <input type='text' name = 'name'/>" +
                " Login: <input type='text' name = 'login'/>"+
                " e-mail: <input type='text' name = 'email'/>"+
                "<input type = 'submit' name ='action' value= 'add'>"+
                "</form>" +
                sb +
                "</body>" +
                "</html>");
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
