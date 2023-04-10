package content;

import config.ConfigReader;
import model.Game;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.naming.ConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsoupHelper {

    public JsoupHelper() {
    }

    public String getTitle(Document document) {
        return document.body().getElementById("title").text();
    }

    /**
     * @param document the page with gamedates of a specific team as document
     * @return a list of table rows of the date table
     */
    public Elements getTableRows(Document document) {
        // Get all elements by tag tbody which is a table
        Elements tables = document.body().getElementsByTag("tbody");
        // The second table is the date table
        Element dateTable = tables.get(1);
        // Return list (as Elements) of all table rows
        return dateTable.getElementsByTag("tr");
    }

    public List<Game> createGamesFromTableRows(String team, Elements tablerows, ConfigReader configReader) throws IOException, ConfigurationException {
        int index = 0;
        if (configReader.getTeamsWithIndex().stream().anyMatch(teamWithIndex -> teamWithIndex.equals(team))) {
            index = 2;
        }
        List<Game> games = new ArrayList<>();
        String currentDateTime = null;
        for (int i = 0; i < tablerows.size(); i++) {
            if (i == 0) {
                // First row is header of table in which we are not interested
                continue;
            }
            Elements td = tablerows.get(i).getElementsByTag("td");
            String dateTime;
            if (td.get(1).text().equals("")) {
                dateTime = currentDateTime;
            } else {
                dateTime = td.get(1).text();
            }
            String date = dateTime.split(" ")[0];
            String time = dateTime.split(" ")[1];
            currentDateTime = dateTime;
            String homeTeam = td.get(3 + index).text();
            String guestTeam = td.get(4 + index).text();
            if (homeTeam.contains(configReader.getHomeTeam()) || guestTeam.contains(configReader.getHomeTeam())) {
                Game game = createGame(team, homeTeam, guestTeam, date, time, homeTeam.contains(configReader.getHomeTeam()));
                games.add(game);
            }
        }
        return games;
    }

    private Game createGame(String team, String homeTeam, String guestTeam, String date, String time, boolean isHome) {
        return new Game(team, homeTeam, guestTeam, date, time, isHome);
    }
}
