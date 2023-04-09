package api;


import config.ConfigReader;
import content.Parser;
import model.Game;


import java.io.IOException;
import java.util.List;

public class FetchGameDates {

    public static void main(String[] args) {
        ConfigReader configReader = new ConfigReader("src/main/resources/application.yml");
        Parser parser = new Parser(configReader);
        List<String> pages;
        try {
            pages = configReader.getPages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Game> games = pages
                .stream()
                .flatMap(page -> {
                    try {
                        return parser.getGames(page).stream();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

        System.out.printf("Found %s games.", games.size());
        games.forEach(System.out::println);

    }


}
