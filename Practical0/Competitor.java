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

    }

    private boolean isSpecialLength() {

    }

    private boolean isPalindrome(String str) {

    }

    private boolean containsPalindrome() {

    }

    public boolean canAccept() {

    }

    public int compete() {
        return -1;
    }

    @Override
    public String toString() {
        return name + " " + surname + ", " + age + " (" + degree + ")";
    }

}
