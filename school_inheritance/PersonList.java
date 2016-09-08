public class PersonList {
    private Person[] people;
    private int count;

    public PersonList(int maxSize) {
        this.people = new Person[maxSize];
    }

    public void listPeople() {
        for (int x = 0; x < people.length; x++) {
            if (people[x] != null) {
                System.out.println(people[x]);
            }
        }
    }

    public void add(Person p) {
        boolean isSearching = true;
        for (int x = 0; x < people.length && isSearching; x++) {
            if (people[x] == null) {
                people[x] = p;
                isSearching = false;
            }
        }
    }
}