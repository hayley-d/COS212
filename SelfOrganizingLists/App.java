public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Testing Count List:");
        for(int i = 0; i < 20; i++)
        {
            System.out.print("*");
        }
        System.out.println();

        CountList<Integer> countList = new CountList<>();

        System.out.println("Testing Insert....");
        for(int i = 0; i < 11; i++)
        {
            countList.insert(i);

        }
        System.out.println("Insert Complete: Success");

        System.out.println("\nTesting Access Method.....");
        // 7 access count == 3
        countList.access(7);
        countList.access(7);
        countList.access(7);

        // 2 access count 6
        countList.access(2);
        countList.access(2);
        countList.access(2);
        countList.access(2);
        countList.access(2);
        countList.access(2);

        countList.access(9);
        countList.access(9);

        countList.access(0);

        countList.access(20);

        System.out.println("Test Complete: Success\n");

        System.out.println("Testing  Iterative Traverse copy....");
        IterativeTraverse<Integer> myList1 = new IterativeTraverse<>(countList);
        System.out.println(myList1);
        System.out.println("Test Complete\n");

        System.out.println("Testing Size Method....");
        System.out.println(myList1.size());
        System.out.println("Test Complete\n");

        System.out.println("Testing Reverse Method....");
        myList1.reverseList().printMe();
        System.out.println("Test Complete\n");

        System.out.println("Testing Recursive Traverse copy....");
        RecursiveTraverse<Integer> myRecList = new RecursiveTraverse<>(countList);
        System.out.println(myRecList);
        System.out.println("Test Complete\n");

        System.out.println("Testing Size Method....");
        System.out.println(myRecList.size());
        System.out.println("Test Complete\n");

        System.out.println("Testing Reverse Method....");
        myRecList.reverseList().printMe();
        System.out.println("Test Complete\n");
    }

}
