public class ClassOrganiser extends Competitor implements Cheerable, Comparable<ClassOrganiser> {

    private String[] earlyLectures;
    private String[] fridayLectures;

    private int score;

    public ClassOrganiser(String name, String surname, int age, String degree, String bio, String result) {
        super(name, surname, age, degree, bio); // Call the constructor of the superclass
        this.score = 0;
        String[] lectureTypes = result.split("$");
        String[] early = lectureTypes[0].split(",");
        String[] friday = lectureTypes[1].split(",");

        earlyLectures = new String[early.length];
        fridayLectures = new String[friday.length];

        for(int i = 0; i < early.length;i++)
        {
            earlyLectures[i] = early[i];
        }

        for(int i = 0; i < friday.length;i++)
        {
            fridayLectures[i] = friday[i];
        }

        if(earlyLectures.length >= fridayLectures.length)
        {
            for(int i = 0; i < fridayLectures.length;i++)
            {
                if(earlyLectures[i].equals(fridayLectures[i]))
                {
                    score += 3;
                    earlyLectures[i] = "";
                    fridayLectures[i] = "";
                }
            }
        }
        else{
            for(int i = 0; i < earlyLectures.length;i++)
            {
                if(earlyLectures[i].equals(fridayLectures[i]))
                {
                    score += 3;
                    earlyLectures[i] = "";
                    fridayLectures[i] = "";
                }
            }
        }

        for(int i = 0; i < earlyLectures.length;i++)
        {
            if(!earlyLectures[i].equals(""))
            {
                score += 1;
            }
        }
        for(int i = 0; i < fridayLectures.length;i++)
        {
            if(!fridayLectures[i].equals(""))
            {
                score += 1;
            }
        }

    }

    @Override
    public String toString() {
        return "Class Registration Roulette("+score+"): "+name + " " + surname + ", " + age + " (" + degree + ")";
    }

    @Override
    public String cheer() {
        return "Go "+name+", go! You're the best at Class Registration Roulette!";
    }

    @Override
    public int compareTo(ClassOrganiser other) {
        // Compare based on distanceToClass
        return Integer.compare(other.score, this.score);
    }

    @Override
    public int compete()
    {
        return score;
    }

}
