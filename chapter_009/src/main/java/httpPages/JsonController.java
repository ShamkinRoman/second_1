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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonController extends HttpServlet {
    private final Map<Integer, Person> map = new ConcurrentHashMap<>();
    private Integer id = 0;
    private final Map<String, List<String>> forAjax = new ConcurrentHashMap<>();

    private void fillAjax() {
        List<String> country = Arrays.asList("Russia", "Belarus", "Ukraine");
        List<String> russianTown = Arrays.asList("Moscow", "SPB", "Tagil");
        List<String> belarusTown = Arrays.asList("Minsk", "Gomel", "Brest");
        List<String> ukraineTown = Arrays.asList("Kiev", "Lvov", "Harkov");
        List<String> chooseCountry = Arrays.asList("You must choose country");
        forAjax.put("country", country);
        forAjax.put("Russia", russianTown);
        forAjax.put("Belarus", belarusTown);
        forAjax.put("Ukraine", ukraineTown);
        forAjax.put("chooseCountry", chooseCountry);
    }

    public JsonController() {
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
//        writer.append(om.writeValueAsString(map));
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
            Person person = gson.fromJson(sb.toString(), Person.class);
            map.put(id++, person);
            map.values().forEach(System.out::println); //для контроля.
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
