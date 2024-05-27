import java.io.FileWriter;
import java.io.IOException;


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
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected \n" + expected+ "\n but got \n"+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(Integer actual)
    {
        TESTS_RUN++;
        if(actual == (null)){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected null" + "\n but got \n"+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(Node actual)
    {
        TESTS_RUN++;
        if(actual == (null)){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected null" + "\n but got \n"+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(Vertex actual)
    {
        TESTS_RUN++;
        if(actual == (null)){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected null" + "\n but got \n"+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(Edge actual)
    {
        TESTS_RUN++;
        if(actual == (null)){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected null" + "\n but got \n"+ actual +  ANSI_RESET);
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
    public static void main(String[] args) throws Exception {
        startSuite("Maze Test");
        Maze maze = new Maze();
        assertEquals(maze.start);
        assertEquals(maze.edges.size,0);
        assertEquals(maze.vertices.size,0);
        assertEquals(maze.getEdges().length,0);
        assertEquals(maze.getVertices().length,0);
        Vertex v = new Vertex(0,0,' ');
        assertEquals(maze.getVertices().length,0);
        assertEquals(maze.getVertex(v));

        Maze m = new Maze("studentMaze.txt.txt");

        assertEquals(m.vertices.printForward(),"(4:1)[T]\t->\t(5:1)[ ]\t->\t(6:1)[ ]\t->\t(7:1)[ ]\t->\t(8:1)[ ]\t->\t(1:2)[ ]\t->\t(2:2)[ ]\t->\t(3:2)[ ]\t->\t(4:2)[ ]\t->\t(6:2)[ ]\t->\t(7:2)[0]\t->\t(8:2)[ ]\t->\t(6:3)[ ]\t->\t(7:3)[ ]\t->\t(8:3)[ ]\t->\t(6:4)[D]\t->\t(8:4)[ ]\t->\t(1:5)[ ]\t->\t(2:5)[T]\t->\t(3:5)[ ]\t->\t(5:5)[ ]\t->\t(6:5)[ ]\t->\t(8:5)[D]\t->\t(2:6)[1]\t->\t(3:6)[ ]\t->\t(4:6)[ ]\t->\t(5:6)[K]\t->\t(6:6)[ ]\t->\t(7:6)[ ]\t->\t(8:6)[ ]\t->\t(6:7)[ ]\t->\t(8:7)[ ]\t->\t(6:8)[ ]\t->\t(8:8)[ ]\t->\t(8:9)[S]");
        assertEquals(m.edges.printForward(),"(5:1)[ ]<-[1.0]->(4:1)[T]\t->\t(4:2)[ ]<-[1.0]->(4:1)[T]\t->\t(6:1)[ ]<-[1.0]->(5:1)[ ]\t->\t(7:1)[ ]<-[1.0]->(6:1)[ ]\t->\t(6:2)[ ]<-[1.0]->(6:1)[ ]\t->\t(8:1)[ ]<-[1.0]->(7:1)[ ]\t->\t(7:2)[0]<-[1.0]->(7:1)[ ]\t->\t(8:2)[ ]<-[1.0]->(8:1)[ ]\t->\t(2:2)[ ]<-[1.0]->(1:2)[ ]\t->\t(3:2)[ ]<-[1.0]->(2:2)[ ]\t->\t(4:2)[ ]<-[1.0]->(3:2)[ ]\t->\t(7:2)[0]<-[1.0]->(6:2)[ ]\t->\t(6:3)[ ]<-[1.0]->(6:2)[ ]\t->\t(8:2)[ ]<-[1.0]->(7:2)[0]\t->\t(7:3)[ ]<-[1.0]->(7:2)[0]\t->\t(8:3)[ ]<-[1.0]->(8:2)[ ]\t->\t(7:3)[ ]<-[1.0]->(6:3)[ ]\t->\t(6:4)[D]<-[1.0]->(6:3)[ ]\t->\t(8:3)[ ]<-[1.0]->(7:3)[ ]\t->\t(8:4)[ ]<-[1.0]->(8:3)[ ]\t->\t(6:5)[ ]<-[1.0]->(6:4)[D]\t->\t(8:5)[D]<-[1.0]->(8:4)[ ]\t->\t(2:5)[T]<-[1.0]->(1:5)[ ]\t->\t(3:5)[ ]<-[1.0]->(2:5)[T]\t->\t(2:6)[1]<-[1.0]->(2:5)[T]\t->\t(3:6)[ ]<-[1.0]->(3:5)[ ]\t->\t(6:5)[ ]<-[1.0]->(5:5)[ ]\t->\t(5:6)[K]<-[1.0]->(5:5)[ ]\t->\t(6:6)[ ]<-[1.0]->(6:5)[ ]\t->\t(8:6)[ ]<-[1.0]->(8:5)[D]\t->\t(3:6)[ ]<-[1.0]->(2:6)[1]\t->\t(4:6)[ ]<-[1.0]->(3:6)[ ]\t->\t(5:6)[K]<-[1.0]->(4:6)[ ]\t->\t(6:6)[ ]<-[1.0]->(5:6)[K]\t->\t(7:6)[ ]<-[1.0]->(6:6)[ ]\t->\t(6:7)[ ]<-[1.0]->(6:6)[ ]\t->\t(8:6)[ ]<-[1.0]->(7:6)[ ]\t->\t(8:7)[ ]<-[1.0]->(8:6)[ ]\t->\t(6:8)[ ]<-[1.0]->(6:7)[ ]\t->\t(8:8)[ ]<-[1.0]->(8:7)[ ]\t->\t(8:9)[S]<-[1.0]->(8:8)[ ]");

        m.stage1Reducing();
        assertEquals(m.edges.printForward(),"(7:1)[ ]<-[1.0]->(6:1)[ ]\t->\t(6:2)[ ]<-[1.0]->(6:1)[ ]\t->\t(7:2)[0]<-[1.0]->(7:1)[ ]\t->\t(7:2)[0]<-[1.0]->(6:2)[ ]\t->\t(6:3)[ ]<-[1.0]->(6:2)[ ]\t->\t(8:2)[ ]<-[1.0]->(7:2)[0]\t->\t(7:3)[ ]<-[1.0]->(7:2)[0]\t->\t(8:3)[ ]<-[1.0]->(8:2)[ ]\t->\t(7:3)[ ]<-[1.0]->(6:3)[ ]\t->\t(6:4)[D]<-[1.0]->(6:3)[ ]\t->\t(8:3)[ ]<-[1.0]->(7:3)[ ]\t->\t(6:5)[ ]<-[1.0]->(6:4)[D]\t->\t(2:5)[T]<-[1.0]->(1:5)[ ]\t->\t(2:6)[1]<-[1.0]->(2:5)[T]\t->\t(6:6)[ ]<-[1.0]->(6:5)[ ]\t->\t(8:6)[ ]<-[1.0]->(8:5)[D]\t->\t(3:6)[ ]<-[1.0]->(2:6)[1]\t->\t(6:6)[ ]<-[1.0]->(5:6)[K]\t->\t(6:1)[ ]<-[2.0]->(4:1)[T]\t->\t(8:2)[ ]<-[2.0]->(7:1)[ ]\t->\t(4:1)[T]<-[4.0]->(1:2)[ ]\t->\t(8:5)[D]<-[2.0]->(8:3)[ ]\t->\t(3:6)[ ]<-[2.0]->(2:5)[T]\t->\t(6:5)[ ]<-[2.0]->(5:6)[K]\t->\t(5:6)[K]<-[2.0]->(3:6)[ ]\t->\t(8:6)[ ]<-[2.0]->(6:6)[ ]\t->\t(6:8)[ ]<-[2.0]->(6:6)[ ]\t->\t(8:9)[S]<-[3.0]->(8:6)[ ]");
        assertEquals(m.vertices.size,22);


        m.stage2Reducing();
        assertEquals(m.edges.printForward(),"(7:1)[ ]<-[1.0]->(6:1)[ ]\t->\t(6:2)[ ]<-[1.0]->(6:1)[ ]\t->\t(7:2)[0]<-[1.0]->(7:1)[ ]\t->\t(7:2)[0]<-[1.0]->(6:2)[ ]\t->\t(6:3)[ ]<-[1.0]->(6:2)[ ]\t->\t(8:2)[ ]<-[1.0]->(7:2)[0]\t->\t(7:3)[ ]<-[1.0]->(7:2)[0]\t->\t(8:3)[ ]<-[1.0]->(8:2)[ ]\t->\t(7:3)[ ]<-[1.0]->(6:3)[ ]\t->\t(6:4)[D]<-[1.0]->(6:3)[ ]\t->\t(8:3)[ ]<-[1.0]->(7:3)[ ]\t->\t(6:5)[ ]<-[1.0]->(6:4)[D]\t->\t(2:6)[1]<-[1.0]->(2:5)[T]\t->\t(6:6)[ ]<-[1.0]->(6:5)[ ]\t->\t(8:6)[ ]<-[1.0]->(8:5)[D]\t->\t(3:6)[ ]<-[1.0]->(2:6)[1]\t->\t(6:6)[ ]<-[1.0]->(5:6)[K]\t->\t(6:1)[ ]<-[2.0]->(4:1)[T]\t->\t(8:2)[ ]<-[2.0]->(7:1)[ ]\t->\t(8:5)[D]<-[2.0]->(8:3)[ ]\t->\t(3:6)[ ]<-[2.0]->(2:5)[T]\t->\t(6:5)[ ]<-[2.0]->(5:6)[K]\t->\t(5:6)[K]<-[2.0]->(3:6)[ ]\t->\t(8:6)[ ]<-[2.0]->(6:6)[ ]\t->\t(8:9)[S]<-[3.0]->(8:6)[ ]");
        assertEquals(m.vertices.size,19);

        m.stage3Reducing();
        assertEquals(m.edges.printForward(),"(7:1)[ ]<-[1.0]->(6:1)[ ]\t->\t(6:2)[ ]<-[1.0]->(6:1)[ ]\t->\t(7:2)[0]<-[1.0]->(7:1)[ ]\t->\t(7:2)[0]<-[1.0]->(6:2)[ ]\t->\t(6:3)[ ]<-[1.0]->(6:2)[ ]\t->\t(8:2)[ ]<-[1.0]->(7:2)[0]\t->\t(7:3)[ ]<-[1.0]->(7:2)[0]\t->\t(8:3)[ ]<-[1.0]->(8:2)[ ]\t->\t(7:3)[ ]<-[1.0]->(6:3)[ ]\t->\t(6:4)[D]<-[1.0]->(6:3)[ ]\t->\t(8:3)[ ]<-[1.0]->(7:3)[ ]\t->\t(6:5)[ ]<-[1.0]->(6:4)[D]\t->\t(2:6)[1]<-[2.0]->(2:5)[T]\t->\t(6:6)[ ]<-[1.0]->(6:5)[ ]\t->\t(8:6)[ ]<-[1.0]->(8:5)[D]\t->\t(3:6)[ ]<-[1.0]->(2:6)[1]\t->\t(6:6)[ ]<-[1.0]->(5:6)[K]\t->\t(6:1)[ ]<-[4.0]->(4:1)[T]\t->\t(8:2)[ ]<-[2.0]->(7:1)[ ]\t->\t(8:5)[D]<-[2.0]->(8:3)[ ]\t->\t(3:6)[ ]<-[4.0]->(2:5)[T]\t->\t(6:5)[ ]<-[2.0]->(5:6)[K]\t->\t(5:6)[K]<-[2.0]->(3:6)[ ]\t->\t(8:6)[ ]<-[2.0]->(6:6)[ ]\t->\t(8:9)[S]<-[3.0]->(8:6)[ ]");
        assertEquals(m.vertices.size,19);

        Vertex v1 = new Vertex(8,9,'S');
        Vertex v2 = new Vertex(2,6,'1');
        m.getVertex(v1);
        assertEquals(m.isReachAble(m.getVertex(v1),m.getVertex(v2)),true);
        assertEquals(m.isReachAble(m.getVertex(v1),m.getVertex(v1)),true);
        Vertex v3 = new Vertex(7,2,'0');
        assertEquals(m.isReachAble(m.getVertex(v1),m.getVertex(v3)),false);
        assertEquals(m.isReachAblePath(m.getVertex(v1),m.getVertex(v3)).length,0);
        assertEquals(m.isReachAblePath(m.getVertex(v1),m.getVertex(v2)).length,6);



        assertEquals(m.shortestPathDistanceNoDoor(v1,v2),9);
        assertEquals(m.shortestPathDistanceNoDoor(v1,v3),Double.POSITIVE_INFINITY);


        Vertex v5 = new Vertex(8,5,'D');
        Vertex v4 = new Vertex(7,2,'0');

        Vertex[] arrayPath = m.shortestPathPathDoor(v5,v4,true);
        for(int i=0; i < arrayPath.length;i++)
        {
            System.out.println(arrayPath[i]+"->");
        }
        System.out.println(m.shortestPathDistanceDoor(v5,v4,true));

        System.out.println(m.isReachAbleThroughDoor(v4,v1));

        arrayPath = m.isReachAbleThroughDoorPath(v1,v4);
        for(int i=0; i < arrayPath.length;i++)
        {
            System.out.println(arrayPath[i]+"->");
        }



        endSuite("Maze Test");
    }

    public static void toFile(MazeGenerator mg, String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName+".txt");
            myWriter.write(mg.toString());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(fileName+".md");
            myWriter.write(mg.toMarkDown());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void studentExample() {
        Maze m = new Maze("studentMaze.txt.txt");
        m.stage1Reducing();
        m.stage2Reducing();
        m.stage3Reducing();
        System.out.println(m.isReachAble(m.getVertex(new Vertex(8,9, 'S')), m.getVertex(new Vertex(4,1, 'T'))));
        System.out.println(m.isReachAble(m.getVertex(new Vertex(8,9, 'S')), m.getVertex(new Vertex(2,6, '1'))));
        Vertex[] path = m.isReachAblePath(m.getVertex(new Vertex(8,9, 'S')), m.getVertex(new Vertex(2,6, '1')));
        for(Vertex v: path){
            System.out.println(v);
        }
        System.out.println(m.isReachAbleThroughDoor(m.getVertex(new Vertex(8,9, 'S')), m.getVertex(new Vertex(4,1, 'T'))));
        path = m.isReachAbleThroughDoorPath(m.getVertex(new Vertex(8,9, 'S')), m.getVertex(new Vertex(4,1, 'T')));
        for(Vertex v: path){
            System.out.println(v);
        }
        System.out.println(m.shortestPathDistanceNoDoor(m.getVertex(new Vertex(8,9, 'S')), m.getVertex(new Vertex(4,1, 'T'))));
        System.out.println(m.shortestPathDistanceNoDoor(m.getVertex(new Vertex(8,9, 'S')), m.getVertex(new Vertex(2,6, '1'))));
        System.out.println(m.shortestPathThroughDoor(m.getVertex(new Vertex(8,9, 'S')), m.getVertex(new Vertex(4,1, 'T'))));
        System.out.println(m.shortestPathThroughDoor(m.getVertex(new Vertex(8,9, 'S')), m.getVertex(new Vertex(2,6, '1'))));
        System.out.println(m.getRatio(m.getVertex(new Vertex(2,6, '1'))));
    }

    public void linkedListTest(){
        startSuite("Linked List Tests");
        LinkedList<Integer> list = new LinkedList<>();
        assertEquals(list.printForward(),"Empty List");
        list.append(12);
        assertEquals(list.printForward(),"12");
        assertEquals(list.head.data,12);
        assertEquals(list.tail.data,12);
        assertEquals(list.size,1);
        list.remove(12);
        assertEquals(list.printForward(),"Empty List");
        assertEquals(list.size,0);
        list.prepend(12);
        assertEquals(list.printForward(),"12");
        assertEquals(list.head.data,12);
        assertEquals(list.tail.data,12);
        assertEquals(list.size,1);

        list.append(5);
        assertEquals(list.printForward(),"12->5");
        assertEquals(list.head.data,12);
        assertEquals(list.tail.data,5);
        assertEquals(list.size,2);

        list.prepend(7);
        assertEquals(list.printForward(),"7->12->5");
        assertEquals(list.head.data,7);
        assertEquals(list.tail.data,5);
        assertEquals(list.size,3);

        list.append(17);
        assertEquals(list.printForward(),"7->12->5->17");
        assertEquals(list.head.data,7);
        assertEquals(list.tail.data,17);
        assertEquals(list.size,4);

        list.append(22);
        assertEquals(list.printForward(),"7->12->5->17->22");
        assertEquals(list.head.data,7);
        assertEquals(list.tail.data,22);
        assertEquals(list.size,5);

        list.append(42);
        assertEquals(list.printForward(),"7->12->5->17->22->42");
        assertEquals(list.head.data,7);
        assertEquals(list.tail.data,42);
        assertEquals(list.size,6);

        list.remove(17);
        assertEquals(list.printForward(),"7->12->5->22->42");
        assertEquals(list.head.data,7);
        assertEquals(list.tail.data,42);
        assertEquals(list.size,5);

        list.remove(13);
        assertEquals(list.printForward(),"7->12->5->22->42");
        assertEquals(list.head.data,7);
        assertEquals(list.tail.data,42);
        assertEquals(list.size,5);

        list.insertionSort();
        assertEquals(list.printForward(),"5->7->12->22->42");
        assertEquals(list.head.data,5);
        assertEquals(list.tail.data,42);
        assertEquals(list.size,5);

        assertEquals(list.contains(15),false);
        assertEquals(list.contains(5),true);

        assertEquals(list.find(12).data,12);
        assertEquals(list.find(15));

        endSuite("Linked List Tests");
    }

    public void vertexEdgeTest(){
        startSuite("Vertex Test");
        Vertex v1 = new Vertex(0,0,'D');
        Vertex v2 = new Vertex(1,0,'D');
        assertEquals(v1.edges.size,v1.getEdges().length);
        assertEquals(v1.toString(),"(0:0)[D]");
        assertEquals(v2.toString(),"(1:0)[D]");
        Edge e = new Edge(v1,v2,2);
        v1.addEdge(e);
        v2.addEdge(e);
        assertEquals(1,v1.getEdges().length);
        assertEquals(1,v2.getEdges().length);
        endSuite("Vertex Test");
    }
}
