import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    LinkedList<Vertex> vertices;
    LinkedList<Edge> edges;
    Vertex start;

    Maze() {
        this.start = null;
        this.vertices = new LinkedList<>();
        this.edges = new LinkedList<>();
    }

    Maze(String fileName) {
        
    }

    static Maze createMaze(String mazeString) {
        return null;
    }

    String latexCode(){
        String result = "\\documentclass[hidelinks, 12pt, a4paper]{article}\r\n" + //
        "\\usepackage{tikz}\n" + //
        "\n" + //
        "\\begin{document}\n" + //
        "\n" + //
        "\\begin{tikzpicture}[node/.style={circle, draw, minimum size=1.2em},yscale=-1]\n";
        for(Vertex v: getVertices()){
            result += v.latexCode() + "\n";
        }
        result += "\n";
        for(Edge e: getEdges()){
            result += e.latexCode() + "\n";
        }
        result += "\\end{tikzpicture}\r\n" + //
                        "\r\n" + //
                        "\\end{document}";
        return result;
    }

    Vertex[] getVertices() {
        Vertex[] verArr = new Vertex[vertices.size];
        Node<Vertex> current = vertices.head;
        int i = 0;
        while(current != null)
        {
            verArr[i] = current.data;
            i++;
            current = current.next;
        }
        return verArr;
    }

    Edge[] getEdges() {
        Edge[] edgesArr = new Edge[edges.size];
        Node<Edge> current = edges.head;
        int i = 0;
        while(current != null)
        {
            edgesArr[i] = current.data;
            i++;
            current = current.next;
        }
        return edgesArr;
    }

    void stage1Reducing() {
        // Removing of redundant steps
        // Removing dead ends
        Node<Vertex> current = vertices.head;
        Vertex v = null;
        while(current != null)
        {
            if(current.data.symbol == ' ')
            {
                //check if has two edges
                if(current.data.edges.size == 2)
                {
                    //add the new edge
                    Edge e = current.data.addEdges();

                    //Add the new edge
                    this.edges.append(e);

                    //Remove edges with vertex
                    v = current.data;
                    removeEdgesWithVertex(v);

                    Node<Vertex> next = current.next;

                    //Remove the vertex
                    removeVertex(v);

                    current = next;
                    continue;
                }
            }
            current = current.next;
        }
    }

    void stage2Reducing() {
        // Removing dead ends
        Node<Edge> current = edges.head;
        while(current != null)
        {
            if(current.data.containsDeadEnd())
            {
                //check if dead end has only one edge
                if(current.data.findDeadEnd().edges.size == 1)
                {
                    //Remove the vertex
                    vertices.remove(current.data.findDeadEnd());

                    Node<Edge> next = current.next;

                    //Remove the edge
                    edges.remove(current.data);

                    current = next;
                    continue;
                }
            }
            current = current.next;
        }
    }

    void stage3Reducing() {
        // Doubling the weight to traps
        Node<Edge> current = edges.head;
        //Go through Edges list and if contains trap double the wight
        while(current != null)
        {
            if(current.data.containsTrap())
            {
                current.data.doubleWeight();
            }
            current = current.next;
        }
    }

    public Vertex[] getAllDoors() {
        return null;
    }

    public Vertex[] getAllGoals() {
        return null;
    }

    public Vertex[] getAllKeys() {
        return null;
    }

    boolean isReachAble(Vertex start, Vertex goal) {
        return false;
    }

    Vertex[] isReachAblePath(Vertex start, Vertex goal) {
        return null;
    }

    double shortestPathDistanceNoDoor(Vertex start, Vertex goal) {
        return 1;
    }

    Vertex[] shortestPathPathNoDoor(Vertex start, Vertex goal) {
        return null;
    }

    double shortestPathDistanceDoor(Vertex start, Vertex goal, boolean goUp) {
        return -1;
    }

    Vertex[] shortestPathPathDoor(Vertex start, Vertex goal, boolean goUp) {
        return null;
    }

    Vertex getVertex(Vertex v) {
        Node<Vertex> node = vertices.find(v);
        if(node == null)
        {
            return null;
        }
        return node.data;
    }

    boolean isReachAbleThroughDoor(Vertex start, Vertex goal) {
        return false;
    }

    Vertex[] isReachAbleThroughDoorPath(Vertex start, Vertex goal) {
        return null;
    }

    double shortestPathThroughDoor(Vertex start, Vertex goal) {
        return -1;
    }

    Vertex[] shortestPathThroughDoorPath(Vertex start, Vertex goal) {
        return null;
    }

    boolean canReachGoal(char targetGoal){
        return false;
    }

    Vertex[] canReachGoalPath(char targetGoal){
        return null;
    }

    double getRatio(Vertex goal){
        return -1;
    }

    Vertex getRecommendedGoal(){
        return null;
    }

    double getRecommendedRatio(){
        return -1;
    }

    Vertex[] getRecommendedPath(){
        return null;
    }

    private void removeEdgesWithVertex(Vertex v)
    {
        Node<Edge> current = edges.head;

        while(current != null)
        {
            if(current.data.containsVertex(v))
            {
                //remove from list
                Node<Edge> e = current.next;

                edges.remove(current.data);

                current = e;
                continue;
            }
            current = current.next;
        }
    }

    private void removeVertex(Vertex v)
    {
        vertices.remove(v);
    }

}
