public class Student extends Person {
    protected int intellegence;
    protected int motivation;

    public Student(String firstName, String lastName,
                    int intellegence, int motivation) {
        super(firstName, lastName);
        this.intellegence = intellegence;
        this.motivation = motivation;
    }
    @Override
    public String toString() {
        return super.toString() + "My intellegence is " + intellegence + "/10"
                + " and my motivation is " + motivation + "/10. "
                + "I'm stressed out.";
    }
}