public class Road implements Comparable<Road> {
    private String roadName;
    private Town roadSource, roadDestination;
    private int weight;

    public Road(Town source, Town destination, int weight, String name) {
        this.roadSource = source;
        this.roadDestination = destination;
        this.weight = weight;
        this.roadName = name;
    }

    public String getName() {
        return roadName;
    }

    public Town getSource() {
        return roadSource;
    }

    public Town getDestination() {
        return roadDestination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Road)) return false;
        Road other = (Road) obj;
        return ((roadSource.equals(other.roadSource) && roadDestination.equals(other.roadDestination)) ||
                (roadSource.equals(other.roadDestination) && roadDestination.equals(other.roadSource))) &&
                roadName.equalsIgnoreCase(other.roadName) &&
                weight == other.weight;
    }


    @Override
    public int hashCode() {
        return roadSource.hashCode() + roadDestination.hashCode() + roadName.hashCode() + weight;
    }

    @Override
    public int compareTo(Road o) {
        return this.roadName.compareToIgnoreCase(o.getName());
    }

    @Override
    public String toString() {
        return roadName + ", " + roadSource + " - " + roadDestination + " (" + weight + " mi)";
    }
}
