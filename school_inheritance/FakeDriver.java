public class FakeDriver {
    public static void main(String[] args) {
        PersonList list = new PersonList(10);
        list.add(new Professor("Sample1", "Data1", 5, 5));
        list.add(new Student("Sample2", "Data2", 10, 10));
        list.add(new UndergraduateStudent("Sample3", "Data3", 10, 10));
        list.add(new GraduateStudent("Sample4", "Data4", 5, 5));
        list.listPeople();
    }
}