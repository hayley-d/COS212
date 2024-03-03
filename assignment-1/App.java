public class App {
    //colours
    public static String ANSI_RED = "\u001b[31;1m";

    public static String ANSI_GREEN = "\u001b[32;1m";

    public static String ANSI_RESET = "\u001b[0m";

    public static int SUITES_RUN = 0;
    public static int SUITES_PASSED = 0;
    public static int TESTS_RUN = 0;
    public static int TESTS_PASSES = 0;

    public static void startSuite(String name)
    {
        SUITES_RUN++;
        System.out.println("======================\n" + "Starting: " + name + "\n======================");
    }

    public static void endSuite(String name)
    {
        if(TESTS_RUN == TESTS_PASSES)
        {
            SUITES_PASSED++;
            System.out.println(ANSI_GREEN + "======================\n" + "Tests Passed " + "\n======================\n" + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "======================\n" + "Tests Failed: " + (TESTS_RUN-TESTS_PASSES) + "\n======================\n"+ ANSI_RESET);
        }
        TESTS_RUN = 0;
        TESTS_PASSES = 0;
    }

    public static void assertEquals(int actual, int expected)
    {
        TESTS_RUN++;
        if(actual == expected){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expected+ " but got "+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(boolean actual, boolean expected)
    {
        TESTS_RUN++;
        if(actual == expected){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expected+ " but got "+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(double actual, double expected)
    {
        TESTS_RUN++;
        if(actual == expected){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expected+ " but got "+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(String actual, String expected)
    {
        TESTS_RUN++;
        if(actual.equals(expected)){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expected+ " but got "+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(CoordinateNode actual, CoordinateNode expected)
    {
        TESTS_RUN++;
        if(actual.x == expected.x && actual.y == expected.y){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expected.toString()+ " but got "+ actual.toString() +  ANSI_RESET);
        }
    }

    public static void assertEquals(CoordinateNode actual, CoordinateNode expected,boolean expectedNull)
    {
        TESTS_RUN++;
        if(actual == null && expected == null){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expected.toString()+ " but got "+ actual.toString() +  ANSI_RESET);
        }
    }


    public static void endAll(){
        if(SUITES_PASSED == SUITES_RUN)
        {
            System.out.println(ANSI_GREEN + "======================\n" + "Congratulations All Suites Passed: " + SUITES_PASSED +"/" + SUITES_RUN + "\n======================\n" + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "======================\n" + "Some Suites Failed: " + SUITES_PASSED +"/" + SUITES_RUN + "\n======================\n" + ANSI_RESET);
        }
    }


    public static void main(String[] args){
        startSuite("Constructor Test");

        LinkedList myList = new LinkedList(1,2);
        assertEquals(myList.head,new CoordinateNode(1,2));

        myList = new LinkedList(5,6);
        assertEquals(myList.head,new CoordinateNode(5,6));

        myList = new LinkedList(21,56);
        assertEquals(myList.head,new CoordinateNode(21,56));

        endSuite("Constructor Test");

        startSuite("Append Test");
        myList = new LinkedList();

        //Empty List append
        myList.append(1,2);
        assertEquals(myList.head,new CoordinateNode(1,2));

        //Test Append with one node in the list
        myList.append(3,4);
        assertEquals(myList.head.next,new CoordinateNode(3,4));

        //Test Append with 2 nodes in the list
        myList.append(5,6);
        assertEquals(myList.head.next.next,new CoordinateNode(5,6));

        myList.append(7,8);
        assertEquals(myList.head.next.next.next,new CoordinateNode(7,8));

        myList.append(9,10);
        assertEquals(myList.head.next.next.next.next,new CoordinateNode(9,10));

        //Duplicate add test
        myList.append(9,10);
        assertEquals(myList.head.next.next.next.next.next,new CoordinateNode(9,10));

        endSuite("Append Test");

        startSuite("Append List Test");
        LinkedList otherList = new LinkedList();
        myList = new LinkedList();

        //Empty List append
        myList.appendList(otherList);
        assertEquals(myList.head,null,true);

        //Empty other list
        myList.append(1,2);
        myList.appendList(otherList);
        assertEquals(myList.head,new CoordinateNode(1,2));
        assertEquals(myList.head.next,null,true);

        //My list is null other list is not
        myList = new LinkedList();
        otherList.append(1,2);
        myList.appendList(otherList);
        assertEquals(myList.head,new CoordinateNode(1,2));

        //Both have 1 value
        myList.append(2,3);
        otherList.appendList(myList);
        assertEquals(otherList.head,new CoordinateNode(1,2));
        assertEquals(otherList.head.next,new CoordinateNode(2,3));

        //One has one value the other has 2 values
        myList = new LinkedList();
        myList.append(1,2);
        myList.appendList(otherList);
        assertEquals(myList.head,new CoordinateNode(1,2));
        assertEquals(myList.head.next,new CoordinateNode(1,2));
        assertEquals(myList.head.next.next,new CoordinateNode(2,3));

        myList = new LinkedList(1,2);
        myList.append(3,4);
        myList.append(5,6);
        myList.append(7,8);
        otherList = new LinkedList(9,10);
        otherList.append(11,12);
        otherList.append(13,14);
        otherList.append(15,16);
        myList.appendList(otherList);
        assertEquals(myList.head,new CoordinateNode(1,2));
        assertEquals(myList.head.next,new CoordinateNode(3,4));
        assertEquals(myList.head.next.next,new CoordinateNode(5,6));
        assertEquals(myList.head.next.next.next,new CoordinateNode(7,8));
        assertEquals(myList.head.next.next.next.next,new CoordinateNode(9,10));
        assertEquals(myList.head.next.next.next.next.next,new CoordinateNode(11,12));
        assertEquals(myList.head.next.next.next.next.next.next,new CoordinateNode(13,14));
        assertEquals(myList.head.next.next.next.next.next.next.next,new CoordinateNode(15,16));
        assertEquals(myList.head.next.next.next.next.next.next.next.next,null,true);
        endSuite("Append List Test");

        startSuite("Contains Test");
        //Empty List test
        myList = new LinkedList();
        assertEquals(myList.contains(1,2),false);
        assertEquals(myList.contains(3,4),false);
        assertEquals(myList.contains(5,6),false);

        //Single Element not in list
        myList = new LinkedList(5,6);
        assertEquals(myList.contains(1,2),false);
        assertEquals(myList.contains(3,4),false);
        assertEquals(myList.contains(5,6),true);

        //Multiple Element false
        myList = new LinkedList(1,2);
        myList.append(3,4);
        myList.append(5,6);
        myList.append(7,8);
        myList.append(9,10);
        assertEquals(myList.contains(1,2),true);
        assertEquals(myList.contains(3,4),true);
        assertEquals(myList.contains(5,6),true);
        assertEquals(myList.contains(7,8),true);
        assertEquals(myList.contains(9,10),true);
        assertEquals(myList.contains(11,12),false);
        assertEquals(myList.contains(0,0),false);
        assertEquals(myList.contains(1,1),false);
        assertEquals(myList.contains(2,2),false);
        assertEquals(myList.contains(3,3),false);

        endSuite("Contains Test");

        startSuite("ToString Test");
        //Empty List Test
        myList = new LinkedList();
        assertEquals(myList.toString(),"Empty List");

        //Single Element
        myList = new LinkedList(0,1);
        assertEquals(myList.toString(),"[0,1]");

        //Many Elements
        myList.append(2,3);
        assertEquals(myList.toString(),"[0,1] -> [2,3]");
        myList.append(4,5);
        assertEquals(myList.toString(),"[0,1] -> [2,3] -> [4,5]");
        myList.append(6,7);
        assertEquals(myList.toString(),"[0,1] -> [2,3] -> [4,5] -> [6,7]");
        myList.append(8,9);
        assertEquals(myList.toString(),"[0,1] -> [2,3] -> [4,5] -> [6,7] -> [8,9]");

        endSuite("ToString Test");

        startSuite("Length Test");
        //Empty List Test
        myList = new LinkedList();
        assertEquals(myList.length(),0);

        //Single Element
        myList = new LinkedList(0,1);
        assertEquals(myList.length(),1);

        //Many Elements
        myList.append(2,3);
        assertEquals(myList.length(),2);
        myList.append(4,5);
        assertEquals(myList.length(),3);
        myList.append(6,7);
        assertEquals(myList.length(),4);
        myList.append(8,9);
        assertEquals(myList.length(),5);

        endSuite("Length Test");

        startSuite("Reverse Test");
        //Empty List Test
        myList = new LinkedList();
        assertEquals(myList.reversed().toString(),"Empty List");
        assertEquals(myList.reversed().length(),0);

        //Single Element
        myList = new LinkedList(0,1);
        assertEquals(myList.reversed().head,new CoordinateNode(0,1));
        assertEquals(myList.reversed().toString(),"[0,1]");
        assertEquals(myList.reversed().length(),1);

        //Many Elements
        myList.append(2,3);
        assertEquals(myList.reversed().head,new CoordinateNode(2,3));
        assertEquals(myList.reversed().toString(),"[2,3] -> [0,1]");
        assertEquals(myList.reversed().length(),2);

        myList.append(4,5);
        assertEquals(myList.reversed().head,new CoordinateNode(4,5));
        assertEquals(myList.reversed().toString(),"[4,5] -> [2,3] -> [0,1]");
        assertEquals(myList.reversed().length(),3);

        myList.append(6,7);
        assertEquals(myList.reversed().head,new CoordinateNode(6,7));
        assertEquals(myList.reversed().toString(),"[6,7] -> [4,5] -> [2,3] -> [0,1]");
        assertEquals(myList.reversed().length(),4);

        myList.append(8,9);
        assertEquals(myList.reversed().head,new CoordinateNode(8,9));
        assertEquals(myList.reversed().toString(),"[8,9] -> [6,7] -> [4,5] -> [2,3] -> [0,1]");
        assertEquals(myList.reversed().length(),5);

        endSuite("Reverse Test");

        startSuite("Maze Constructor Test");
        Maze myMaze = new Maze("./input2.txt");
        //File not found test
        assertEquals(myMaze.getMapSize(),0);

        //File Found test
        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\input.txt");
        assertEquals(myMaze.getMapSize(),5);

        //Too many lines
        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\toMany.txt");
        assertEquals(myMaze.getMapSize(),2);

        //Different characters
        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\moreChars.txt");
        assertEquals(myMaze.getMapSize(),5);

        //long file
        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\longInput.txt");
        assertEquals(myMaze.getMapSize(),15);

        endSuite("Maze Constructor Test");

        startSuite("Maze Copy Test");

        // Empty copy test
        Maze otherMaze = new Maze("./input2.txt");
        myMaze = new Maze(otherMaze);
        assertEquals(myMaze.getMapSize(),0);
        assertEquals(myMaze.toString(),otherMaze.toString());
        assertEquals(myMaze.toString(),"Empty Map");

        //File Found test
        otherMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\input.txt");
        myMaze = new Maze(otherMaze);
        assertEquals(myMaze.getMapSize(),5);
        assertEquals(myMaze.toString(),otherMaze.toString());
        assertEquals(myMaze.toString(),"-----\n" + "-----\n" + "-----\n" + "-----\n" + "-----");

        //Too many lines
        otherMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\toMany.txt");
        myMaze = new Maze(otherMaze);
        assertEquals(myMaze.getMapSize(),2);
        assertEquals(myMaze.toString(),otherMaze.toString());
        assertEquals(myMaze.toString(),"-----\n" + "-----");

        //Different characters
        otherMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\moreChars.txt");
        myMaze = new Maze(otherMaze);
        assertEquals(myMaze.getMapSize(),5);
        assertEquals(myMaze.toString(),otherMaze.toString());
        assertEquals(myMaze.toString(),"--X--\n" + "-----\n" + "--X--\n" + "-----\n" + "---X-");

        //long file
        otherMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\longInput.txt");
        myMaze = new Maze(otherMaze);
        assertEquals(myMaze.getMapSize(),15);
        assertEquals(myMaze.toString(),otherMaze.toString());
        assertEquals(myMaze.toString(),"--X--\n" + "-----\n" + "--X--\n" + "-----\n" + "---X-\n" + "--X--\n" + "-----\n" + "--X--\n" + "-----\n" + "---X-\n" + "--X--\n" + "-----\n" + "--X--\n" + "-----\n" + "---X-");

        endSuite("Maze Copy Test");

        startSuite("Validation Test");
        //Empty Maze Test
        myMaze = new Maze("./input2.txt");
        myList = new LinkedList();
        assertEquals(myMaze.validSolution(0,0,0,0,null),false);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);

        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\input.txt");
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);
        myList.append(0,0);
        myList.append(1,2);
        assertEquals(myMaze.validSolution(-1,0,0,0,myList),false);
        assertEquals(myMaze.validSolution(0,-1,0,0,myList),false);
        assertEquals(myMaze.validSolution(0,0,-1,0,myList),false);
        assertEquals(myMaze.validSolution(0,0,0,-1,myList),false);
        assertEquals(myMaze.validSolution(-1,-1,0,0,myList),false);
        assertEquals(myMaze.validSolution(-1,0,-1,0,myList),false);
        assertEquals(myMaze.validSolution(-1,0,0,-1,myList),false);
        assertEquals(myMaze.validSolution(0,-1,-1,0,myList),false);
        assertEquals(myMaze.validSolution(0,-1,0,-1,myList),false);
        assertEquals(myMaze.validSolution(0,0,-1,-1,myList),false);
        assertEquals(myMaze.validSolution(-1,-1,-1,0,myList),false);
        assertEquals(myMaze.validSolution(0,-1,-1,-1,myList),false);
        assertEquals(myMaze.validSolution(-1,-1,-1,-1,myList),false);

        //invalid head 0,0
        assertEquals(myMaze.validSolution(1,0,0,0,myList),false);
        assertEquals(myMaze.validSolution(0,1,0,0,myList),false);
        assertEquals(myMaze.validSolution(1,1,0,0,myList),false);

        //Invalid tail
        assertEquals(myMaze.validSolution(0,0,0,2,myList),false);
        assertEquals(myMaze.validSolution(0,0,1,0,myList),false);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);

        //Outlier nodes
        myList.append(-1,2);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);
        myList.append(1,-1);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);
        myList.append(-1,-2);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);
        myList.append(1,7);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);

        //check diagonals
        myList = new LinkedList();
        myList.append(0,0);
        myList.append(1,1);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);
        myList = new LinkedList();
        myList.append(0,0);
        myList.append(1,0);
        myList.append(1,1);
        myList.append(0,0);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);
        myList = new LinkedList();
        myList.append(0,0);
        myList.append(1,0);
        myList.append(1,1);
        myList.append(2,2);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);
        myList = new LinkedList();
        myList.append(1,1);
        myList.append(1,2);
        myList.append(2,3);
        myList.append(1,3);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);
        myList = new LinkedList();
        myList.append(1,1);
        myList.append(1,2);
        myList.append(0,1);
        myList.append(0,2);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);

        //Step Test
        myList = new LinkedList();
        myList.append(0,0);
        myList.append(1,0);
        myList.append(2,0);
        myList.append(6,0);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);
        myList = new LinkedList();
        myList.append(0,0);
        myList.append(1,0);
        myList.append(7,0);
        myList.append(6,0);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);
        myList = new LinkedList();
        myList.append(0,0);
        myList.append(0,1);
        myList.append(0,2);
        myList.append(6,0);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);
        myList = new LinkedList();
        myList.append(0,0);
        myList.append(0,1);
        myList.append(0,7);
        myList.append(0,8);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);
        myList = new LinkedList();
        myList.append(0,0);
        myList.append(0,1);
        myList.append(0,2);
        myList.append(0,3);
        myList.append(10,9);
        myList.append(10,8);
        myList.append(10,7);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);

        myList = new LinkedList();
        myList.append(0,0);
        myList.append(0,0);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);

        myList = new LinkedList();
        myList.append(0,0);
        myList.append(0,1);
        myList.append(0,2);
        myList.append(0,1);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);
        myList = new LinkedList();
        myList.append(0,0);
        myList.append(1,0);
        myList.append(1,1);
        myList.append(2,1);
        myList.append(2,0);
        myList.append(1,0);
        myList.append(0,0);
        assertEquals(myMaze.validSolution(0,0,0,0,myList),false);

        //other node checks

        //list with one node
        myList = new LinkedList();
        myList.append(0,0);
        assertEquals(myMaze.validSolution(0,0,1,2,myList),false);

        //Testing popper planned out maze
        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\input.txt");
        myList = new LinkedList();
        myList.append(2,0);
        myList.append(3,0);
        myList.append(3,1);
        myList.append(2,1);
        myList.append(1,1);
        myList.append(1,2);
        myList.append(1,3);
        myList.append(2,3);
        myList.append(3,3);
        assertEquals(myMaze.validSolution(2,0,3,3,myList),true);

        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\input.txt");
        myList = new LinkedList();
        myList.append(2,0);
        myList.append(3,0);
        myList.append(3,1);
        myList.append(2,1);
        myList.append(2,2);
        myList.append(3,2);
        myList.append(3,1);
        myList.append(2,1);
        myList.append(1,1);
        myList.append(1,2);
        myList.append(1,3);
        myList.append(2,3);
        myList.append(3,3);
        assertEquals(myMaze.validSolution(2,0,3,3,myList),false);

        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\input.txt");
        myList = new LinkedList();
        myList.append(0,2);
        myList.append(0,3);
        myList.append(1,3);
        myList.append(1,2);
        myList.append(1,1);
        myList.append(0,1);
        myList.append(-1,1);
        myList.append(-1,2);
        myList.append(0,2);
        myList.append(1,2);

        assertEquals(myMaze.validSolution(2,0,1,2,myList),false);

        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\input.txt");
        myList = new LinkedList();
        myList.append(2,0);
        myList.append(3,0);
        myList.append(3,1);
        myList.append(2,1);
        myList.append(1,1);
        myList.append(3,2);
        myList.append(1,3);
        myList.append(2,3);
        myList.append(3,3);
        assertEquals(myMaze.validSolution(2,0,3,3,myList),false);

        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\input.txt");
        myList = new LinkedList();
        myList.append(2,0);
        myList.append(3,0);
        myList.append(3,1);
        myList.append(2,1);
        myList.append(1,1);
        myList.append(1,2);
        myList.append(1,3);
        myList.append(2,3);
        myList.append(3,3);
        assertEquals(myMaze.validSolution(3,0,3,3,myList),false);

        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\input.txt");
        myList = new LinkedList();
        myList.append(2,0);
        myList.append(3,0);
        myList.append(3,1);
        myList.append(2,1);
        myList.append(1,1);
        myList.append(1,2);
        myList.append(1,3);
        myList.append(2,3);
        myList.append(3,3);
        assertEquals(myMaze.validSolution(2,0,3,2,myList),false);

        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\input.txt");
        myList = new LinkedList();
        myList.append(2,0);
        myList.append(3,0);
        myList.append(3,1);
        myList.append(2,1);
        myList.append(1,1);
        myList.append(1,2);
        myList.append(0,3);
        myList.append(2,3);
        myList.append(3,3);
        assertEquals(myMaze.validSolution(0,2,3,3,myList),false);

        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\moreChars.txt");
        myList = new LinkedList();
        myList.append(2,0);
        myList.append(3,0);
        myList.append(3,1);
        myList.append(2,1);
        myList.append(1,1);
        myList.append(1,2);
        myList.append(1,3);
        myList.append(2,3);
        myList.append(3,3);
        assertEquals(myMaze.validSolution(2,0,3,3,myList),false);

        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\moreChars.txt");
        myList = new LinkedList();
        myList.append(3,0);
        myList.append(3,1);
        myList.append(2,1);
        myList.append(1,1);
        myList.append(1,2);
        myList.append(1,3);
        myList.append(2,3);
        myList.append(3,3);
        assertEquals(myMaze.validSolution(3,0,3,3,myList),true);

        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\moreChars.txt");
        myList = new LinkedList();
        myList.append(3,0);
        myList.append(3,1);
        myList.append(2,1);
        myList.append(1,1);
        myList.append(1,2);
        myList.append(2,2);
        myList.append(2,3);
        myList.append(3,3);
        assertEquals(myMaze.validSolution(3,0,3,3,myList),false);

        endSuite("Validation Test");

        startSuite("Solve Test");
        //Check if map is too short
        //If maze is empty
        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\moreChars2.txt");
        assertEquals(myMaze.solve(0,1,3,3),"No valid solution exists");
        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\tooShort.txt");
        assertEquals(myMaze.solve(0,1,3,3),"No valid solution exists");

        //Coordinates not in range
        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\moreChars2.txt");
        assertEquals(myMaze.solve(-1,1,3,3),"No valid solution exists");
        assertEquals(myMaze.solve(0,-1,3,3),"No valid solution exists");
        assertEquals(myMaze.solve(0,1,-3,3),"No valid solution exists");
        assertEquals(myMaze.solve(0,1,3,-3),"No valid solution exists");
        assertEquals(myMaze.solve(10,1,3,3),"No valid solution exists");
        assertEquals(myMaze.solve(0,10,3,3),"No valid solution exists");
        assertEquals(myMaze.solve(0,1,10,3),"No valid solution exists");
        assertEquals(myMaze.solve(0,1,3,10),"No valid solution exists");

        //Start on a wall
        assertEquals(myMaze.solve(2,0,3,3),"No valid solution exists");

        //End on a wall
        assertEquals(myMaze.solve(1,0,2,2),"No valid solution exists");

        //End point on edge of maze
        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\moreChars.txt");
        assertEquals(myMaze.solve(0,1,4,1),"Solution\n" + "@@X@@\n" + "S@-@E\n" + "@@X@-\n" + "@@@@-\n" + "@@-X-\n" + "[0,1] -> [0,0] -> [1,0] -> [1,1] -> [1,2] -> [0,2] -> [0,3] -> [0,4] -> [1,4] -> [1,3] -> [2,3] -> [3,3] -> [3,2] -> [3,1] -> [3,0] -> [4,0] -> [4,1]");
        assertEquals(myMaze.solve(0,1,0,1),"Solution\n" + "--X--\n" + "E----\n" + "--X--\n" + "-----\n" + "---X-\n" + "[0,1]");
        assertEquals(myMaze.solve(0,1,0,0),"Solution\n" + "E-X--\n" + "S----\n" + "--X--\n" + "-----\n" +"---X-\n" + "[0,1] -> [0,0]");

        //Start on edge
        assertEquals(myMaze.solve(0,0,3,3),"Solution\n" + "S-X@@\n" + "@@@@@\n" + "@@X@@\n" + "@@-E-\n" + "@@-X-\n" + "[0,0] -> [0,1] -> [0,2] -> [0,3] -> [0,4] -> [1,4] -> [1,3] -> [1,2] -> [1,1] -> [2,1] -> [3,1] -> [3,0] -> [4,0] -> [4,1] -> [4,2] -> [3,2] -> [3,3]");
        assertEquals(myMaze.solve(1,4,3,3),"Solution\n" + "@@X--\n" + "@@---\n" + "@@X--\n" + "@@@E-\n" + "@S-X-\n" + "[1,4] -> [0,4] -> [0,3] -> [0,2] -> [0,1] -> [0,0] -> [1,0] -> [1,1] -> [1,2] -> [1,3] -> [2,3] -> [3,3]");
        assertEquals(myMaze.solve(0,2,0,0),"Solution\n" + "E-X--\n" + "@----\n" + "S-X--\n" + "-----\n" + "---X-\n" + "[0,2] -> [0,1] -> [0,0]");

        //no solution
        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\onlyWalls.txt");
        assertEquals(myMaze.solve(1,0,2,2),"No valid solution exists");

        //Big maze
        myMaze = new Maze("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Assignments\\Assignment 1\\Assignment1\\src\\bigMaze");
        assertEquals(myMaze.solve(6,1,6,7),"Solution\n" + "---@@@@@---------\n" + "XXX@@@S@-------\n" + "-----X@@--XXX----\n" + "XXX@@@@---------\n" + "---@@@@-------\n" + "XXXXXX@------------\n" + "-XX@@@@-XXX----\n" + "XX-@@@E--------\n" + "XXX@@-XXX---\n" + "---@@-\n" + "[6,1] -> [5,1] -> [4,1] -> [3,1] -> [3,0] -> [4,0] -> [5,0] -> [6,0] -> [7,0] -> [7,1] -> [7,2] -> [6,2] -> [6,3] -> [5,3] -> [4,3] -> [3,3] -> [3,4] -> [4,4] -> [5,4] -> [6,4] -> [6,5] -> [6,6] -> [5,6] -> [4,6] -> [3,6] -> [3,7] -> [3,8] -> [3,9] -> [4,9] -> [4,8] -> [4,7] -> [5,7] -> [6,7]");




        endSuite("Solve Test");

        endAll();
    }
}
