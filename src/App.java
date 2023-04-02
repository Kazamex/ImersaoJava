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
        String urlTopMovies = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(urlTopMovies);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Catch data (title, poster, rate)
        var generator = new StickerGenerator();
        var parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);

        //Show and manipulate data Top Movies
        for (Map<String,String> movie : movieList) {    
            String urlImage= movie.get("image");
            String title = movie.get("title");
            InputStream inputStream = new URL(urlImage).openStream();        
            String fileName = title + "TopMovie.png";
            generator.generate(inputStream, fileName);
            System.out.println(title);
        }

        //Top Series
        String urlTopTVSeries = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        address = URI.create(urlTopTVSeries);
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder(address).GET().build();
        client.send(request, BodyHandlers.ofString());
        body = response.body();
        List<Map<String, String>> tvSerieList = parser.parse(body);

        System.out.println();
        System.out.println("Top TV Series:");

        for (Map<String,String> tvSerie : tvSerieList) {    
            String urlImage= tvSerie.get("image");
            String title = tvSerie.get("title");
            InputStream inputStream = new URL(urlImage).openStream();        
            String fileName = title + "TopTvSerie.png";
            generator.generate(inputStream, fileName);
            System.out.println(title);
        }

        //Popular Series
        String urlPopularTVSeries = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
        address = URI.create(urlPopularTVSeries);
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder(address).GET().build();
        response = client.send(request, BodyHandlers.ofString());
        body = response.body();
        tvSerieList = parser.parse(body);

        System.out.println();
        System.out.println("Popular TV Series:");

        for (Map<String,String> tvSerie : tvSerieList) {    
            String urlImage= tvSerie.get("image");
            String title = tvSerie.get("title");
            InputStream inputStream = new URL(urlImage).openStream();        
            String fileName = title + "PopularTvSerie.png";
            generator.generate(inputStream, fileName);
            System.out.println(title);
        }
    }
}
