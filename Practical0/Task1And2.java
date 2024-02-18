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

    private boolean checkPalin(String substring)
    {
        if(substring.equals("") || substring.length() < 2)
        {
            return false;
        }
        int end = substring.length()-1;
        char[] charArr = substring.toCharArray();
        for(int i = 0; i < end; i++,end--)
        {
            if(charArr[i] != charArr[end])
            {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String str) {
        String lowerSen = str.replaceAll("\\s+", "").toLowerCase();//remove spaces and make lowercase

        for(int i = 0; i < lowerSen.length()-1;i++)
        {
            for(int j = i+2;j <= lowerSen.length();j++)
            {
                String subString = lowerSen.substring(i,j);
                if(checkPalin(subString))
                {
                    return true;
                }
            }
        }
        return true;
    }

    public boolean containsPalindrome(String bio) {
        if(bio.equals("") || bio.length() < 2)
        {
            return false;
        }
        bio = bio.replaceAll("\\s+", "").toLowerCase(); //remove the spaces and convert to lower case

        for (int i = 0; i < bio.length() - 1; i++) {
            //for every letter in bio
            for (int j = i + 2; j <= bio.length(); j++) {
                String substring = bio.substring(i, j);
                if (isPalindrome(substring)) {
                    return true;
                }
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
            // Remove the last character if it is a newline
            if (!result.isEmpty() && result.charAt(result.length() - 1) == '\n') {
                result = result.substring(0, result.length() - 1);
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
