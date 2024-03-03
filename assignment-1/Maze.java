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
        if(path.head.x != startX || path.head.y != startY)
        {
            return false;
        }

        if(path.getTail().x != goalX || path.getTail().y != goalY)
        {
            return false;
        }

        //Check nodes in path
        if(!path.validateMovements(map.length,this.map))
        {
            return false;
        }

        return true;
    }

    public String solve(int x, int y, int goalX, int goalY) {
        //If the length is short
        if(map.length < 2)
        {
            return "No valid solution exists";
        }

        //check coordinates are in range
        if(y>=map.length || y<0 || goalY>= map.length || goalY<0 || x < 0 || goalX < 0)
        {
            return "No valid solution exists";
        }

        //Bounds Check
        if(x >= map[y].length() || goalX >= map[goalY].length())
        {
            return "No valid solution exists";
        }

        //check start and end for walls
        if(map[y].charAt(x) == 'X' || map[y].charAt(x) == 'x')
        {
            return "No valid solution exists";
        }
        if(map[goalY].charAt(goalX) == 'X' || map[goalY].charAt(goalX) == 'x')
        {
            return "No valid solution exists";
        }
        //Create path list starting at x and y
        LinkedList path = new LinkedList();

        //Set start and end
        String[] maze = copyMaze();

        String orignal = maze[y];
        String modified = orignal.substring(0,x) + "S" + orignal.substring(x+1);
        maze[y] = modified;

         orignal = maze[goalY];
         modified = orignal.substring(0,goalX) + "E" + orignal.substring(goalX+1);
        maze[goalY] = modified;



        //traverse map
        String[] copyMap = copyMaze();
        boolean traverse = traverse(x,y,copyMap,goalX,goalY,path);

        if(traverse)
        {
            LinkedList newPath = path.reversed();
            //mark the path
            replacePath(newPath.head.next,newPath.getTail(),maze);

            //String build
            String output = "Solution\n";
            output += printMaze("",0,maze) +"\n";
            output += newPath.toString();

            return output;
        }
        else{
            return "No valid solution exists";
        }
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

    private String printMaze(String myMap,int currentRow,String[] copyMap){
        //Base Case
        if( currentRow == copyMap.length -1)
        {
            myMap += copyMap[currentRow];
            return myMap;
        }
        if(currentRow >= copyMap.length)
        {
            return myMap;
        }
        myMap += copyMap[currentRow] + "\n";
        currentRow += 1;
        return printMaze(myMap,currentRow,copyMap);
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

    private String replacePath(CoordinateNode current, CoordinateNode tail, String[] copyMap)
    {
        if(current == null || current == tail)
        {
            return printMaze("",0,copyMap);
        }
        String orignal =  copyMap[current.y];
        String modified = orignal.substring(0,current.x) + "@" + orignal.substring(current.x+1);
        copyMap[current.y] = modified;
        return replacePath(current.next,tail,copyMap);
    }

    //checks if it is a wall

    private boolean isWall(int x,int y)
    {
        if(map[y].charAt(x)=='X' || map[y].charAt(x)=='x')
        {
            return true;
        }
        return false;
    }

    //checks if position is outOfBounds

    private boolean outOfBounds(int x,int y)
    {
        if(y>=map.length || y < 0)
        {
            return true;
        }

        if(x < 0 || x >= map[y].length())
        {
            return true;
        }

        return false;
    }

    //sets position to tried value
    private void triedPosition(int x,int y,String[] copyMap)
    {
        String orignal =  copyMap[y];
        String modified = orignal.substring(0,x) + "v" + orignal.substring(x+1);
        copyMap[y] = modified;
    }

    //marks the path
    private void markPath(int x,int y,String[] copyMap)
    {
        String orignal =  copyMap[y];
        String modified = orignal.substring(0,x) + "@" + orignal.substring(x+1);
        copyMap[y] = modified;
    }


    //check if position is valid
    private boolean validPosition(int x, int y,String[] copyMap)
    {
        //check if out of bounds
        if(outOfBounds(x,y))
        {
            return false;
        }
        //check if a wall
        if(isWall(x,y))
        {
            return false;
        }
        //check if visited
        if(copyMap[y].charAt(x)=='v')
        {
            return false;
        }
        return true;
    }
    private void goLeft(int x,int y,LinkedList path)
    {
        if(outOfBounds(x,y) || isWall(x, y))
        {
            return;
        }
        path.append(x, y);
        if(outOfBounds(x-1,y) || isWall(x-1,y))
        {
            return;
        }
        goLeft(x-1,y,path);
    }

    private boolean traverse(int x,int y,String[] copyMap,int goalX, int goalY,LinkedList path){
        boolean done = false;

        //check if valid
        if(validPosition(x,y,copyMap))
        {
            //mark position as tried
            triedPosition(x,y,copyMap);

            //if end of the maze
            if(x == goalX && y == goalY)
            {
                done = true;
            }
            else{
                if(!done)
                {
                    done = traverse(x-1,y,copyMap,goalX,goalY,path); //go left
                }
                if(!done)
                {
                    done = traverse(x,y-1,copyMap,goalX,goalY,path);//go up
                }
                if(!done)
                {
                    done = traverse(x,y+1,copyMap,goalX,goalY,path);//go down
                }
                if(!done)
                {
                    done = traverse(x+1,y,copyMap,goalX,goalY,path);//go right
                }
            }

            if(done)
            {
                //mark the path
                path.append(x,y);
            }
        }
        return done;
    }

    private String[] copyMaze(){
        String [] copy = new String[map.length];
        copy(0,copy);
        return copy;
    }

    private void copy(int currentRow,String[] copyMap)
    {
        if(currentRow >= copyMap.length)
        {
            return;
        }
        copyMap[currentRow] = map[currentRow];
        copy(currentRow+1,copyMap);
    }

}