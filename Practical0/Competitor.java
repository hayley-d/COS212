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
        if(containsPalindrome() && isSpecialLength() && containsGenZSlang())
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
