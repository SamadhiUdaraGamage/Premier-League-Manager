import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Match implements Serializable {
    private FootballClub teamX;
    private FootballClub teamY;
    private String name1;
    private String name2;
    private int teamXScore;
    private int teamYScore;
    private Date date;

    public Match() {

    }

    public FootballClub getTeamX() {
        return teamX;
    }

    public void setTeamX(FootballClub teamX) {
        this.teamX = teamX;
    }

    public FootballClub getTeamY() {
        return teamY;
    }

    public void setTeamY(FootballClub teamY) {
        this.teamY = teamY;
    }

    public int getTeamXScore() {
        return teamXScore;
    }

    public void setTeamXScore(int teamXScore) {
        this.teamXScore = teamXScore;
    }

    public int getTeamYScore() {
        return teamYScore;
    }

    public void setTeamYScore(int teamYScore) {
        this.teamYScore = teamYScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    @Override
    public String toString() {
        return "Match{" +
                "teamX=" + teamX +
                ", teamY=" + teamY +
                ", xName='" + name1 + '\'' +
                ", yName='" + name2 + '\'' +
                ", teamXScore=" + teamXScore +
                ", teamYScore=" + teamYScore +
                ", date=" + date +
                '}';
    }
}
