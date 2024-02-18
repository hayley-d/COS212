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

    private boolean isSpecialLength() {
        int length = 42;
        return bio.length() == length;
    }

    private boolean isPalindrome(String str) {
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

    private boolean containsPalindrome() {
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
