import java.util.Comparator;

public class CusComparator implements Comparator<FootballClub> {


    @Override
    public int compare(FootballClub f1, FootballClub f2) {

        if (f1.getPoints()>f2.getPoints())
            return -1;
        else if (f1.getPoints()<f2.getPoints())
            return 1;
        else {
            int goaldiffer1=f1.getScoredGoalsCount()-f1.getReceivedGoalsCount();
            int goaldiffer2=f2.getScoredGoalsCount()-f2.getReceivedGoalsCount();
            if (goaldiffer1>goaldiffer2)
                return -1;
            else if (goaldiffer1<goaldiffer2)
                return 1;
            else return 0;
        }
    }
}
