public class Competitor {
    protected String name;
    protected String surname;
    protected int age;
    protected String degree;
    protected String bio;

    public Competitor(String name, String surname, int age, String degree, String bio) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.degree = degree;
        this.bio = bio;
    }

    private boolean containsGenZSlang() {
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

    private boolean isSpecialLength() {
        int length = 42;
        return bio.length() == length;
    }

    private boolean isPalindrome(String str) {
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
                            //found a palindrome
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

    private boolean containsPalindrome() {
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

    public boolean canAccept() {

        if(containsPalindrome() || isSpecialLength() || containsGenZSlang())
        {
            return true;
        }
        return false;
    }

    public int compete() {
        return -1;
    }

    @Override
    public String toString() {
        return name + " " + surname + ", " + age + " (" + degree + ")";
    }

}
