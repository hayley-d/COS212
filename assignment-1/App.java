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

        endAll();
    }
}
