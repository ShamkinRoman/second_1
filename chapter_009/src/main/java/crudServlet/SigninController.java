package crudServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SigninController extends HttpServlet {
    private Map<String, Integer> roles = new HashMap<>();

    private void fillMap(){
        roles.put("admin", 1);
        roles.put("user", 2);
        roles.put("guest", 3);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        fillMap();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login.equals("lolo") && password.equals("pass")) {
            HttpSession session = req.getSession();
            synchronized (session){
                session.setAttribute("access",roles.get(req.getParameter("role")));
                System.out.println(session.getAttribute("access"));
                session.setAttribute("login", "LOLO");
            }
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error","Wrong authorization");
            doGet(req,resp);
        }
    }
}
