package crudServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        HttpSession session = req.getSession();
        if ((int) session.getAttribute("access") <= (int) super.getRolesFromMap().get(validate.getRoleById(req.getParameter("id")))) {
            session.setAttribute("updateNot", "");
            super.doPost(req, resp);
        } else {
            session.setAttribute("updateNot", "You can't edit this user!");
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}