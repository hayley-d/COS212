import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    private String[] map;

    public Maze(String filename) {

    }

    public Maze(Maze other) {
    }

    @Override
    public String toString() {
        return "";
    }

    public boolean validSolution(int startX, int startY, int goalX, int goalY, LinkedList path) {
        return false;
    }

    public String solve(int x, int y, int goalX, int goalY) {
        return "";
    }

    public LinkedList validStarts(int x, int y) {
        return null;
    }

}