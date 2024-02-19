import java.sql.SQLOutput;

public class App {
    public static void main(String[] args)
    {
        readFromFileTest();
    }

    public static void testTask1(){
        String bio = "Hi I am sad";
        Task1And2 task = new Task1And2();

        System.out.println("Testing Contains Slang....\n");
        if(!task.containsGenZSlang(bio))
        {
            System.out.println("Correct");
        }
        else{
            System.out.println("Wrong");
        }
        bio = "";
        if(!task.containsGenZSlang(bio))
        {
            System.out.println("Correct");
        }
        else{
            System.out.println("Wrong");
        }
        bio = "I racecar no cap";
        if(task.containsGenZSlang(bio))
        {
            System.out.println("Correct");
        }
        else{
            System.out.println("Wrong");
        }
        System.out.println("Testing Contains slang Complete\n");

        System.out.println("Testing is special length....");
        String char42 = "Loremipsumdolorsitametconsecteturadipisciw";
        System.out.println(char42.length());
        if(task.isSpecialLength(char42))
        {
            System.out.println("Correct");
        }
        else{
            System.out.println("Wrong");
        }
        if(!task.isSpecialLength(""))
        {
            System.out.println("Correct");
        }
        else{
            System.out.println("Wrong");
        }
        if(!task.isSpecialLength("Hi my name jef"))
        {
            System.out.println("Correct");
        }
        else{
            System.out.println("Wrong");
        }
        System.out.println("Testing special length Complete\n");

        System.out.println("Testing isPalindrome...");
        String palindrome = "racecar";
        if(task.isPalindrome(palindrome))
        {
            System.out.println("Correct");
        }
        else{
            System.out.println("Wrong");
        }
        bio = "I racecar no cap";
        if(task.containsPalindrome(bio))
        {
            System.out.println("Correct");
        }
        else{
            System.out.println("Wrong");
        }
        if(!task.isPalindrome("was"))
        {
            System.out.println("Correct");
        }
        else{
            System.out.println("Wrong");
        }

        if(!task.containsPalindrome("I was hungry"))
        {
            System.out.println("Correct");
        }
        else{
            System.out.println("Wrong hungry");
        }
        if(task.containsPalindrome("no cap Racecars ! qwertioplkjhgfdsazxcvbnm"))
        {
            System.out.println("Correct THISONE");
        }
        else{
            System.out.println("Wrong");
        }
        if(!task.isPalindrome(""))
        {
            System.out.println("Correct");
        }
        else{
            System.out.println("Wrong");
        }
        System.out.println("Testing palindrome Complete\n");
    }

    public static void testList()
    {
        OrderedList<Integer> myList = new OrderedList<>();
        for(int i = 11; i >= 1; i--)
        {
            myList.insert(i);
            System.out.println(myList);
        }
    }

    public static void testParking(){
        String bio = "no cap manamwr ! qwrertioplkjhgfdsazxcvbnm";
        System.out.println(bio.length());
        System.out.println(containsGenZSlang("I Love Carrots! no cap"));
        System.out.println(isPalindrome(bio));
        ParkingHunter hunter1 = new ParkingHunter("Hayley","Dodkins",21,"BSc","no cap manamwr ! qwrertioplkjhgfdsazxcvbnm","20m");
        ParkingHunter hunter2 = new ParkingHunter("Bunny","Dodkins",5,"BScIKS","I Love Carrots! no cap","100m");
        ParkingHunter hunter3 = new ParkingHunter("Tom","Nook",78,"Hospitality","I Love Money!","40m");
        System.out.println(hunter1);
        System.out.println(hunter2);
        System.out.println(hunter3);
        OrderedList<ParkingHunter> hunterList = new OrderedList<>();
        if(hunter1.canAccept())
        {
            hunterList.insert(hunter1);
        }
        System.out.println(hunter2.canAccept());
        if(hunter2.canAccept())
        {
            hunterList.insert(hunter2);
        }
        if(hunter3.canAccept())
        {
            hunterList.insert(hunter3);
        }
        System.out.println(hunterList);
        System.out.println(hunter1.cheer());
        System.out.println(hunter2.cheer());
        System.out.println(hunter3.cheer());

        ParkingHunter blankHunter = new ParkingHunter("Joe","Goldburg",21,"BSc","no cap Racecar ! qwrertioplkjhgfdsazxcvbnm","");
        System.out.println(blankHunter.cheer());
        System.out.println(blankHunter);
        hunterList.insert(blankHunter);
        System.out.println(hunterList);
    }

