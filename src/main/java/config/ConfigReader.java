package config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigReader {

    private final File file;
    private final ObjectMapper objectMapper;

    public ConfigReader(String path) {
        this.file = new File(path);
        this.objectMapper = new ObjectMapper(new YAMLFactory());
    }

    public List<String> getPages() throws IOException {
        JsonNode pagesJsonNode = getJsonNode("pages");
        List<String> pages = new ArrayList<>();
        pagesJsonNode.forEach(node -> pages.add(node.textValue()));
        return pages;
    }

    public List<String> getTeamsWithIndex() throws IOException {
        JsonNode teamsWithIndexJsonNode = getJsonNode("teams-with-index");
        List<String> teamsWithIndex = new ArrayList<>();
        teamsWithIndexJsonNode.forEach(node -> teamsWithIndex.add(node.textValue()));
        return teamsWithIndex;
    }

    public boolean getPostingEnabled() throws IOException {
        JsonNode postingEnabledJsonNode = getJsonNode("posting-enabled");
        return postingEnabledJsonNode.asBoolean(false);
    }

    public String getCalendarId() throws IOException {
        JsonNode calendarIdJsonNode = getJsonNode("calendar-id");
        return calendarIdJsonNode.asText("primary");
    }

    public String getHomeTeam() throws IOException, ConfigurationException {
        JsonNode homeTeamJsonNode = getJsonNode("home-team");
        if (homeTeamJsonNode instanceof NullNode) {
            throw new ConfigurationException("'home-team' property must be set in application.yml");
        }
        return homeTeamJsonNode.asText("");
    }

    private JsonNode getJsonNode(String key) throws IOException {
        return objectMapper.readTree(this.file).get(key);
    }
}
