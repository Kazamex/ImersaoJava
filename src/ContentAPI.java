public class ContentAPI {
    private final String title;
    private final String urlImage;
    private final String rating;
    private final String year;

    public ContentAPI(String title, String urlImage) {
        this.title = title;
        this.urlImage = urlImage;
        this.rating = null;
        this.year = null;
    }

    public ContentAPI(String title, String urlImage, String rating, String year){
        this.title = title;
        this.urlImage = urlImage;
        this.rating = rating;
        this.year = year;
    }

    public String getFileName(){
        return title + ".png";
    }

    public String getTitle() {
        return title;
    }
    public String getUrlImage() {
        return urlImage;
    }

    public String getRating() {
        return rating;
    }

    public String getYear() {
        return year;
    }

    
}
