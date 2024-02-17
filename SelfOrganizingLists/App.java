public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Testing Count List:");
        for(int i = 0; i < 20; i++)
        {
            System.out.print("*");
        }
        System.out.println();

        TransposeList<Integer> countList = new TransposeList<Integer>();

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
        countList.printMe();

        // 2 access count 6
        countList.access(2);
        countList.access(2);
        countList.access(2);
        countList.access(2);
        countList.access(2);
        countList.access(2);
        countList.printMe();

        countList.access(9);
        countList.access(9);
        countList.printMe();

        countList.access(0);
        countList.printMe();

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

        System.out.println("Testing Contains Method...");
        if(myList1.contains(7))
        {
            System.out.println("Contains 7");
        }
        else{
            System.out.println("contains does not work");
        }
        if(myRecList.contains(7))
        {
            System.out.println("Contains 7");
        }
        else{
            System.out.println("contains does not work");
        }
        System.out.println("Test Complete\n");

        System.out.println("Testing Get Method...");
        Node<Integer> node = myList1.get(2);//should get 9
        System.out.println("9 = "+node.data);
        node = myList1.get(0);
        System.out.println("2 = "+node.data);
        node = myList1.get(10);
        System.out.println("10 = "+node.data);

        node = myList1.get(20);
        if(node !=null)
            System.out.println("10 = "+node.data);


        node = myRecList.get(0);
        System.out.println("2 = "+node.data);
        node = myRecList.get(2);
        System.out.println("9 = "+node.data);
        node = myRecList.get(10);
        System.out.println("10 = "+node.data);
        node = myRecList.get(20);
        if(node !=null)
            System.out.println("10 = "+node.data);
        System.out.println("Test Complete\n");

        System.out.println("Testing Finnd Method...");
        Node<Integer> found = myList1.find(2);//should get 9
        System.out.println("2 = "+found.data);
        found = myList1.find(0);
        System.out.println("0 = "+found.data);
        found = myList1.find(10);
        System.out.println("10 = "+found.data);

        found = myList1.find(20);
        if(found !=null)
            System.out.println("10 = "+found.data);


        found = myRecList.find(2);
        System.out.println("2 = "+found.data);
        found = myRecList.find(9);
        System.out.println("9 = "+found.data);
        found = myRecList.find(10);
        System.out.println("10 = "+found.data);
        found = myRecList.find(20);
        if(found !=null)
            System.out.println("10 = "+found.data);
        System.out.println("Test Complete\n");


        System.out.println("Testing Colne Method...");
        myList1.clone(countList).printMe();
        myRecList.clone(countList).printMe();
        System.out.println("Test Complete\n");
    }

}
