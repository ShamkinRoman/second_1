package crudServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/*
Class for creating users used HTML form.
this is class is extending UserServlet.
It use name submit form as parameter (action)  for parents class.
 */

public class UserCreateServlet extends UserServlet {
    private static final ValidateService validate = ValidateService.getInstance();
    private Map<String, Function<HttpServletRequest, Boolean>> action = new HashMap<>();
    private Map<String, Integer> roles = new HashMap<>();
    private void fillMap() {
        roles.put("admin", 1);
        roles.put("user", 2);
        roles.put("guest", 3);
        roles.put("99", 99);
    }

    public UserCreateServlet() {
        this.action.put("add", add());
        this.action.put("addPasswordRole", addPasswordRole());
        fillMap();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/UserCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = doAction(req) ? "successful" : "negative";
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

    public boolean doAction(HttpServletRequest request) {
        Function<HttpServletRequest, Boolean> function = action.get(request.getParameter("action"));
        return function != null ? function.apply(request) : false;
    }



    public Function<HttpServletRequest, Boolean> add() {
        return (param -> {
            String name = param.getParameter("name");
            String login = param.getParameter("login");
            String email = param.getParameter("email");
            return validate.add(new User(name, login, email));
        });
    }

    public Function<HttpServletRequest, Boolean> addPasswordRole() {
        return (param -> {
            boolean result = false;
            String name = param.getParameter("name");
            String login = param.getParameter("login");
            String email = param.getParameter("email");
            String password = param.getParameter("password");
            String role = param.getParameter("role");
            HttpSession session = param.getSession();
            int access = (int) session.getAttribute("access");
            if  (roles.get(role) >= access) {
                result = validate.addPasswordRole(new User(name, login, email), password, role);
                session.setAttribute("addWrongRole", " not");
            } else {
                session.setAttribute("addWrongRole", " You can't add this role");
            }
            return result;
        });
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}