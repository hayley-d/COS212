import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    LinkedList<Vertex> vertices;
    //edges;
    Vertex start;

    Maze() {
        
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
        return null;
    }

    Edge[] getEdges() {
        return null;
    }

    void stage1Reducing() {// Removing of redundant steps
        
    }

    void stage2Reducing() {// Removing dead ends
        
    }

    void stage3Reducing() {// Doubling the weight to traps
        
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
        return null;
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

}
