public class UndergraduateStudent extends Student {
    public UndergraduateStudent(String firstName, String lastName,
                                int intellegence, int motivation) {
        super(firstName, lastName, intellegence, motivation);
    }
    @Override
    public String toString() {
        return super.toString() + " I'm going home this weekend to get "
                + "laundry done; talk about clutch.";
    }
}