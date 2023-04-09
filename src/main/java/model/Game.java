package model;

public class Game {


    private final String team;
    private final String homeTeam;
    private final String guestTeam;
    private final String date;
    private final String time;

    public Game(String team, String homeTeam, String guestTeam, String date, String time) {
        this.team = team;
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.date = date;
        this.time = time;
    }

    public String getTeam() {
        return team;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getForeignTeam() {
        return guestTeam;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Game{" +
                "team='" + team + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", guestTeam='" + guestTeam + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