    public static void testLifter()
    {
        TextbookLifter hunter1 = new TextbookLifter("Hayley","Dodkins",21,"BSc","no cap Racecar ! qwrertioplkjhgfdsazxcvbnm","2kg,1kg,1kg$2kg,30kg,4kg");
        TextbookLifter hunter2 = new TextbookLifter("Bunny","Dodkins",5,"BScIKS","I Love Carrots!","2kg,1kg,1kg$1kg,1kg,1kg");
        TextbookLifter hunter3 = new TextbookLifter("Tom","Nook",78,"Hospitality","I Love Money!","2kg,1kg,1kg$2kg,3kg,4kg");
        System.out.println(hunter1);
        System.out.println(hunter2);
        System.out.println(hunter3);
        OrderedList<TextbookLifter> hunterList = new OrderedList<>();
        hunterList.insert(hunter1);
        hunterList.insert(hunter2);
        hunterList.insert(hunter3);
        System.out.println(hunterList);
    }

    public static void organizerTest(){
        ClassOrganiser hunter1 = new ClassOrganiser("Hayley","Dodkins",21,"BSc","no cap Racecar ! qwrertioplkjhgfdsazxcvbnm","COS212,COS110,COS216$COS110,COS221");
        ClassOrganiser hunter2 = new ClassOrganiser("Bunny","Dodkins",5,"BScIKS","I Love Carrots!","COS212,COS110,COS216$COS110,COS221,COS234,COS234");
        ClassOrganiser hunter3 = new ClassOrganiser("Tom","Nook",78,"Hospitality","I Love Money!","COS212,COS110,COS221,COS216$COS110,COS221");
        System.out.println(hunter1);
        System.out.println(hunter2);
        System.out.println(hunter3);
        OrderedList<ClassOrganiser> hunterList = new OrderedList<>();
        hunterList.insert(hunter1);
        hunterList.insert(hunter2);
        hunterList.insert(hunter3);
        System.out.println(hunterList);
    }

    public static void readFromFileTest(){
        Task1And2 newTask = new Task1And2();

        System.out.println(newTask.listApplicantStatuses("C:\\Users\\User-PC\\Dropbox\\COS 212 2024\\Practicals\\Practical 0\\Practical0\\src\\competitors.txt"));
    }



    public static boolean isPalindrome(String str) {
        //Step 1: remove the spaces
        String sen = str.replaceAll("\\s+", "").toLowerCase();//remove spaces and make lowercase
        char[] charArr = sen.toCharArray();
        int length = charArr.length;
        if(length > 2)
        {
            int startIndex = 0;
            int endIndex = 2;
            while(startIndex< length-1)
            {
                while(endIndex < length-1)
                {
                    if(charArr[startIndex] == charArr[endIndex])
                    {
                        int startTemp = startIndex;
                        //move inwards
                        while(endIndex > startIndex)
                        {
                            if(charArr[endIndex] != charArr[startIndex])
                            {
                                break;
                            }
                            startIndex += 1;
                            endIndex -= 1;
                        }
                        if(endIndex == startIndex)
                        {
                            //found a palindrom
                            return true;
                        }
                        else{
                            startIndex = startTemp;
                            break;
                        }
                    }
                    endIndex += 1;
                }
                startIndex += 1;
                endIndex = startIndex + 2;
            }
        }
        return false;
    }

    private static boolean containsGenZSlang(String bio) {
        if(bio.length()<4)
        {
            return false;
        }
        String phrase = "no cap";

        int index = bio.indexOf(phrase);
        // Check if the phrase is found in the bio (ignoring case) and accounting for extra whitespace
        while (index != -1) {
            if ((index == 0 || !Character.isLetter(bio.charAt(index - 1))) &&
                    (index + phrase.length() == bio.length() || !Character.isLetter(bio.charAt(index + phrase.length())))) {
                return true;
            }
            index = bio.indexOf(phrase, index + 1);
        }

        return false;
    }



}
