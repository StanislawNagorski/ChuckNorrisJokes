import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class JokeGetter {

    private static final String HOST = "https://api.chucknorris.io/jokes/random";
    private static Map<String, String> jokeMap;

    public static JSONObject getJokeJsonObject(String category) throws IOException, ParseException {

        jokeMap = new LinkedHashMap<>();
        URL url;
        if (!category.isBlank()) {
            String urlWithCategory = HOST + "?category=" + category;
            url = new URL(urlWithCategory);
        } else {
            url = new URL(HOST);
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        StringBuilder jsonStringBuilder = new StringBuilder();
        if (responseCode != 200) {
            throw new RuntimeException("Response code: " + responseCode);
        } else {
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                jsonStringBuilder.append(scanner.nextLine());
            }
            scanner.close();
        }

        String jsonString = jsonStringBuilder.toString();

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(jsonString);

        String id = String.valueOf(jsonObject.get("id"));
        String jokeString = String.valueOf(jsonObject.get("value"));

        jokeMap.put(id, jokeString);

        return jsonObject;
    }

    public static String getJoke(String category) throws IOException, ParseException {
        return String.valueOf(getJokeJsonObject(category).get("value"));
    }

    public static List<String> categoriesList() throws IOException {
        List<String> categories = new ArrayList<>();
        String categoryListHOST = "https://api.chucknorris.io/jokes/categories";
        URL categoriesURL = new URL(categoryListHOST);
        HttpURLConnection connection = (HttpURLConnection) categoriesURL.openConnection();
        Scanner scanner = new Scanner(categoriesURL.openStream());

        String categoriesTotalString = scanner.nextLine();
        String[] categoriesArray = categoriesTotalString.split("\\W");
        for (String categoryString : categoriesArray) {
            if (!categoryString.isBlank()){
                categories.add(categoryString.trim());
            }
        }
        return categories;
    }


}
