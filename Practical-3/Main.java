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

    public static void assertEquals(Node actual, Integer expectedMark,int expectedNum)
    {
        TESTS_RUN++;
        if(actual==null)
        {
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expectedMark+" and "+ expectedNum + " but got null" +  ANSI_RESET);
            return;
        }
        else if(actual.mark == expectedMark && actual.studentNumber==expectedNum){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expectedMark+" and "+ expectedNum + " but got " + actual +  ANSI_RESET);
            return;
        }
    }

    public static void assertEquals(Node actual)
    {
        TESTS_RUN++;
        if(actual==null)
        {
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected null but got " + actual +  ANSI_RESET);
            return;
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
        constructorTest();
        sortTest();
        accessTest();
        removeTest();
        endAll();
    }

    public static void constructorTest(){
        startSuite("Constructor Test");
        //Empty Tree
        SplayTree tree = new SplayTree("Empty Tree");
        assertEquals(tree.root);
        //Single root
        String input = "{[u10:50%]{}{}}";
        tree = new SplayTree(input);
        assertEquals(tree.root,50,10);
        assertEquals(tree.toStringOneLine(),"{[u10:50%]{}{}}");
        input = "{[u10:50%]{[u5:40%]{}{}}{}}";
        tree = new SplayTree(input);
        assertEquals(tree.root,50,10);
        assertEquals(tree.root.left,40,5);
        assertEquals(tree.toStringOneLine(),"{[u10:50%]{[u5:40%]{}{}}{}}");

        input = "{[u10:50%]{[u5:40%]{}{}}{[u15:60%]{}{}}}";
        tree = new SplayTree(input);
        assertEquals(tree.root,50,10);
        assertEquals(tree.root.left,40,5);
        assertEquals(tree.root.right,60,15);
        assertEquals(tree.toStringOneLine(),"{[u10:50%]{[u5:40%]{}{}}{[u15:60%]{}{}}}");
        endSuite("Constructor Test");
    }

    public static void sortTest(){
        startSuite("Sort Test");
        //Empty Tree
        SplayTree tree = new SplayTree();
        assertEquals(tree.sortByStudentNumber(),"Empty Tree");
        assertEquals(tree.sortByMark(),"Empty Tree");

        //{[u4:null%]{[u3:null%]{[u1:null%]{}{}}{}}{[u6:null%]{[u5:null%]{}{}}{[u9:null%]{}{[u11:null%]{}{[u14:null%]{}{[u38:null%]{[u23:null%]{[u21:null%]{}{}}{[u30:null%]{}{}}}{}}}}}}}
        tree = new SplayTree("{[u4:null%]{[u3:null%]{[u1:null%]{}{}}{}}{[u6:null%]{[u5:null%]{}{}}{[u9:null%]{}{[u11:null%]{}{[u14:null%]{}{[u38:null%]{[u23:null%]{[u21:null%]{}{}}{[u30:null%]{}{}}}{}}}}}}}");
        assertEquals(tree.sortByStudentNumber(),"[u1:null%][u3:null%][u4:null%][u5:null%][u6:null%][u9:null%][u11:null%][u14:null%][u21:null%][u23:null%][u30:null%][u38:null%]");
        assertEquals(tree.sortByMark(),"[u1:null%][u3:null%][u4:null%][u5:null%][u6:null%][u9:null%][u11:null%][u14:null%][u21:null%][u23:null%][u30:null%][u38:null%]");

       //{[u23:90%]{[u1:90%]{}{[u12:56%]{[u6:10%]{}{}}{[u14:5%]{}{}}}}{[u34:80%]{}{}}}
        tree = new SplayTree("{[u23:90%]{[u1:90%]{}{[u12:56%]{[u6:10%]{}{}}{[u14:5%]{}{}}}}{[u34:80%]{}{}}}");
        assertEquals(tree.sortByStudentNumber(),"[u1:90%][u6:10%][u12:56%][u14:5%][u23:90%][u34:80%]");
        assertEquals(tree.sortByMark(),"[u14:5%][u6:10%][u12:56%][u34:80%][u1:90%][u23:90%]");

        tree =  new SplayTree("{[u5:34%]{}{[u14:66%]{[u10:null%]{}{[u11:null%]{}{}}}{[u21:23%]{[u20:null%]{}{}}{[u50:20%]{[u38:40%]{[u30:null%]{}{[u37:null%]{[u31:null%]{}{}}{}}}{[u40:null%]{}{}}}{}}}}}");
        assertEquals(tree.sortByStudentNumber(),"[u5:34%][u10:null%][u11:null%][u14:66%][u20:null%][u21:23%][u30:null%][u31:null%][u37:null%][u38:40%][u40:null%][u50:20%]");
        assertEquals(tree.sortByMark(),"[u10:null%][u11:null%][u20:null%][u30:null%][u31:null%][u37:null%][u40:null%][u50:20%][u21:23%][u5:34%][u38:40%][u14:66%]");

        tree = new SplayTree();
        tree.access(23,45);
        assertEquals(tree.sortByStudentNumber(),"[u23:45%]");
        assertEquals(tree.sortByMark(),"[u23:45%]");

        tree.access(15);
        assertEquals(tree.sortByStudentNumber(),"[u15:null%][u23:45%]");
        assertEquals(tree.sortByMark(),"[u15:null%][u23:45%]");

        tree.access(11,12);
        assertEquals(tree.sortByStudentNumber(),"[u11:12%][u15:null%][u23:45%]");
        assertEquals(tree.sortByMark(),"[u15:null%][u11:12%][u23:45%]");

        endSuite("Sort Test");
    }

    public static void accessTest(){
        startSuite("Access Test");
        //Empty Tree
        SplayTree tree = new SplayTree("Empty Tree");
        //double access
        assertEquals(tree.root);
        assertEquals(tree.access(14,70),70,14);
        assertEquals(tree.root,70,14);
        //single access
        tree = new SplayTree("Empty Tree");
        assertEquals(tree.access(14),null,14);
        assertEquals(tree.root,null,14);

        //two access
        tree = new SplayTree();
        assertEquals(tree.access(14,70),70,14);
        assertEquals(tree.root,70,14);
        assertEquals(tree.access(15,60),60,15);
        assertEquals(tree.root,60,15);
        assertEquals(tree.root.left,70,14);

        tree = new SplayTree();
        assertEquals(tree.access(14),null,14);
        assertEquals(tree.root,null,14);
        assertEquals(tree.access(14,70),70,14);
        assertEquals(tree.root,70,14);
        assertEquals(tree.root.left);
        assertEquals(tree.root.right);

        //multiple
        tree = new SplayTree();
        assertEquals(tree.access(1,10),10,1);
        assertEquals(tree.root,10,1);
        assertEquals(tree.root.left);
        assertEquals(tree.root.right);
        assertEquals(tree.access(1,40),40,1);
        assertEquals(tree.root,40,1);
        assertEquals(tree.root.left);
        assertEquals(tree.root.right);
        assertEquals(tree.access(2,20),20,2);
        assertEquals(tree.root,20,2);
        assertEquals(tree.root.left,40,1);
        assertEquals(tree.access(3,30),30,3);
        assertEquals(tree.root,30,3);
        assertEquals(tree.root.left,20,2);
        assertEquals(tree.root.left.left,40,1);

        tree = new SplayTree();
        assertEquals(tree.access(1,10),10,1);
        assertEquals(tree.root,10,1);
        assertEquals(tree.access(2,20),20,2);
        assertEquals(tree.root,20,2);
        assertEquals(tree.access(3,30),30,3);
        assertEquals(tree.root,30,3);
        assertEquals(tree.access(4,40),40,4);
        assertEquals(tree.root,40,4);
        assertEquals(tree.access(5,50),50,5);
        assertEquals(tree.root,50,5);
        assertEquals(tree.access(6,60),60,6);
        assertEquals(tree.access(7,70),70,7);
        assertEquals(tree.access(8,80),80,8);

        tree = new SplayTree();
        assertEquals(tree.access(5,50),50,5);
        assertEquals(tree.access(4,40),40,4);
        assertEquals(tree.access(7,70),70,7);
       // assertEquals(tree.access(6,60),60,6);

        tree = new SplayTree("{[u31:null%]{[u20:null%]{[u11:null%]{[u10:null%]{}{}}{}}{[u30:null%]{}{}}}{[u37:null%]{}{[u38:null%]{}{[u40:null%]{}{}}}}}");
        assertEquals(tree.toStringOneLine(),"{[u31:null%]{[u20:null%]{[u11:null%]{[u10:null%]{}{}}{}}{[u30:null%]{}{}}}{[u37:null%]{}{[u38:null%]{}{[u40:null%]{}{}}}}}");

        tree.access(37);

        assertEquals(tree.toStringOneLine(),"{[u37:null%]{[u31:null%]{[u20:null%]{[u11:null%]{[u10:null%]{}{}}{}}{[u30:null%]{}{}}}{}}{[u38:null%]{}{[u40:null%]{}{}}}}");
        tree.access(20);
        assertEquals(tree.toStringOneLine(),"{[u20:null%]{[u11:null%]{[u10:null%]{}{}}{}}{[u31:null%]{[u30:null%]{}{}}{[u37:null%]{}{[u38:null%]{}{[u40:null%]{}{}}}}}}" );
        tree.access(30);
        assertEquals(tree.toStringOneLine(),"{[u30:null%]{[u20:null%]{[u11:null%]{[u10:null%]{}{}}{}}{}}{[u31:null%]{}{[u37:null%]{}{[u38:null%]{}{[u40:null%]{}{}}}}}}");
       tree.access(38);
       assertEquals(tree.toStringOneLine(),"{[u38:null%]{[u30:null%]{[u20:null%]{[u11:null%]{[u10:null%]{}{}}{}}{}}{[u37:null%]{[u31:null%]{}{}}{}}}{[u40:null%]{}{}}}" );

        tree = new SplayTree("{[u20:null%]{[u11:null%]{[u10:null%]{}{}}{}}{[u31:null%]{[u30:null%]{}{}}{[u37:null%]{}{[u38:null%]{}{[u40:null%]{}{}}}}}}");
        tree.access(17,50);
        assertEquals(tree.toStringOneLine(),"{[u17:50%]{[u11:null%]{[u10:null%]{}{}}{}}{[u20:null%]{}{[u31:null%]{[u30:null%]{}{}}{[u37:null%]{}{[u38:null%]{}{[u40:null%]{}{}}}}}}}");
        assertEquals(tree.sortByMark(),"[u10:null%][u11:null%][u20:null%][u30:null%][u31:null%][u37:null%][u38:null%][u40:null%][u17:50%]");
        assertEquals(tree.sortByStudentNumber(),"[u10:null%][u11:null%][u17:50%][u20:null%][u30:null%][u31:null%][u37:null%][u38:null%][u40:null%]");
        assertEquals(tree.toStringOneLine(),"{[u17:50%]{[u11:null%]{[u10:null%]{}{}}{}}{[u20:null%]{}{[u31:null%]{[u30:null%]{}{}}{[u37:null%]{}{[u38:null%]{}{[u40:null%]{}{}}}}}}}");

        tree.access(35,221);
        assertEquals(tree.toStringOneLine(),"{[u35:221%]{[u20:null%]{[u17:50%]{[u11:null%]{[u10:null%]{}{}}{}}{}}{[u31:null%]{[u30:null%]{}{}}{}}}{[u37:null%]{}{[u38:null%]{}{[u40:null%]{}{}}}}}");
        tree.access(31);
        assertEquals(tree.toStringOneLine(),"{[u31:null%]{[u20:null%]{[u17:50%]{[u11:null%]{[u10:null%]{}{}}{}}{}}{[u30:null%]{}{}}}{[u35:221%]{}{[u37:null%]{}{[u38:null%]{}{[u40:null%]{}{}}}}}}");
        tree.access(17);
        assertEquals(tree.toStringOneLine(),"{[u17:50%]{[u11:null%]{[u10:null%]{}{}}{}}{[u20:null%]{}{[u31:null%]{[u30:null%]{}{}}{[u35:221%]{}{[u37:null%]{}{[u38:null%]{}{[u40:null%]{}{}}}}}}}}");
        tree.access(10);
        assertEquals(tree.toStringOneLine(),"{[u10:null%]{}{[u11:null%]{}{[u17:50%]{}{[u20:null%]{}{[u31:null%]{[u30:null%]{}{}}{[u35:221%]{}{[u37:null%]{}{[u38:null%]{}{[u40:null%]{}{}}}}}}}}}}");
        tree.access(30);
        assertEquals(tree.toStringOneLine(),"{[u30:null%]{[u10:null%]{}{[u17:50%]{[u11:null%]{}{}}{[u20:null%]{}{}}}}{[u31:null%]{}{[u35:221%]{}{[u37:null%]{}{[u38:null%]{}{[u40:null%]{}{}}}}}}}");

        endSuite("Access Test");
    }

    public static void removeTest(){
        startSuite("Remove Test");
        //Empty Tree
        SplayTree tree = new SplayTree("Empty Tree");
        assertEquals(tree.access(14,70),70,14);
        assertEquals(tree.root,70,14);
        tree.remove(14);
        assertEquals(tree.root);

        tree = new SplayTree("{[u7:null%]{[u3:null%]{[u1:null%]{}{[u2:null%]{}{}}}{[u5:null%]{}{}}}{[u13:null%]{[u10:null%]{}{}}{}}}");
        tree.remove(5);
        assertEquals(tree.toStringOneLine(),"{[u3:null%]{[u1:null%]{}{[u2:null%]{}{}}}{[u7:null%]{}{[u13:null%]{[u10:null%]{}{}}{}}}}");
        tree.remove(10);
        assertEquals(tree.toStringOneLine(),"{[u7:null%]{[u3:null%]{[u1:null%]{}{[u2:null%]{}{}}}{}}{[u13:null%]{}{}}}");
        tree.remove(1);
        assertEquals(tree.toStringOneLine(),"{[u3:null%]{[u2:null%]{}{}}{[u7:null%]{}{[u13:null%]{}{}}}}");
        tree.remove(7);
        assertEquals(tree.toStringOneLine(),"{[u3:null%]{[u2:null%]{}{}}{[u13:null%]{}{}}}");
        tree.remove(6);
        assertEquals(tree.toStringOneLine(),"{[u3:null%]{[u2:null%]{}{}}{[u13:null%]{}{}}}");
        tree.remove(13);
        assertEquals(tree.toStringOneLine(),"{[u3:null%]{[u2:null%]{}{}}{}}");
        tree.remove(2);
        assertEquals(tree.toStringOneLine(),"{[u3:null%]{}{}}");
        tree.remove(3);
        assertEquals(tree.toStringOneLine(),"Empty Tree");

        tree = new SplayTree("{[u4:null%]{[u3:null%]{[u1:null%]{}{}}{}}{[u6:null%]{[u5:null%]{}{}}{[u9:null%]{}{[u11:null%]{}{[u14:null%]{}{[u38:null%]{[u23:null%]{[u21:null%]{}{}}{[u30:null%]{}{}}}{}}}}}}}");
        /*tree.remove(11);*/
        tree.access(11);
        System.out.println(tree.toString());
        assertEquals(tree.toStringOneLine(),"{[u11:null%]{[u4:null%]{[u3:null%]{[u1:null%]{}{}}{}}{[u9:null%]{[u6:null%]{[u5:null%]{}{}}{}}{}}}{[u14:null%]{}{[u38:null%]{[u23:null%]{[u21:null%]{}{}}{[u30:null%]{}{}}}{}}}}");
        tree.access(9);
        assertEquals(tree.toStringOneLine(),"{[u9:null%]{[u4:null%]{[u3:null%]{[u1:null%]{}{}}{}}{[u6:null%]{[u5:null%]{}{}}{}}}{[u11:null%]{}{[u14:null%]{}{[u38:null%]{[u23:null%]{[u21:null%]{}{}}{[u30:null%]{}{}}}{}}}}}");

        endSuite("Remove Test");
    }


}
