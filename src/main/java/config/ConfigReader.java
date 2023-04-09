package config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigReader {

    private final String path;
    private final ObjectMapper objectMapper;

    public ConfigReader(String path) {
        this.path = path;
        this.objectMapper = new ObjectMapper(new YAMLFactory());
    }

    public List<String> getPages() throws IOException {
        File file = new File(path);

        JsonNode pagesJsonNode = getJsonNode(file, "pages");
        List<String> pages = new ArrayList<>();
        pagesJsonNode.forEach(node -> pages.add(node.textValue()));
        return pages;
    }

    public List<String> getTeamsWithIndex() throws IOException {
        File file = new File(path);

        JsonNode teamsWithIndexJsonNode = getJsonNode(file, "teamswithindex");
        List<String> teamsWithIndex = new ArrayList<>();
        teamsWithIndexJsonNode.forEach(node -> teamsWithIndex.add(node.textValue()));
        return teamsWithIndex;
    }

    private JsonNode getJsonNode(File file, String key) throws IOException {
        return objectMapper.readTree(file).get(key);
    }
}
