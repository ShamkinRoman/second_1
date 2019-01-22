package crudServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    public UserCreateServlet() {
        this.action.put("addPasswordRole", addPasswordRole());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/UserCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int access = (int) session.getAttribute("access");
        if ((int) super.getRolesFromMap().get(req.getParameter("role")) >= access) {
            String result = doAction(req) ? "successful" : "negative";
            session.setAttribute("addNot", "");
        } else {
           session.setAttribute("addNot", "You can't add this role");
        }
        System.out.println(session.getAttribute("addNot"));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

    public boolean doAction(HttpServletRequest request) {
        Function<HttpServletRequest, Boolean> function = action.get(request.getParameter("action"));
        return function != null ? function.apply(request) : false;
    }

    public Function<HttpServletRequest, Boolean> addPasswordRole() {
        return (param -> {
            String name = param.getParameter("name");
            String login = param.getParameter("login");
            String email = param.getParameter("email");
            String password = param.getParameter("password");
            String role = param.getParameter("role");
            boolean  result = validate.addPasswordRole(new User(name, login, email), password, role);
                        return result;
        });
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}