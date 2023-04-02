import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //Connection https
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        HttpClient client = HttpClient.newHttpClient();
        URI address = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Catch data (title, poster, nota)
        var parser = new JsonParser();
        List<Map<String, String>> filmList = parser.parse(body);
        
        var generator = new StickerGenerator();
        //Show and manipulate data
        for (Map<String,String> film : filmList) {
            
            String urlImage= film.get("image");
            String title = film.get("title");
            InputStream inputStream = new URL(urlImage).openStream();        
            String fileName = title + ".png";

            generator.generate(inputStream, fileName);
            
            System.out.println(title);
            System.out.println();
        }

    }
}
