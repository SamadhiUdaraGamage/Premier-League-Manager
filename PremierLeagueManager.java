import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class PremierLeagueManager implements LeagueManager,  Serializable {


    private static final long serialVersionUID = 1L;
//    private List<SportsClub> clubList = new ArrayList<SportsClub>();
//    private List<Match>clubList1=new ArrayList<>();


    public final ArrayList<FootballClub> league;
    public final ArrayList<Match> matchArrayList;
    private final Scanner scanner = null;
    private FootballClub[] getclubList;
    private Match[] getclubList1;


    public PremierLeagueManager() {
        this.matchArrayList = new ArrayList<Match>();
        league = new ArrayList<FootballClub>();
    }


    @Override
    public boolean addfootballclub(FootballClub club) {

        for (FootballClub club1 : league) {
            if (Objects.equals(club1.getName(), club.getName())) {

                return false;
            }
        }
        league.add(club);
        return true;

    }


    @Override
    public boolean deleteFootballClub(String clubname) {

        for (FootballClub club1 : league) {

            if (Objects.equals(club1.getName(), clubname)) {
                league.remove(club1);
                return true;
            }
        }
        return false;


    }

    @Override
    public Match getMatchbyDate(Date date){

        for (Match match : matchArrayList) {

            if (Objects.equals(match.getDate(), date)) {

                return match;
            }
        }

        return null;
    }

    @Override
    public void AddPlayedMatch(Match match) {
        this.matchArrayList.add(match);
    }

    @Override
    public void RunGUI() {

    }
}

