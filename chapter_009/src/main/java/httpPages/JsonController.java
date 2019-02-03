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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonController extends HttpServlet {
    private Map<Integer, Person> map = new ConcurrentHashMap<>();
    private Integer id=0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append(new ObjectMapper().writeValueAsString(this.map));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s=reader.readLine())!=null){
            sb.append(s);
        }
        reader.close();
        Person person = new Gson().fromJson(sb.toString(), Person.class);
        map.put(id++, person);
        PrintWriter writer = new PrintWriter(new ObjectMapper().writeValueAsString(person));
        writer.flush();
    }
}
