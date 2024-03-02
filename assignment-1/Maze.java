import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    private String[] map;

    public Maze(String filename) {
        try{
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            //read in the number of rows
            int rows = scanner.nextInt();
            scanner.nextLine();//Move to the next line

            this.map = new String[rows];
            readNextLine(scanner,0);
            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            this.map = new String[0];
        }
    }

    public Maze(Maze other) {
        if(other == null || other.map.length == 0)
        {
            this.map = new String[0];
            return;
        }

        int rows = other.map.length;

        this.map = new String[rows];

        copyMap(other.map,0);
    }

    @Override
    public String toString() {
       if(map.length == 0)
       {
           return "Empty Map";
       }
       return printMaze("",0);
    }

    public boolean validSolution(int startX, int startY, int goalX, int goalY, LinkedList path) {
        //check if empty
        if(map == null || map.length == 0|| path == null || path.length() ==0)
        {
            return false;
        }
        //check coordinate values
        if(startX < 0 || startY < 0 || goalX < 0 || goalY < 0)
        {
            return false;
        }

        //Validate Head and tail
        if(path.head.x != startX && path.head.y != startY || path.getTail().x != goalX && path.getTail().y != goalY)
        {
            return false;
        }

        //Check nodes in path
        if(!path.validateMovements(map.length))
        {
            return false;
        }

    }

    public String solve(int x, int y, int goalX, int goalY) {
        return "";
    }

    public LinkedList validStarts(int x, int y) {
        return null;
    }

    //HELPER FUNCTIONS

    private void readNextLine(Scanner scanner,int currentRow)
    {
        if(currentRow < map.length)
        {
            String line = scanner.nextLine();
            map[currentRow] = line;
            currentRow += 1;
            readNextLine(scanner,currentRow);
        }
    }

    public int getMapSize(){
        return map.length;
    }

    private String printMaze(String myMap,int currentRow){
        //Base Case
        if( currentRow == map.length -1)
        {
            myMap += map[currentRow];
            return myMap;
        }
        if(currentRow >= map.length)
        {
            return myMap;
        }
        myMap += map[currentRow] + "\n";
        currentRow += 1;
        return printMaze(myMap,currentRow);
    }

    private void copyMap(String[] other, int currentRow)
    {
        if(currentRow < other.length)
        {
            this.map[currentRow] = other[currentRow];
            currentRow += 1;
            copyMap(other,currentRow);
        }
    }

}