public class Main {

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
        Hashmap<String,Integer> map = new Hashmap<>();
        assertEquals(map.capacity,2);
        assertEquals(map.numValues,0);
        assertEquals(map.data.length,2);
        endSuite("Constructor Test");

        startSuite("Insert Test");
        map = new Hashmap<>();
        assertEquals(map.insert("One",1),true);
        assertEquals(map.numValues,1);
        assertEquals(map.data.length,2);

        assertEquals(map.insert("Two",2),true);
        assertEquals(map.numValues,2);
        assertEquals(map.data.length,4);

        assertEquals(map.insert("Tree",3),true);
        assertEquals(map.numValues,3);
        assertEquals(map.data.length,4);

        assertEquals(map.insert("Four",4),true);
        assertEquals(map.numValues,4);
        assertEquals(map.data.length,8);

        assertEquals(map.insert("Five",5),true);
        assertEquals(map.numValues,5);
        assertEquals(map.data.length,8);

        assertEquals(map.insert("Six",6),true);
        assertEquals(map.numValues,6);
        assertEquals(map.data.length,8);

        assertEquals(map.insert("Seven",7),true);
        assertEquals(map.numValues,7);
        assertEquals(map.data.length,8);

        assertEquals(map.insert("Eight",8),true);
        assertEquals(map.numValues,8);
        assertEquals(map.data.length,16);

        assertEquals(map.insert("One",1),false);
        assertEquals(map.numValues,8);
        assertEquals(map.data.length,16);

        assertEquals(map.insert("Two",2),false);
        assertEquals(map.numValues,8);
        assertEquals(map.data.length,16);

        assertEquals(map.insert("Tree",3),false);
        assertEquals(map.numValues,8);
        assertEquals(map.data.length,16);

        assertEquals(map.insert("Four",4),false);
        assertEquals(map.numValues,8);
        assertEquals(map.data.length,16);

        assertEquals(map.insert("Five",5),false);
        assertEquals(map.numValues,8);
        assertEquals(map.data.length,16);

        assertEquals(map.insert("Six",6),false);
        assertEquals(map.numValues,8);
        assertEquals(map.data.length,16);

        assertEquals(map.insert("Seven",7),false);
        assertEquals(map.numValues,8);
        assertEquals(map.data.length,16);

        assertEquals(map.insert("Eight",8),false);
        assertEquals(map.numValues,8);
        assertEquals(map.data.length,16);
        endSuite("Insert Test");

        /*Stating new Test*/
        startSuite("Delete Test");

        map.delete("One");
        assertEquals(map.numValues,7);
        assertEquals(map.data.length,16);

        map.delete("Two");
        assertEquals(map.numValues,6);
        assertEquals(map.data.length,16);

        map.delete("Three");
        assertEquals(map.numValues,6);
        assertEquals(map.data.length,16);

        map.delete("Tree");
        assertEquals(map.numValues,5);
        assertEquals(map.data.length,16);

        map.delete("Four");
        assertEquals(map.numValues,4);
        assertEquals(map.data.length,16);

        map.delete("Five");
        assertEquals(map.numValues,3);
        assertEquals(map.data.length,16);

        map.delete("Six");
        assertEquals(map.numValues,2);
        assertEquals(map.data.length,16);

        map.delete("Seven");
        assertEquals(map.numValues,1);
        assertEquals(map.data.length,16);

        map.delete("Eight");
        assertEquals(map.numValues,0);
        assertEquals(map.data.length,16);

        map.delete("Nine");
        assertEquals(map.numValues,0);
        assertEquals(map.data.length,16);

        endSuite("Delete Test");

        startSuite("Get Test");
        map = new Hashmap<>();
        map.insert("One",1);
        map.insert("Two",2);
        map.insert("Three",3);
        map.insert("Four",4);
        map.insert("Five",5);
        map.insert("Six",6);
        map.insert("Seven",7);
        map.insert("Eight",8);

