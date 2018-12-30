package crudServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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
        printWriter.append("<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<title>" + getClass().getName() + "</title> " +
                "</head>" +
                "<body>" +
                "Что то написано :)  " + new Date().toString());
        for (User user : super.findAll()){
            sb.append("<form action'" + req.getContextPath() + "/' method = 'post'>"+
                    " id: <input type='text' name = 'id' value = '" + user.getId()+ "'/>" +
                    " Name: <input type='text' name = 'name' value = '" + user.getName()+ "'/>" +
                    " Login: <input type='text' name = 'login' value = '" +user.getLogin()+ "' />"+
                    " e-mail: <input type='text' name = 'email' value = '" +user.getEmail() + "'/>"+
                    "<input type = 'submit' name ='action' value= 'update'>"+
                    "<input type = 'submit' name ='action' value= 'delete'>"+
                    "</form>");
            sb.append("<br>");
        }
        printWriter.append(sb);
        printWriter.append("</body>" +
                "</html>");
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
