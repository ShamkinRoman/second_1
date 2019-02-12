package cinemaServlet;

import com.google.gson.Gson;
import httpPages.Person;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class HallServlet extends HttpServlet {
    private static final CinemaValidateService cinema = new CinemaValidateService().getInstance();
    private final Map<Integer, Person> map = new ConcurrentHashMap<>();
    private final List<Buyer> list = new CopyOnWriteArrayList<>();
    private final Map<String, List<String>> forAjax = new ConcurrentHashMap<>();

    private void fillAjax() {
        List<String> size = Arrays.asList("3", "3");
        forAjax.put("sizeTable", size);

    }

    public HallServlet() {
        fillAjax();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        ObjectMapper om = new ObjectMapper();
        String parameter = req.getParameter("value");
        System.out.println(parameter);
        writer.append(om.writeValueAsString(forAjax.get(parameter)));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        String s;
        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = req.getReader();
            while ((s = reader.readLine()) != null) {
                sb.append(s);
            }
            Buyer buyer = gson.fromJson(sb.toString(), Buyer.class);
            list.add(buyer);
            list.forEach(System.out::println); //для контроля.
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
