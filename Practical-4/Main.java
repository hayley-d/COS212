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
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expected+ " but got "+ actual +  ANSI_RESET);
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

        longTest();
        hashTest();


        startSuite("Logic Test");
        assertEquals(true,true);
        assertEquals(false,false);
        assertEquals(true,true);
        assertEquals(false,false);
        endSuite("Number Test");

        endAll();
    }

    public static void longTest(){
        startSuite("Prime Number Generator Test");
        PrimeNumberGenerator p = new PrimeNumberGenerator();

        //test if head is 2
        assertEquals(p.head.value,2);


        assertEquals(p.currentPrime(),2);
        assertEquals(p.toString(),"[2]");

        assertEquals(p.nextPrime(),3);
        assertEquals(p.toString(),"[3]");
        assertEquals(p.currentPrime(),3);

        assertEquals(p.nextPrime(),5);
        assertEquals(p.toString(),"[5]");
        assertEquals(p.currentPrime(),5);

        assertEquals(p.nextPrime(),7);
        assertEquals(p.toString(),"[7]");
        assertEquals(p.currentPrime(),7);

        assertEquals(p.nextPrime(),11);
        assertEquals(p.toString(),"[11]->[13]");
        assertEquals(p.currentPrime(),11);
        assertEquals(p.nextPrime(),13);

        assertEquals(p.currentPrime(),13);
        assertEquals(p.nextPrime(),17);
        assertEquals(p.toString(),"[17]->[19]->[23]");
        assertEquals(p.currentPrime(),17);
        assertEquals(p.nextPrime(),19);
        assertEquals(p.toString(),"[19]->[23]");
        assertEquals(p.nextPrime(),23);
        assertEquals(p.nextPrime(),29);
        assertEquals(p.toString(),"[29]->[31]->[37]->[41]->[43]");
        assertEquals(p.nextPrime(),31);
        assertEquals(p.nextPrime(),37);
        assertEquals(p.nextPrime(),41);
        assertEquals(p.nextPrime(),43);
        p.sieveOfEratosthenes();

        endSuite("Prime Number Generator Test");
    }

    public static void hashTest(){
        startSuite("Hash Test");
        Hashmap hash = new Hashmap();
        assertEquals(hash.toString(),"2\n" + "0\t-");

        hash.insert(21345678,56);
        assertEquals(hash.toString(),"2\n" + "0\tu21345678:56%");

        hash.insert(78654654,56);
        assertEquals(hash.toString(),"3\n" + "0\tu21345678:56%\n" + "1\tu78654654:56%");

        hash.insert(23543678,57);
        assertEquals(hash.toString(),"5\n" + "0\tu21345678:56%\n" + "1\tu78654654:56%\n" + "2\tu23543678:57%\n" + "3\t-");

        hash.insert(19237654,57);
        assertEquals(hash.toString(),"5\n" + "0\tu21345678:56%\n" + "1\tu78654654:56%\n" + "2\tu23543678:57%\n" + "3\tu19237654:57%");

        hash.insert(21345678,89);
        assertEquals(hash.toString(),"5\n" + "0\tu21345678:89%\n" + "1\tu78654654:56%\n" + "2\tu23543678:57%\n" + "3\tu19237654:57%");
        System.out.println(hash.toStringOneLine());

        hash.insert(27689045,89);
        assertEquals(hash.toString(),"7\n" + "0\t-\n" + "1\tu78654654:56%\n" + "2\tu21345678:89%\n" + "3\t-\n" + "4\tu23543678:57%\n" + "5\t-\n" + "6\tu27689045:89%\n" + "7\tu19237654:57%");

        hash.insert(45678456,89);
        assertEquals(hash.toString(),"7\n" +
                "0\tu45678456:89%\n" +
                "1\tu78654654:56%\n" +
                "2\tu21345678:89%\n" +
                "3\t-\n" +
                "4\tu23543678:57%\n" +
                "5\t-\n" +
                "6\tu27689045:89%\n" +
                "7\tu19237654:57%");

        hash.insert(23434567,89);
        assertEquals(hash.toString(),"7\n" +
                "0\tu45678456:89%\n" +
                "1\tu78654654:56%\n" +
                "2\tu21345678:89%\n" +
                "3\t-\n" +
                "4\tu23543678:57%\n" +
                "5\tu23434567:89%\n" +
                "6\tu27689045:89%\n" +
                "7\tu19237654:57%");

        hash.insert(19765676,89);
        assertEquals(hash.toString(),"7\n" +
                "0\tu45678456:89%\n" +
                "1\tu78654654:56%\n" +
                "2\tu21345678:89%\n" +
                "3\tu19765676:89%\n" +
                "4\tu23543678:57%\n" +
                "5\tu23434567:89%\n" +
                "6\tu27689045:89%\n" +
                "7\tu19237654:57%");

        hash.insert(11235567,89);
        assertEquals(hash.toString(),"11\n" +
                "0\tu23543678:57%\n" +
                "1\tu78654654:56%\n" +
                "2\tu23434567:89%\n" +
                "3\tu19237654:57%\n" +
                "4\t-\n" +
                "5\t-\n" +
                "6\tu27689045:89%\n" +
                "7\t-\n" +
                "8\t-\n" +
                "9\t-\n" +
                "10\tu11235567:89%\n" +
                "11\tu45678456:89%\n" +
                "12\t-\n" +
                "13\t-\n" +
                "14\tu21345678:89%\n" +
                "15\tu19765676:89%");

        endSuite("Hash Test");
    }

}
