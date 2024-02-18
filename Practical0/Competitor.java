public class Competitor {
    protected String name;
    private String surname;
    private int age;
    private String degree;
    private String bio;

    public Competitor(String name, String surname, int age, String degree, String bio) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.degree = degree;
        this.bio = bio;
    }

    private boolean containsGenZSlang() {
        String phrase = "no cap";
        bio = bio.toLowerCase();
        return bio.contains(phrase);
    }

    private boolean isSpecialLength() {
        int length = 42;
        return bio.length() == length;
    }

    private boolean isPalindrome(String str) {
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

    private boolean containsPalindrome() {
        String[] words = bio.split("\\s+"); // Split the sentence into words
        for (String word : words) {
            if (isPalindrome(word)) {
                return true;
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
