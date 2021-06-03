import java.io.Serializable;
import java.util.Objects;

abstract class SportsClub implements Serializable {
    private String name;
    private String location;
    private String statistics;


    public SportsClub(String name,String location,String statistics) {
        this.name = name;
        this.location=location;
        this.statistics=statistics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatistics() {
        return statistics;
    }

    public void setStatistics(String statistics) {
        this.statistics = statistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsClub that = (SportsClub) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(location, that.location) &&
                Objects.equals(statistics, that.statistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, statistics);
    }

    @Override
    public String toString() {
        return "SportsClub{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", statistics='" + statistics + '\'' +
                '}';
    }
}

