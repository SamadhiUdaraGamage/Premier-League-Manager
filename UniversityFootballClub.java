import java.util.Objects;

public class UniversityFootballClub extends SportsClub {
    private String universityName;



    public UniversityFootballClub(String universityName,String name,String location,String statistics) {
        super(name,location,statistics);
        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversityFootballClub that = (UniversityFootballClub) o;
        return Objects.equals(universityName, that.universityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityName);
    }

    @Override
    public String toString() {
        return "UniversityFootballClub{" +
                "universityName='" + universityName + '\'' +
                '}';
    }
}

