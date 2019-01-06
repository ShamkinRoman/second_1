package crudServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/*
Class for creating users used HTML form.
this is class is extending UserServlet.
It use name submit form as parameter (action)  for parents class.
 */

public class UserCreateServlet extends UserServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append("<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<title>" + getClass().getName() + "</title> " +
                "</head>" +
                "<body>" +
                "Something write :)  " + new Date().toString() +
                "<form action='" + req.getContextPath() + "/list' method = 'get'> " +
                "<button type='submit'> ListUsers </button> </form>" +
                "<form action'" + req.getContextPath() + "/' method = 'post'>" +
                " Name: <input type='text' name = 'name'/>" +
                " Login: <input type='text' name = 'login'/>" +
                " e-mail: <input type='text' name = 'email'/>" +
                "<input type = 'submit' name ='action' value= 'add'>" +
                "</form>" +
                "</body>" +
                "</html>");
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ss;
        ss = String.format("%s/index.jsp", req.getContextPath());
        super.doPost(req, resp);
        System.out.println(ss);
        System.out.println("Goto reDirect");
        resp.sendRedirect("index.jsp");
        System.out.println("After Redirect");
    }
}