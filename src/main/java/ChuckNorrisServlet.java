import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ChuckNorrisServlet")
public class ChuckNorrisServlet extends HttpServlet {

    private final String h1Format = "<h1> %s </h1>";
    private final String h2Format = "<h2> %s </h2>";
    private final String h3Format = "<h3> %s </h3>";
    private final String h4Format = "<h4> %s </h4>";
    private final String linkFormat = "<h4><a href=\"JokeFromCategoryServlet/ %s \"> %s </a></h4>";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.printf(h1Format, "Wybierz kategorię dowcipów o Chucku:");

        //response.sendRedirect();
        //atrybut odpowiedzi
        //redirect do JSP

        writer.printf(h4Format, "losowy dowcip");

        try {
            writer.printf(h4Format, JokeGetter.getJoke(""));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        writer.println("Kategorie dowcipów:");
        writer.println("<br>");
        List<String> categoriesList = JokeGetter.categoriesList();
        for (String category : categoriesList) {
            String trimCategory = category.trim();
            if (!category.equals("")) {
                writer.printf(linkFormat, trimCategory, trimCategory);
            }
        }
    }
}
