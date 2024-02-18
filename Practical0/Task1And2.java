import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Task1And2 {

    public boolean containsGenZSlang(String bio) {
        String phrase = "no cap";
        String lowercaseBio = bio.toLowerCase();
        int index = lowercaseBio.indexOf(phrase);
        // Check if the phrase is found in the bio (ignoring case) and accounting for extra whitespace
        while (index != -1) {
            if ((index == 0 || !Character.isLetter(lowercaseBio.charAt(index - 1))) &&
                    (index + phrase.length() == lowercaseBio.length() || !Character.isLetter(lowercaseBio.charAt(index + phrase.length())))) {
                return true;
            }
            index = lowercaseBio.indexOf(phrase, index + 1);
        }

        return false;
    }

    public boolean isSpecialLength(String bio) {
        int length = 42;
        return bio.length() == length;
    }

    public boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean containsPalindrome(String bio) {
        String lowerBio = bio.toLowerCase();
        int beginCharacterIndex = 0;
        int endCharacterIndex = 0;
        while(beginCharacterIndex < lowerBio.length())
        {
            while(endCharacterIndex < lowerBio.length())
            {
                String substringToCheck = lowerBio.substring(beginCharacterIndex,endCharacterIndex);
                if(isPalindrome(substringToCheck))
                {
                    return true;
                }
                endCharacterIndex = endCharacterIndex+1;
            }
            beginCharacterIndex = beginCharacterIndex +1;
            endCharacterIndex = beginCharacterIndex;
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
            System.out.println("File not found");
        }
        return result;
    }

}
