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

        // Comentário
        // Fazer uma conexão http
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        HttpClient client = HttpClient.newHttpClient();
        URI endereco = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Pegar dados (Titulo, poster, nota)
        var parser = new JsonParser();
        List<Map<String, String>> listaFilmes = parser.parse(body);
        
        var geradora = new GeradoraFigurinhas();
        //Exibir e manipular dados
        for (Map<String,String> filme : listaFilmes) {
            
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            InputStream inputStream = new URL(urlImagem).openStream();        
            String nomeArquivo = titulo + ".png";

            geradora.criar(inputStream, nomeArquivo);
            
            System.out.println(titulo);
            System.out.println();
        }

    }
}
