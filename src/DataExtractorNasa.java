import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataExtractorNasa implements DataExtractor{
    public List<ContentAPI> extractContent(String json){
        var parser = new JsonParser();
        List<Map<String, String>> dataList = parser.parse(json);
        List<ContentAPI> contents = new ArrayList<>();
        
        //Populate list
        for(Map<String,String> attribute : dataList){
            String title = attribute.get("title");
            String urlImage = attribute.get("url");
            var content = new ContentAPI(title, urlImage);
            contents.add(content);
        }

        return contents;
    }
}
