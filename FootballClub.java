import java.io.Serializable;
import java.util.Objects;

public class FootballClub extends SportsClub implements Serializable {

    private int winCount;
    private int drawCount;
    private int defeatCount;
    private int receivedGoalsCount;
    private int scoredGoalsCount;
    private int points;
    private int matchesPlayed;


    public FootballClub(String name,String location) {
        super(name,location,"");
        this.winCount=0;
        this.drawCount=0;
        this.defeatCount=0;
        this.receivedGoalsCount=0;
        this.scoredGoalsCount=0;
        this.points=0;
        this.matchesPlayed=0;

    }

    public FootballClub(String name, String location, String statistics, int winCount,int drawCount,int defeatCount,int receivedGoalsCount,int scoredGoalsCount,int points,int matchesPlayed) {
        super(name, location, statistics);
        this.winCount = winCount;
        this.drawCount=drawCount;
        this.defeatCount=defeatCount;
        this.receivedGoalsCount=receivedGoalsCount;
        this.scoredGoalsCount=scoredGoalsCount;
        this.points=points;
        this.matchesPlayed=matchesPlayed;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(int drawCount) {
        this.drawCount = drawCount;
    }

    public int getDefeatCount() {
        return defeatCount;
    }

    public void setDefeatCount(int defeatCount) {
        this.defeatCount = defeatCount;
    }

    public int getReceivedGoalsCount() {
        return receivedGoalsCount;
    }

    public void setReceivedGoalsCount(int receivedGoalsCount) {
        this.receivedGoalsCount = receivedGoalsCount;
    }

    public int getScoredGoalsCount() {
        return scoredGoalsCount;
    }

    public void setScoredGoalsCount(int scoredGoalsCount) {
        this.scoredGoalsCount = scoredGoalsCount;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballClub that = (FootballClub) o;
        return winCount == that.winCount &&
                drawCount == that.drawCount &&
                defeatCount == that.defeatCount &&
                receivedGoalsCount == that.receivedGoalsCount &&
                scoredGoalsCount == that.scoredGoalsCount &&
                points == that.points &&
                matchesPlayed == that.matchesPlayed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(winCount, drawCount, defeatCount, receivedGoalsCount, scoredGoalsCount, points, matchesPlayed);
    }

    @Override
    public String toString() {
        return "FootballClub{" +
                "winCount=" + winCount +
                ", drawCount=" + drawCount +
                ", defeatCount=" + defeatCount +
                ", receivedGoalsCount=" + receivedGoalsCount +
                ", scoredGoalsCount=" + scoredGoalsCount +
                ", points=" + points +
                ", matchesPlayed=" + matchesPlayed +
                '}';
    }
}

