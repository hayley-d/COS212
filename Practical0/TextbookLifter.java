public class TextbookLifter extends Competitor implements Cheerable, Comparable<TextbookLifter> {

    private int[] normalBooks;
    private int[] outdatedBooks;

    private int score;

    public TextbookLifter(String name, String surname, int age, String degree, String bio, String result) {
        super(name, surname, age, degree, bio); // Call the constructor of the superclass
        if(result.length() > 2)
        {
            String[] bookTypes = result.split("\\$");
            String[] nBooks = bookTypes[0].split(",");
            normalBooks = new int[nBooks.length];
            for(int i = 0; i < nBooks.length;i++)
            {
                normalBooks[i] = Integer.parseInt(nBooks[i].replace("kg", ""));
            }

            if(bookTypes.length > 1)
            {
                String[] oBooks = bookTypes[1].split(",");
                outdatedBooks = new int[oBooks.length];
                for(int i = 0; i < oBooks.length;i++)
                {
                    outdatedBooks[i] = Integer.parseInt(oBooks[i].replace("kg", ""));
                }
            }
            else{
                outdatedBooks = new int[0];
            }
        }
        else{
            outdatedBooks = new int[0];
            normalBooks = new int[0];
        }

        score = 0;

        //calculate the score
        for(int weight: normalBooks)
        {
            score += weight;
        }

        for(int weight: outdatedBooks)
        {
            score += (2*weight);
        }
    }

    @Override
    public String toString() {
        return "Textbook Weightlifting("+score+"): "+name + " " + surname + ", " + age + " (" + degree + ")";
    }

    @Override
    public String cheer() {
        return "Go "+name+", go! You're the best at Textbook Weightlifting!";
    }

    @Override
    public int compareTo(TextbookLifter other) {
        // Compare based on distanceToClass
        return Integer.compare(this.score, other.score);
    }

    @Override
    public int compete()
    {
        return score;
    }

}
