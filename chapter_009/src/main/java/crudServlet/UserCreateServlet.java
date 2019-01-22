package crudServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *Class for creating users used HTML form.
 *
 */

public class UserCreateServlet extends UserServlet {
    private static final ValidateService validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/UserCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int access = (int) session.getAttribute("access");
        if ((int) super.getRolesFromMap().get(req.getParameter("role")) >= access) {
           addPasswordRole(req);
            session.setAttribute("addNot", "");
        } else {
           session.setAttribute("addNot", "You can't add this role");
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

    public boolean addPasswordRole(HttpServletRequest param) {
            String name = param.getParameter("name");
            String login = param.getParameter("login");
            String email = param.getParameter("email");
            String password = param.getParameter("password");
            String role = param.getParameter("role");
            boolean  result = validate.addPasswordRole(new User(name, login, email), password, role);
            return result;
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}