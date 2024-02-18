import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Task1And2 {

    public boolean containsGenZSlang(String bio) {
        String phrase = "no cap";
        bio = bio.toLowerCase();
        return bio.contains(phrase);
    }

    public boolean isSpecialLength(String bio) {
        int length = 42;
        return bio.length() == length;
    }

    public boolean isPalindrome(String str) {
        str = str.toLowerCase();
        char[] mycharArr = str.toCharArray();
        int end = str.length()-1;
        for (int i = 0; i < end; i++, end--) {
           if(mycharArr[i] != mycharArr[end])
           {
               return false;
           }
        }
        return true;
    }

    public boolean containsPalindrome(String bio) {
        String[] words = bio.split("\\s+"); // Split the sentence into words
        for (String word : words) {
            if (isPalindrome(word)) {
                return true;
            }
        }
        return false;
    }

    public boolean canAccept(String bio) {
        if(containsPalindrome(bio) && isSpecialLength(bio) && containsGenZSlang(bio))
        {
            return true;
        }
        return false;
    }

    public String listApplicantStatuses(String fileName) {
        String result = "";
        try{
            Scanner scanner = new Scanner(new FileReader(fileName));
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] fields = line.split("#");
                if(fields.length == 7)
                {
                    String name = fields[0];
                    String surname = fields[1];
                    int age = Integer.parseInt(fields[2]);
                    String degree = fields[3];
                    String bio = fields[4];
                    String event = fields[5];
                    String eventResult = fields[6];

                    if(canAccept(bio))
                    {
                        result += name +" " + surname +": "+"accepted\n";
                    }
                    else{
                        result += name +" " + surname +": "+"not accepted\n";
                    }
                }
            }
            return result;
        }
        catch(FileNotFoundException e)
        {
            
        }
        return result;
    }

}
