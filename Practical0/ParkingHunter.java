public class ParkingHunter extends Competitor implements Cheerable, Comparable<ParkingHunter> {

    private int distanceToClass;

    public ParkingHunter(String name, String surname, int age, String degree, String bio, String result) {
        super(name, surname, age, degree, bio); // Call the constructor of the superclass
        String distanceNumber = result.replace("m", "");
        int distance = Integer.parseInt(distanceNumber);
        this.distanceToClass = distance;
    }

    @Override
    public String toString() {
        return "Parking Spot Hunting("+distanceToClass+"): "+name + " " + surname + ", " + age + " (" + degree + ")";
    }

    @Override
    public int compete()
    {
        return distanceToClass;
    }

    @Override
    public String cheer() {
        return "Go "+name+", go! You're the best at Parking Spot Hunting!";
    }

    @Override
    public int compareTo(ParkingHunter other) {
        // Compare based on distanceToClass
        return Integer.compare(other.distanceToClass, this.distanceToClass);
    }
}
