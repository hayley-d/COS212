import java.util.*;
class Graph {
    private int numVertices;
    private LinkedList<Integer> adjacencyList[];

    // Constructor
    Graph(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    // Add edges
    void addEdge(int source, int destination) {
        adjacencyList[source].add(destination);

    }

    // Helper method for DFS that uses recursion
    private void DFSUtil(int vertex, boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[vertex] = true;
        System.out.print(vertex + " ");

        // Recur for all the vertices adjacent to this vertex
        for (Integer neighbor : adjacencyList[vertex])
        {
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited);
            }
        }
    }

    // The DFS traversal method
    public void DFS(int startVertex)
    {
        // Mark all the vertices as not visited (set as false by default)
        boolean visited[] = new boolean[numVertices];

        // Call the recursive helper method to print DFS traversal
        DFSUtil(startVertex, visited);
    }

    // BFS traversal from a given source
    public void BFS(int startVertex) {
        boolean visited[] = new boolean[numVertices];

        LinkedList<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited[startVertex] = true;
        queue.add(startVertex);

        while (queue.size() != 0) {
            // Dequeue a vertex from the queue and print it
            startVertex = queue.poll();
            System.out.print(startVertex + " ");

            // Get all adjacent vertices of the dequeued vertex
            // If an adjacent vertex has not been visited, mark it visited and enqueue it
            Iterator<Integer> it = adjacencyList[startVertex].listIterator();
            while (it.hasNext()) {
                int nextVertex = it.next();
                if (!visited[nextVertex]) {
                    visited[nextVertex] = true;
                    queue.add(nextVertex);
                }
            }
        }
    }

    // Helper method for topological sort that uses recursion
    private void topologicalSortUtil(int vertex, boolean visited[], Stack<Integer> stack)
    {
        // Mark the current node as visited
        visited[vertex] = true;

        // Recur for all the vertices adjacent to this vertex
        for (Integer neighbor : adjacencyList[vertex]) {
            if (!visited[neighbor]) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }

        // Push the current vertex to the stack which stores the result
        stack.push(vertex);
    }

    // The topological sort method
    void topologicalSort() {
        Stack<Integer> stack = new Stack<>();

        // Mark all the vertices as not visited
        boolean visited[] = new boolean[numVertices];

        // Call the recursive helper method to store topological sort starting from all vertices one by one
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        // Print the contents of the stack
        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
    }

}
