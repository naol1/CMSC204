public class Town implements Comparable<Town> {
    private String townName;

    public Town(String name) {
        this.townName = name;
    }

    public String getName() {
        return townName;
    }

    @Override
    public int compareTo(Town o) {
        return this.townName.compareToIgnoreCase(o.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Town)) return false;
        Town other = (Town) obj;
        return this.townName.equalsIgnoreCase(other.getName());
    }

    @Override
    public int hashCode() {
        return townName.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return townName;
    }
}
