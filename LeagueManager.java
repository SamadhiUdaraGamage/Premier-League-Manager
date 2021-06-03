import java.util.Date;
import java.util.List;

public interface LeagueManager {
    public boolean addfootballclub(FootballClub claub);
    public boolean deleteFootballClub(String line);
    public void AddPlayedMatch(Match match);
    public void RunGUI();


    Match getMatchbyDate(Date date);
}