        assertEquals(map.get("One"),1);
        assertEquals(map.get("Two"),2);
        assertEquals(map.get("Three"),3);
        assertEquals(map.get("Four"),4);
        assertEquals(map.get("Five"),5);
        assertEquals(map.get("Six"),6);
        assertEquals(map.get("Seven"),7);
        assertEquals(map.get("Eight"),8);

        map.delete("Eight");
        assertEquals(map.get("Eight"));
        assertEquals(map.get("One"),1);
        assertEquals(map.get("Two"),2);
        assertEquals(map.get("Three"),3);
        assertEquals(map.get("Four"),4);
        assertEquals(map.get("Five"),5);
        assertEquals(map.get("Six"),6);
        assertEquals(map.get("Seven"),7);

        map.delete("Seven");
        assertEquals(map.get("Seven"));
        assertEquals(map.get("One"),1);
        assertEquals(map.get("Two"),2);
        assertEquals(map.get("Three"),3);
        assertEquals(map.get("Four"),4);
        assertEquals(map.get("Five"),5);
        assertEquals(map.get("Six"),6);

        map.delete("Six");
        assertEquals(map.get("Six"));
        assertEquals(map.get("One"),1);
        assertEquals(map.get("Two"),2);
        assertEquals(map.get("Three"),3);
        assertEquals(map.get("Four"),4);
        assertEquals(map.get("Five"),5);

        map.delete("Five");
        assertEquals(map.get("Five"));
        assertEquals(map.get("One"),1);
        assertEquals(map.get("Two"),2);
        assertEquals(map.get("Three"),3);
        assertEquals(map.get("Four"),4);

        map.delete("Four");
        assertEquals(map.get("Four"));
        assertEquals(map.get("One"),1);
        assertEquals(map.get("Two"),2);
        assertEquals(map.get("Three"),3);

        map.delete("Three");
        assertEquals(map.get("Three"));
        assertEquals(map.get("One"),1);
        assertEquals(map.get("Two"),2);

        map.delete("Two");
        assertEquals(map.get("One"),1);
        assertEquals(map.get("Two"));

        map.delete("One");
        assertEquals(map.get("One"));

        endSuite("Get Test");

        startSuite("Clear Test");
        map = new Hashmap<>();
        map.insert("One",1);
        map.insert("Two",2);
        map.insert("Three",3);
        map.insert("Four",4);
        map.insert("Five",5);
        map.insert("Six",6);
        map.insert("Seven",7);
        map.insert("Eight",8);

        map.clear();
        assertEquals(map.numValues,0);
        assertEquals(map.capacity,2);
        assertEquals(map.data.length,2);

        map.insert("One",1);
        map.insert("Two",2);
        map.insert("Three",3);
        map.insert("Four",4);
        map.insert("Five",5);
        map.insert("Six",6);
        map.insert("Seven",7);
        map.insert("Eight",8);

        assertEquals(map.numValues,8);
        assertEquals(map.capacity,16);
        assertEquals(map.data.length,16);

        endSuite("Clear Test");

        startSuite("Get Keys Test");
        map = new Hashmap<>();
        assertEquals(map.getKeys().length,0);
        map.insert("One",1);
        assertEquals(map.getKeys().length,1);
        map.insert("Two",2);
        assertEquals(map.getKeys().length,2);
        map.insert("Three",3);
        assertEquals(map.getKeys().length,3);
        map.insert("Four",4);
        assertEquals(map.getKeys().length,4);
        map.insert("Five",5);
        assertEquals(map.getKeys().length,5);
        map.insert("Six",6);
        assertEquals(map.getKeys().length,6);
        map.insert("Seven",7);
        assertEquals(map.getKeys().length,7);
        map.insert("Eight",8);
        assertEquals(map.getKeys().length,8);
        endSuite("Get Keys Test");

        endAll();
    }
}
