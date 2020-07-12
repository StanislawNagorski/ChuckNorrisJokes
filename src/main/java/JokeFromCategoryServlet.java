import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/JokeFromCategoryServlet/*")
public class JokeFromCategoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        String pathInfo = request.getPathInfo();
        String jokeCategory = pathInfo.trim().substring(2);
        String categoryFormat = "<h2>Dowcip z kategorii: %s </h2>";

        writer.printf(categoryFormat,jokeCategory);
        try {
            writer.println(JokeGetter.getJoke(jokeCategory));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

