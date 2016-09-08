import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class GraphAlgorithms {
    /**
     * Find the shortest distance between the start vertex and all other
     * vertices given a weighted graph.
     * You should use the adjacency list representation of the graph.
     *
     * Assume the adjacency list contains adjacent nodes of each node in the
     * order they should be visited. There are no negative edge weights in the
     * graph.
     *
     * If there is no path from from the start vertex to a given vertex,
     * have the distance be INF as seen in the graphs class.
     *
     * @throws IllegalArgumentException if graph or start vertex is null
     * @param graph the graph to search
     * @param start the starting vertex
     * @return map of the shortest distance between start and all other vertices
     */
    public static Map<Vertex, Integer> dijkstraShortestPath(Graph graph,
                                                            Vertex start) {
        if (graph == null) {
            throw new IllegalArgumentException("graph is null");
        }
        if (start == null) {
            throw new IllegalArgumentException("start is null");
        }
        int[][] weights = graph.getAdjacencyMatrix();
        Map<Vertex, Integer> distances = new HashMap<>();
        for (Vertex v : graph.getVertices()) {
            distances.put(v, Graph.INF);
        }
        Set<Vertex> set = new HashSet<>();
        Set<Vertex> unset = new HashSet<>();
        distances.put(start, 0);
        unset.add(start);
        while (!unset.isEmpty()) {
            Vertex examineNode = getLowestDistance(unset, distances);
            unset.remove(examineNode);
            set.add(examineNode);
            Map<Vertex, Integer> m = graph.getAdjacencies(examineNode);
            if (m != null) {
                for (Vertex v : m.keySet()) {
                    if (!set.contains(v)) {
                        int edgeD = weights[examineNode.getId()][v.getId()];
                        int recalc = edgeD + distances.get(examineNode);
                        if (distances.get(v) > recalc) {
                            distances.put(v, recalc);
                            unset.add(v);
                        }
                    }
                }
            }
        }
        return distances;
    }

    /**
     * Find the vertex in a set with the lowest distance
     * @param s The set of vertices to evaluate
     * @param distances The map of vertices to distances
     * @return vertex with lowest distance
     */
    private static Vertex getLowestDistance(Set<Vertex> s, Map<Vertex,
            Integer> distances) {
        int lowest = Graph.INF;
        Vertex lowNode = null;
        for (Vertex v : s) {
            if (distances.get(v) < lowest) {
                lowNode = v;
                lowest = distances.get(v);
            }
        }
        return lowNode;
    }

    /**
     * Run Floyd Warshall on the given graph to find the length of all of the
     * shortest paths between vertices.
     *
     * You will also detect if there are negative cycles in the
     * given graph.
     *
     * You should use the adjacency matrix representation of the graph.
     *
     * If there is no path from from the start vertex to a given vertex,
     * have the distance be INF as seen in the graphs class.
     *
     * @throws IllegalArgumentException if graph is null
     * @param graph the graph
     * @return the distances between each vertex. For example, given {@code arr}
     * represents the 2D array, {@code arr[i][j]} represents the distance from
     * vertex i to vertex j. Return {@code null} if there is a negative cycle.
     */
    public static int[][] floydWarshall(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("graph is null");
        }
        int[][] g = graph.getAdjacencyMatrix();
        Set<Vertex> vertices = graph.getVertices();
        for (int k = 0; k < vertices.size(); k++) {
            for (int i = 0; i < vertices.size(); i++) {
                for (int j = 0; j < vertices.size(); j++) {
                    if (g[i][j] > g[i][k] + g[k][j]) {
                        g[i][j] = g[i][k] + g[k][j];
                        if (g[i][k] + g[k][j] < 0) {
                            return null;
                        }
                    }
                }
            }
        }
        return g;
    }

    /**
     * A topological sort is a linear ordering of vertices with the restriction
     * that for every edge uv, vertex u comes before v in the ordering.
     *
     * You should use the adjacency list representation of the graph.
     * When considering which vertex to visit next while exploring the graph,
     * choose the next vertex in the adjacency list.
     *
     * You should start your topological sort with the smallest vertex
     * and if you need to select another starting vertex to continue
     * with your sort (like with a disconnected graph),
     * you should choose the next smallest applicable vertex.
     *
     * @throws IllegalArgumentException if the graph is null
     * @param graph a directed acyclic graph
     * @return a topological sort of the graph
     */
    public static List<Vertex> topologicalSort(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph is null");
        }
        int n = graph.getVertices().size();
        HashMap<Vertex, Boolean> added = new HashMap<>();
        LinkedList<Vertex> sorted = new LinkedList<>();
        for (Vertex v : graph.getVertices()) {
            added.put(v, false);
        }
        for (Vertex v : graph.getVertices()) {
            if (!added.get(v)) {
                dF(graph, added, sorted, v);
            }
        }
        return sorted;
    }

    /**
     * Implements depth first search
     * @param g Graph
     * @param added used nodes
     * @param sorted List added to
     * @param u Vertex to start
     */
    private static void dF(Graph g, HashMap<Vertex, Boolean> added,
                                    LinkedList<Vertex> sorted, Vertex u) {
        added.put(u, true);
        if (g.getAdjacencies(u) != null) {
            for (Vertex v : g.getAdjacencies(u).keySet()) {
                if (!added.get(v)) {
                    dF(g, added, sorted, v);
                }
            }
        }
        sorted.addFirst(u);
    }

    /**
     * A class that pairs a vertex and a distance. Hint: might be helpful
     * for Dijkstra's.
     */
    private static class VertexDistancePair implements
        Comparable<VertexDistancePair> {
        private Vertex vertex;
        private int distance;

        /**
         * Creates a vertex distance pair
         * @param vertex the vertex to store in this pair
         * @param distance the distance to store in this pair
         */
        public VertexDistancePair(Vertex vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        /**
         * Gets the vertex stored in this pair
         * @return the vertex stored in this pair
         */
        public Vertex getVertex() {
            return vertex;
        }

        /**
         * Sets the vertex to be stored in this pair
         * @param vertex the vertex to be stored in this pair
         */
        public void setVertex(Vertex vertex) {
            this.vertex = vertex;
        }

        /**
         * Gets the distance stored in this pair
         * @return the distance stored in this pair
         */
        public int getDistance() {
            return distance;
        }

        /**
         * Sets the distance to be stored in this pair
         * @param distance the distance to be stored in this pair
         */
        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(VertexDistancePair v) {
            return (distance < v.getDistance() ? -1
                                          : distance > v.getDistance() ? 1 : 0);
        }
    }
}