public class Professor extends Person {
    private int rateMyProfessorRating;
    private double averageGPA;
    public Professor(String firstName, String lastName,
                     int rateMyProfessorRating, double averageGPA) {
        super(firstName, lastName);
        this.rateMyProfessorRating = rateMyProfessorRating;
        this.averageGPA = averageGPA;
    }
    @Override
    public String toString() {
        return super.toString() + "My Rate My Professor rating is "
                + rateMyProfessorRating + "/10" + " and my average GPA is "
                + averageGPA + "/4.00. I really wish my students would stop "
                + "emailing me so much.";
    }
}