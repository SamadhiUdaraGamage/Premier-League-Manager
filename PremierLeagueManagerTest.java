import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class PremierLeagueManagerTest {
    PremierLeagueManager plm;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        plm = new PremierLeagueManager();
    }

    @org.junit.jupiter.api.Test
    void addfootballclub() {
        FootballClub ft1 = new FootballClub("qq","yy");
        plm.addfootballclub(ft1);

        assertEquals(1, plm.league.size());
    }

    @org.junit.jupiter.api.Test
    void addExistingfootballclub() {
        FootballClub ft1 = new FootballClub("qq","yy");
        plm.addfootballclub(ft1);

        FootballClub ft2 = new FootballClub("qq","yy");

        boolean sucess= plm.addfootballclub(ft2);


       assertEquals(false,sucess);
    }

    @org.junit.jupiter.api.Test
    void deleteFootballClub() {
        FootballClub ft1 = new FootballClub("qq","yy");
        plm.addfootballclub(ft1);

        plm.deleteFootballClub(ft1.getName());

        assertEquals(0, plm.league.size());
    }

    @org.junit.jupiter.api.Test
    void deleteNotExistingFootballClub() {
        FootballClub ft1 = new FootballClub("qq","yy");
        plm.addfootballclub(ft1);

        boolean sucess=plm.deleteFootballClub("ww");

        assertEquals(false,sucess);
    }


    @org.junit.jupiter.api.Test
    void AddPlayedMatch() {
        Match match = new Match();
        plm.AddPlayedMatch(match);

        assertEquals(1, plm.matchArrayList.size());
    }

    @org.junit.jupiter.api.Test
    void getMatchbyDateExisting() throws ParseException {


        Match match = new Match();
        match.setDate(new SimpleDateFormat("yyyy/mm/dd").parse("2020/12/20"));
        plm.AddPlayedMatch(match);

       Match search =  plm.getMatchbyDate(new SimpleDateFormat("yyyy/mm/dd").parse("2020/12/20"));

       assertNotNull(search);
    }

    @org.junit.jupiter.api.Test
    void getMatchbyDateNotExisting() throws ParseException {

        Match match = new Match();
        match.setDate(new SimpleDateFormat("yyyy/mm/dd").parse("2020/12/20"));
        plm.AddPlayedMatch(match);

        Match search =  plm.getMatchbyDate(new SimpleDateFormat("yyyy/mm/dd").parse("2020/12/21"));

       assertNull(search);
    }

}