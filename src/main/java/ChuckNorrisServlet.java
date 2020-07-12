import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ChuckNorrisServlet")
public class ChuckNorrisServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String randomJoke = "brak internetu to najgorszy dowcip";
        try {
            randomJoke = JokeGetter.getJoke("");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        request.setAttribute("randomJoke", randomJoke);

        List<String> categoriesList = JokeGetter.categoriesList();
        request.setAttribute("categoriesList", categoriesList);

        request.getRequestDispatcher("/WEB-INF/newindex.jsp").forward(request, response);

    }
}
