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

/**
 * This is Servlet for Update user in STORAGE.
 */
public class UserUpdateServlet extends UserServlet {
    private static final ValidateService validate = ValidateService.getInstance();
    private Map<String, Function<HttpServletRequest, Boolean>> action = new HashMap<>();
    private Map<String, Integer> roles = new HashMap<>();

    private void fillMap() {
        roles.put("admin", 1);
        roles.put("user", 2);
        roles.put("guest", 3);
        roles.put("99", 99);
    }

    public UserUpdateServlet() {
        fillMap();
        this.action.put("update", update());
//        this.action.put("addPasswordRole", addPasswordRole());
    }

    /**
     * @param req  Request from URL Client.
     * @param resp Response to Client.
     * @throws ServletException exception.
     * @throws IOException      exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", super.findAllMap().values());
        req.getRequestDispatcher("/WEB-INF/views/UserEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String roleUser = validate.getRoleByLogin(req.getParameter("loginOld"));
        System.out.println(String.format("Servlet Update"));
        System.out.println(String.format(" role is, %s", roleUser));
        System.out.println(String.format(" id is, %s", req.getParameter("id")));
        System.out.println(String.format(" role by id is, %s", validate.getRoleById(req.getParameter("id"))));
        System.out.println(roleUser);
        HttpSession session = req.getSession();

        if ((int) session.getAttribute("access") <= roles.get(validate.getRoleById(req.getParameter("id")))) {
            String result = doAction(req) ? "successful" : "negative";
        }
//        resp.sendRedirect(String.format("%s/", req.getContextPath()));
        doGet(req, resp);
    }

    public boolean doAction(HttpServletRequest request) {
        Function<HttpServletRequest, Boolean> function = action.get(request.getParameter("action"));
        return function != null ? function.apply(request) : false;
    }

    public Function<HttpServletRequest, Boolean> update() {
        return (param -> {
            boolean result = false;
            try {
                int id = (int) Integer.parseInt(param.getParameter("id"));
                String name = param.getParameter("name");
                String login = param.getParameter("login");
                String email = param.getParameter("email");
                result = validate.update(new User(id, name, login, email));
            } catch (NumberFormatException nfe) {
                System.out.println(nfe.getMessage());
            }
            return result;
        });
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}