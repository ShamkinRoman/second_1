package crudServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/*
This is Servlet class. It used two methods doGet and doPost.
In URL method POST doing add, update, delete.
For example adding name=NameUser?login=LoginUser?email=Email@mail.com?action=add
For example updating name=NameUser?login=LoginUser?email=Email@mail.com?action=update?id=1
For example deleting name=NameUser?login=LoginUser?email=Email@mail.com?action=delete?id=1
Method GET display contain storage.
 */
public class UserServlet extends HttpServlet {
    private static ValidateService validate = ValidateService.getInstance();
    private Map<String, Function<HttpServletRequest, Boolean>> action = new HashMap<>();
    private volatile int id = 0;
/*
initializations main method for storage.
In constructor may be includes some actions in last time.
 */
    public UserServlet() {
        action.put("add", add());
        action.put("update", update());
        action.put("delete", delete());
    }

    public Function<HttpServletRequest, Boolean> add() {
        return (param -> {
            String name = param.getParameter("name");
            String login = param.getParameter("login");
            String email = param.getParameter("email");
            return validate.add(new User(this.id++, name, login, email));
        });
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        for (User user : validate.findAll()) {
            printWriter.append(user + "<br>");
        }
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(doAction(req) ? "successful" : "negative");
        printWriter.flush();
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

    public Function<HttpServletRequest, Boolean> delete() {
        return (param -> {
            boolean result = false;
            try {
                int id = (int) Integer.parseInt(param.getParameter("id"));
                String name = param.getParameter("name");
                String login = param.getParameter("login");
                String email = param.getParameter("email");
                result = validate.delete(new User(id, name, login, email));
            } catch (NumberFormatException nfe) {
                System.out.println(nfe.getMessage());
            }
            return result;
        });
    }
}