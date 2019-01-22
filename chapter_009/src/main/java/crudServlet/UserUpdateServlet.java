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
        System.out.println(String.format("Servlet Update"));
        System.out.println(String.format(" id is, %s", req.getParameter("id")));
        System.out.println(String.format(" role by id is, %s", validate.getRoleById(req.getParameter("id"))));
        HttpSession session = req.getSession();
        if ((int) session.getAttribute("access") <= (int) super.getRolesFromMap().get(validate.getRoleById(req.getParameter("id")))) {
            super.doPost(req, resp);
        } else {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}