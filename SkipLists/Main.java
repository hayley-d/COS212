public class Main {
    public static void main(String[] args) {
        /*SkipList<Integer> myList = new SkipList<>(4);
        for (int i = 0; i < 10; i++) {
            myList.insert(i);

        }
        myList.insert(3);
        myList.insert(9);
        System.out.println(myList);*/
        /*System.out.print("Searching for 8\t");

        myList.printSearchPath(0);
        System.out.print("Searching for 2\t");
        myList.printSearchPath(2);*/
        System.out.println("\n");

        SkipList<Integer> myList2 = new SkipList<>(4);
        for (int i = 9; i >=0; i--) {
            myList2.insert(i);

        }
        System.out.println(myList2);
        System.out.println("\n");
        myList2.insert(9);
        System.out.println("\n");
        System.out.println(myList2);
        myList2.insert(9);
        System.out.println("\n");
        System.out.println(myList2);






    }
}
