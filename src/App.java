import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    private static final String UNSTYLED_TEXT = "\u001b[0m";
    private static final String BOLD_TEXT = "\u001b[1m";
    private static final String ITALIC_TEXT = "\u001b[3m";
    private static final String DIRECTORY_NASA = "saida/Nasa/";
    private static final String URL_API_NASA = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
    private static final String DIRECTORY_TOP_MOVIES = "saida/TopMovies/";
    private static final String URL_API_IMDB_TOP_MOVIES = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
    private static final String DIRECTORY_TOP_TV_SERIES = "saida/TopTVSeries/";
    private static final String URL_API_IMDB_TOP_TV_SERIES = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
    private static final String DIRECTORY_POPULAR_TV_SERIES = "saida/PopularTVSeries/";
    private static final String URL_API_IMDB_POPULAR_TV_SERIES = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
    
    public static void main(String[] args) throws Exception {
        createFolder("saida/");
        var generator = new StickerGenerator();

        //Stickers Nasa
        //Connection https
        var consumer = new ConsumerClientHttp();
        String body = consumer.searchData(URL_API_NASA);

        // Catch data (title, poster, rate)
        createFolder(DIRECTORY_NASA);
        var nasaExtractor = new DataExtractorNasa();
        List<ContentAPI> nasaContents = nasaExtractor.extractContent(body);
        
        for (int i=0; i < nasaContents.size(); i++){
            ContentAPI content = nasaContents.get(i);
            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            generator.generate(inputStream, content.getFileName(), DIRECTORY_NASA, content.getTitle());
        }

        //Show and manipulate data Top Movies
        generateIMDBList(DIRECTORY_TOP_MOVIES,URL_API_IMDB_TOP_MOVIES,"Top Movies");
        generateIMDBList(DIRECTORY_TOP_TV_SERIES,URL_API_IMDB_TOP_TV_SERIES,"Top TV Series");
        generateIMDBList(DIRECTORY_POPULAR_TV_SERIES,URL_API_IMDB_POPULAR_TV_SERIES,"Popular TV Series");
        
    }

    public static void createFolder(String directory){
        File fileDirectory = new File(directory);
        fileDirectory.mkdir();
    }

    public static void generateIMDBList(String directory, String url, String topicTitle) throws Exception{
        var consumer = new ConsumerClientHttp();
        var generator = new StickerGenerator();

        generateTopic(topicTitle);
        //Show and manipulate data Top Movies

        // Catch data (title, poster, rate)
        String body = consumer.searchData(url);
        createFolder(directory);
        var imdbExtractor = new DataExtractorIMDB();
        List<ContentAPI> imdbContents = imdbExtractor.extractContent(body);

        for (int i=0; i < imdbContents.size(); i++){
            ContentAPI content = imdbContents.get(i);
            generateTextItem(content.getTitle(), content.getYear(), content.getRating(), i+1);
            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            generator.generate(inputStream, content.getFileName(), directory, content.getTitle());
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
