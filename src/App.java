import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    private static final String UNSTYLED_TEXT = "\u001b[0m";
    private static final String BOLD_TEXT = "\u001b[1m";
    private static final String ITALIC_TEXT = "\u001b[3m";

    public static void main(String[] args) throws Exception {
        String directoryString = "saida/";
        File directory = new File(directoryString);
        directory.mkdir();

        //https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json

        //Connection https
        String urlTopMovies = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        var consumer = new ConsumerClientHttp();
        String body = consumer.searchData(urlTopMovies);

        // Catch data (title, poster, rate)
        var generator = new StickerGenerator();
        var parser = new JsonParser();
        int countRanking = 1;
        List<Map<String, String>> movieList = parser.parse(body);

        directoryString = "saida/TopMovies/";
        directory = new File(directoryString);
        directory.mkdir();

        generateTopic("Top Movies");
        //Show and manipulate data Top Movies
        for (Map<String,String> movie : movieList) {    
            String title = movie.get("title");
            String year = movie.get("year");
            String rating = movie.get("imDbRating");
            generateTextItem(title, year, rating, countRanking);
            countRanking++;
            String urlImage= movie.get("image");
            InputStream inputStream = new URL(urlImage).openStream();        
            String fileName = title + "TopMovie.png";
            generator.generate(inputStream, fileName, directoryString, title);
        }

        //Top Series
        String urlTopTVSeries = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        body = consumer.searchData(urlTopTVSeries);
        List<Map<String, String>> tvSerieList = parser.parse(body);
        countRanking = 1;

        directoryString = "saida/TopTVSeries/";
        directory = new File(directoryString);
        directory.mkdir();
        generateTopic("Top TV Series");
        for (Map<String,String> tvSerie : tvSerieList) {    
            String title = tvSerie.get("title");
            String year = tvSerie.get("year");
            String rating = tvSerie.get("imDbRating");
            generateTextItem(title, year, rating, countRanking);
            countRanking++;
            String urlImage= tvSerie.get("image");
            InputStream inputStream = new URL(urlImage).openStream();        
            String fileName = title + "TopTvSerie.png";
            generator.generate(inputStream, fileName, directoryString, title);
        }

        directoryString = "saida/PopularTVSeries/";
        directory = new File(directoryString);
        directory.mkdir();
        //Popular Series
        countRanking = 1;
        String urlPopularTVSeries = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
        body = consumer.searchData(urlPopularTVSeries);
        tvSerieList = parser.parse(body);

        generateTopic("Popular TV Series");
        for (Map<String,String> tvSerie : tvSerieList) {    
            String title = tvSerie.get("title");
            String year = tvSerie.get("year");
            String rating = tvSerie.get("imDbRating");
            generateTextItem(title, year, rating, countRanking);
            countRanking++;
            String urlImage= tvSerie.get("image");
            InputStream inputStream = new URL(urlImage).openStream();        
            String fileName = title + "PopularTvSerie.png";
            generator.generate(inputStream, fileName, directoryString, title);
        }
    }

    public static void generateTopic(String topic){
        System.out.println();
        System.out.println(BOLD_TEXT + topic + ":" + UNSTYLED_TEXT);
        System.out.println();
    }

    public static void generateTextItem(String title, String year, String rating, int countRanking){
        String podiumMedal, stars;
        double ratingNumber = Double.parseDouble(rating);

        if (countRanking == 1) { podiumMedal = UNSTYLED_TEXT + " \uD83E\uDD47"; }
        else if (countRanking == 2) { podiumMedal = UNSTYLED_TEXT + " \uD83E\uDD48"; }
        else if (countRanking == 3) { podiumMedal = UNSTYLED_TEXT + " \uD83E\uDD49"; }
        else{ podiumMedal = ""; }
        if (ratingNumber <= 10 && ratingNumber > 8){stars = " \u2B50 \u2B50 \u2B50 \u2B50 \u2B50";}
        else if (ratingNumber <= 8 && ratingNumber > 6){stars = " \u2B50 \u2B50 \u2B50 \u2B50";}
        else if (ratingNumber <= 6 && ratingNumber > 4){stars = " \u2B50 \u2B50 \u2B50";}
        else if (ratingNumber <= 4 && ratingNumber > 2){stars = " \u2B50 \u2B50";}
        else{stars = " \u2B50";}
        
        System.out.println(UNSTYLED_TEXT + countRanking + "." + ITALIC_TEXT + "\u001b[38;5;25m" + title + UNSTYLED_TEXT + podiumMedal);
        System.out.println(UNSTYLED_TEXT + "\u001b[38;5;70m  Year" + UNSTYLED_TEXT + ": (\u001b[38;5;245m" + year + UNSTYLED_TEXT + ")");
        System.out.println(UNSTYLED_TEXT + "\u001b[38;5;226m  Rating" + UNSTYLED_TEXT + ": " + BOLD_TEXT + rating + UNSTYLED_TEXT + stars);
        System.out.println();
        countRanking++;
    }
}
