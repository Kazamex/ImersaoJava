import java.util.List;

public interface DataExtractor {
    List<ContentAPI> extractContent(String json);
}
