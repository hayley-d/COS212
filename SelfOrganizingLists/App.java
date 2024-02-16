public class App {
    public static void main(String[] args) throws Exception {
        CountList<Integer> countList = new CountList<>();

        for(int i = 0; i < 11; i++)
        {
            countList.insert(i);

        }
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

        countList.printMe();
        IterativeTraverse<Integer> myList1 = new IterativeTraverse<>(countList);
        System.out.println(myList1.toString());
    }

}
