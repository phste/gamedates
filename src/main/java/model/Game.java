package model;

public class Game {

    private final String team;
    private final String homeTeam;
    private final String guestTeam;
    private final String date;
    private final String time;

    private final boolean isHome;

    public Game(String team, String homeTeam, String guestTeam, String date, String time, boolean isHome) {
        this.team = team;
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.date = date;
        this.time = time;
        this.isHome = isHome;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getSummary() {
        String location = this.isHome ? "[HEIM]" : "[AUSWÄRTS]";
        return this.team + ": " + this.homeTeam + " - " + this.guestTeam + " - " + location;
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
