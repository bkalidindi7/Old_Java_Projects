public class GraduateStudent extends Student {
    public GraduateStudent(String firstName, String lastName,
                           int intellegence, int motivation) {
        super(firstName, lastName, intellegence, motivation);
    }
    @Override
    public String toString() {
        return super.toString() + " AND I'm broke.";
    }
}