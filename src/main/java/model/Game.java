package model;
public class Game {

    private final String homeTeam;
    private final String foreignTeam;
    private final String date;
    private final String time;

    public Game(String homeTeam, String foreignTeam, String date, String time) {
        this.homeTeam = homeTeam;
        this.foreignTeam = foreignTeam;
        this.date = date;
        this.time = time;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getForeignTeam() {
        return foreignTeam;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
