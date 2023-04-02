import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_JSON_ATTRIBUTES = Pattern.compile("\"(.+?)\":\"(.*?)\"");
    
    public List<Map<String, String>> parse(String json) {
        Matcher matcher = REGEX_ITEMS.matcher(json);
        
        if (!matcher.find()) {
            throw new IllegalArgumentException("Did not find files");
        }

        String[] items = matcher.group(1).split("\\},\\{");
        List<Map<String, String>> data = new ArrayList<>();

        for (String item : items) {
            Map<String, String> itemAttributes = new HashMap<>();
            Matcher matcherJsonAttributes = REGEX_JSON_ATTRIBUTES.matcher(item);

            while (matcherJsonAttributes.find()) {
                String atributo = matcherJsonAttributes.group(1);
                String valor = matcherJsonAttributes.group(2);
                itemAttributes.put(atributo, valor);
            }
            data.add(itemAttributes);
        }
        return data;
    }
}
