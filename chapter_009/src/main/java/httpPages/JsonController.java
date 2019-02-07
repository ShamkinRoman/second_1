package httpPages;

import com.google.gson.Gson;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonController extends HttpServlet {
    private final Map<Integer, Person> map = new ConcurrentHashMap<>();
    private Integer id = 0;
    private final List<Person> list = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        ObjectMapper om = new ObjectMapper();
        writer.append(om.writeValueAsString(map));
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
            while ((s=reader.readLine())!=null){
                sb.append(s);
            }
            Person person = gson.fromJson(sb.toString(), Person.class);
            map.put(id++,person);
            list.add(person);
            map.values().forEach(System.out::println); //для контроля.
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
