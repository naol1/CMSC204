import java.util.*;

public class Graph implements GraphInterface<Town, Road> {
    private Map<Town, List<Road>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        if (sourceVertex == null || destinationVertex == null) return null;
        for (Road road : adjList.getOrDefault(sourceVertex, new ArrayList<>())) {
            if (road.getDestination().equals(destinationVertex)) return road;
        }
        return null;
    }

    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        if (sourceVertex == null || destinationVertex == null) throw new NullPointerException();
        if (!adjList.containsKey(sourceVertex) || !adjList.containsKey(destinationVertex))
            throw new IllegalArgumentException();

        Road road = new Road(sourceVertex, destinationVertex, weight, description);
        adjList.get(sourceVertex).add(road);
        adjList.get(destinationVertex).add(new Road(destinationVertex, sourceVertex, weight, description));
        return road;
    }

    @Override
    public boolean addVertex(Town v) {
        if (v == null) throw new NullPointerException();
        if (adjList.containsKey(v)) return false;
        adjList.put(v, new ArrayList<>());
        return true;
    }

    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        return getEdge(sourceVertex, destinationVertex) != null;
    }

    @Override
    public boolean containsVertex(Town v) {
        return adjList.containsKey(v);
    }

    @Override
    public Set<Road> edgeSet() {
        Set<Road> edges = new HashSet<>();
        for (List<Road> roads : adjList.values()) {
            edges.addAll(roads);
        }
        return edges;
    }

    @Override
    public Set<Road> edgesOf(Town vertex) {
        if (!adjList.containsKey(vertex)) throw new IllegalArgumentException();
        return new HashSet<>(adjList.get(vertex));
    }

    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        Road toRemove = getEdge(sourceVertex, destinationVertex);
        if (toRemove != null) {
            adjList.get(sourceVertex).remove(toRemove);
            adjList.get(destinationVertex).removeIf(road -> road.getDestination().equals(sourceVertex));
        }
        return toRemove;
    }

    @Override
    public boolean removeVertex(Town v) {
        if (!adjList.containsKey(v)) return false;
        adjList.remove(v);
        for (List<Road> roads : adjList.values()) {
            roads.removeIf(road -> road.getDestination().equals(v));
        }
        return true;
    }

    @Override
    public Set<Town> vertexSet() {
        return adjList.keySet();
    }

    private Map<Town, Integer> distanceMap;
    private Map<Town, Town> previousVertexMap;

    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        dijkstraShortestPath(sourceVertex);
        ArrayList<String> path = new ArrayList<>();
        Town current = destinationVertex;

        while (current != null && !current.equals(sourceVertex)) {
            Town prev = previousVertexMap.get(current);
            if (prev == null) break;
            Road road = getEdge(prev, current);
            path.add(prev.getName() + " via " + road.getName() + " to " + current.getName() + " " + road.getWeight() + " mi");
            current = prev;
        }

        Collections.reverse(path);
        return path.isEmpty() ? null : path;
    }
 
    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        if (!adjList.containsKey(sourceVertex)) throw new IllegalArgumentException("Source vertex not found");

        // Initialize distances and previous vertex maps
        distanceMap = new HashMap<>();
        previousVertexMap = new HashMap<>();

        for (Town town : adjList.keySet()) {
            distanceMap.put(town, Integer.MAX_VALUE); // Initially set to "infinity"
            previousVertexMap.put(town, null);
        }

        distanceMap.put(sourceVertex, 0); // Distance to source is 0
        PriorityQueue<Town> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distanceMap::get));
        priorityQueue.add(sourceVertex);

        while (!priorityQueue.isEmpty()) {
            Town current = priorityQueue.poll();

            // Iterate over neighbors
            for (Road road : adjList.get(current)) {
                Town neighbor = road.getDestination();
                int newDist = distanceMap.get(current) + road.getWeight();

                // Update distance and previous vertex if a shorter path is found
                if (newDist < distanceMap.get(neighbor)) {
                    distanceMap.put(neighbor, newDist);
                    previousVertexMap.put(neighbor, current);
                    priorityQueue.add(neighbor);
                }
            }
        }
    }

}
