import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    vertexes;
    edges;
    Vertex start;

    Maze() {
        
    }

    Maze(String fileName) {
        
    }

    static Maze createMaze(String mazeString) {
        
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
       
    }

    Edge[] getEdges() {
        
    }

    void stage1Reducing() {// Removing of redundant steps
        
    }

    void stage2Reducing() {// Removing dead ends
        
    }

    void stage3Reducing() {// Doubling the weight to traps
        
    }

    public Vertex[] getAllDoors() {
        
    }

    public Vertex[] getAllGoals() {
        
    }

    public Vertex[] getAllKeys() {
        
    }

    boolean isReachAble(Vertex start, Vertex goal) {
        
    }

    Vertex[] isReachAblePath(Vertex start, Vertex goal) {
        
    }

    double shortestPathDistanceNoDoor(Vertex start, Vertex goal) {
        
    }

    Vertex[] shortestPathPathNoDoor(Vertex start, Vertex goal) {
        
    }

    double shortestPathDistanceDoor(Vertex start, Vertex goal, boolean goUp) {
        
    }

    Vertex[] shortestPathPathDoor(Vertex start, Vertex goal, boolean goUp) {
       
    }

    Vertex getVertex(Vertex v) {
        
    }

    boolean isReachAbleThroughDoor(Vertex start, Vertex goal) {
        
    }

    Vertex[] isReachAbleThroughDoorPath(Vertex start, Vertex goal) {
        
    }

    double shortestPathThroughDoor(Vertex start, Vertex goal) {
        
    }

    Vertex[] shortestPathThroughDoorPath(Vertex start, Vertex goal) {
        
    }

    boolean canReachGoal(char targetGoal){
        
    }

    Vertex[] canReachGoalPath(char targetGoal){
        
    }

    double getRatio(Vertex goal){
        
    }

    Vertex getRecommendedGoal(){
        
    }

    double getRecommendedRatio(){
        
    }

    Vertex[] getRecommendedPath(){
        
    }

}
