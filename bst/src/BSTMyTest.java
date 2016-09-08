/**
 * Created by Bharath on 2/8/15.
 */
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class BSTMyTest {
    private class Person implements Comparable<Person> {
        private int age;
        private String name;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public boolean equals(Object other) {
            if (other == null) return false;
            if (this == other) return true;
            if (!(other instanceof Person)) return false;
            Person that = (Person) other;
            return this.name == that.name && this.age == that.age;
        }
        public int compareTo(Person other) {
            return this.age - other.age;
        }
        public String toString() {
            return name;
        }
    }

    private BST<Person> bst;

    @Before
    public void setup() {
        bst = new BST<>();
    }

    @Test
    public void testHeight() {
        bst.clear();
        Person bobby = new Person("Bobby", 21);
        Person suzie = new Person("Suzie", 18);
        Person haley = new Person("Haley", 22);
        Person vinny = new Person("Vinny", 16);
        Person billy = new Person("Billy", 20);
        Person susan = new Person("Susan", 17);
        Person katie = new Person("Katie", 23);
        Person vince = new Person("Vince", 19);


        assertEquals(-1, bst.height());
        bst.add(bobby);
        assertEquals(0, bst.height());
        bst.add(suzie);
        assertEquals(1, bst.height());
        bst.add(haley);
        assertEquals(1, bst.height());
        bst.add(vinny);
        assertEquals(2, bst.height());
        bst.add(billy);
        assertEquals(2, bst.height());
        bst.add(susan);
        assertEquals(3, bst.height());
        bst.add(katie);
        assertEquals(3, bst.height());
        bst.add(vince);
        assertEquals(3, bst.height());
    }

    @Test
    public void testDepth() {
        Person katie = new Person("Katie", 23);
        Person vinny = new Person("Vinny", 16);
        Person flynn = new Person("Flynn", 24);
        Person steve = new Person("Steve", 15);
        Person bobby = new Person("Bobby", 21);
        Person susan = new Person("Susan", 17);
        Person haley = new Person("Haley", 22);
        Person vince = new Person("Vince", 19);
        Person suzie = new Person("Suzie", 18);
        Person billy = new Person("Billy", 20);

        bst.add(katie);
        bst.add(vinny);
        bst.add(flynn);
        bst.add(steve);
        bst.add(bobby);
        bst.add(susan);
        bst.add(haley);
        bst.add(vince);
        bst.add(suzie);
        bst.add(billy);

        assertEquals(1, bst.depth(katie));
        assertEquals(2, bst.depth(vinny));
        assertEquals(2, bst.depth(flynn));
        assertEquals(3, bst.depth(steve));
        assertEquals(3, bst.depth(bobby));
        assertEquals(4, bst.depth(susan));
        assertEquals(4, bst.depth(haley));
        assertEquals(5, bst.depth(vince));
        assertEquals(6, bst.depth(suzie));
        assertEquals(6, bst.depth(billy));
        assertEquals(-1, bst.depth(new Person("John", 100)));
    }

    @Test
    public void testRemove() {
        Person bobby = new Person("Bobby", 21);
        Person suzie = new Person("Suzie", 18);
        Person haley = new Person("Haley", 22);
        Person vinny = new Person("Vinny", 16);
        Person billy = new Person("Billy", 20);
        Person susan = new Person("Susan", 17);
        Person katie = new Person("Katie", 23);
        Person vince = new Person("Vince", 19);


        bst.add(suzie);
        bst.add(haley);
        bst.add(bobby);
        bst.add(vinny);
        bst.add(billy);
        bst.add(katie);
        bst.add(vince);
        bst.add(susan);

        bst.remove(vinny);
        assertEquals(bst.getRoot().getLeft().getData(), susan);

    }

    @Test
    public void testRemove2() {

        /*
                                katie
                 vinny                        flynn
         steve            bobby
                      susan     haley
                         vince
                      suzie  billly
        */
        Person katie = new Person("Katie", 23);
        Person vinny = new Person("Vinny", 16);
        Person flynn = new Person("Flynn", 24);
        Person steve = new Person("Steve", 15);
        Person bobby = new Person("Bobby", 21);
        Person susan = new Person("Susan", 17);
        Person haley = new Person("Haley", 22);
        Person vince = new Person("Vince", 19);
        Person suzie = new Person("Suzie", 18);
        Person billy = new Person("Billy", 20);

        bst.add(katie);
        bst.add(vinny);
        bst.add(flynn);
        bst.add(steve);
        bst.add(bobby);
        bst.add(susan);
        bst.add(haley);
        bst.add(vince);
        bst.add(suzie);
        bst.add(billy);


        assertTrue(bst.contains(katie));
        assertTrue(bst.contains(vinny));
        assertTrue(bst.contains(flynn));
        assertTrue(bst.contains(steve));
        assertTrue(bst.contains(bobby));
        assertTrue(bst.contains(susan));
        assertTrue(bst.contains(haley));
        assertTrue(bst.contains(vince));
        assertTrue(bst.contains(suzie));
        assertTrue(bst.contains(billy));

        bst.remove(vinny);

//        System.out.println(bst.getRoot().getData()); //23
//        System.out.println(bst.getRoot().getLeft().getData()); //17
//        System.out.println(bst.getRoot().getRight().getData()); //24
//        System.out.println(bst.getRoot().getLeft().getLeft().getData()); //15
//        System.out.println(bst.getRoot().getLeft().getRight().getData()); //21
//        System.out.println(bst.getRoot().getLeft().getRight().getRight().getData()); //22
//        System.out.println(bst.getRoot().getLeft().getRight().getLeft().getData()); //19
//        System.out.println(bst.getRoot().getLeft().getRight().getLeft().getRight().getData()); //20
//        System.out.println(bst.getRoot().getLeft().getRight().getLeft().getLeft().getData()); //18
        List<Person> list = bst.inorder();
        for(Person p : list) {
            System.out.println(p.age);
        }

        assertTrue(bst.contains(katie));
        assertTrue(!bst.contains(vinny));
        assertTrue(bst.contains(flynn));
        assertTrue(bst.contains(steve));
        assertTrue(bst.contains(bobby));
        assertTrue(bst.contains(susan));
        assertTrue(bst.contains(haley));
        assertTrue(bst.contains(vince));
        assertTrue(bst.contains(suzie));
        assertTrue(bst.contains(billy));

        assertEquals(bst.getRoot().getLeft().getData(), susan);

        bst.remove(susan);

//        System.out.println(bst.getRoot().getData()); //23 - Katie
//        System.out.println(bst.getRoot().getLeft().getData()); //18 - Suzie
//        System.out.println(bst.getRoot().getRight().getData()); //24 - Flynn
//        System.out.println(bst.getRoot().getLeft().getLeft().getData()); //15 - Steve
//        System.out.println(bst.getRoot().getLeft().getRight().getData()); //21 - Bobby
//        System.out.println(bst.getRoot().getLeft().getRight().getRight().getData()); //22 - Haley
//        System.out.println(bst.getRoot().getLeft().getRight().getLeft().getData()); //19 - Vince
//        System.out.println(bst.getRoot().getLeft().getRight().getLeft().getRight().getData()); //20 - Billy

        assertTrue(bst.contains(katie));
        assertTrue(!bst.contains(vinny));
        assertTrue(bst.contains(flynn));
        assertTrue(bst.contains(steve));
        assertTrue(bst.contains(bobby));
        assertTrue(!bst.contains(susan));
        assertTrue(bst.contains(haley));
        assertTrue(bst.contains(vince));
        assertTrue(bst.contains(suzie));
        assertTrue(bst.contains(billy));

        assertEquals(bst.getRoot().getLeft().getData(), suzie);

        bst.remove(steve);
//        System.out.print(bst.getRoot().getLeft().getData());
//        System.out.println(bst.contains(steve));
//        System.out.println(bst.getRoot().getData()); //23 - Katie
//        System.out.println(bst.getRoot().getLeft().getData()); //18 - Suzie
//        System.out.println(bst.getRoot().getRight().getData()); //24 - Flynn
//        System.out.println(bst.getRoot().getLeft().getLeft()); //null
//        System.out.println(bst.getRoot().getLeft().getRight().getData()); //21 - Bobby
//        System.out.println(bst.getRoot().getLeft().getRight().getRight().getData()); //22 - Haley
//        System.out.println(bst.getRoot().getLeft().getRight().getLeft().getData()); //19 - Vince
//        System.out.println(bst.getRoot().getLeft().getRight().getLeft().getRight().getData()); //20 - Billy

        assertTrue(bst.contains(katie));
        assertTrue(!bst.contains(vinny));
        assertTrue(bst.contains(flynn));
        assertTrue(!bst.contains(steve));
        assertTrue(bst.contains(bobby));
        assertTrue(!bst.contains(susan));
        assertTrue(bst.contains(haley));
        assertTrue(bst.contains(vince));
        assertTrue(bst.contains(suzie));
        assertTrue(bst.contains(billy));

        assertEquals(bst.getRoot().getLeft().getLeft(), null);

        bst.remove(suzie);

        System.out.println(bst.getRoot().getData()); //23 - Katie
        System.out.println(bst.getRoot().getLeft().getData()); //19 - Vince
        System.out.println(bst.getRoot().getRight().getData()); //24 - Flynn
        System.out.println(bst.getRoot().getLeft().getLeft()); //null
        System.out.println(bst.getRoot().getLeft().getRight().getData()); //21 - Bobby
        // System.out.println(bst.getRoot().getLeft().getRight().getRight().getData()); //22 - Haley
        // System.out.println(bst.getRoot().getLeft().getRight().getLeft().getData()); //20 - Billy

        assertTrue(bst.contains(katie));
        assertTrue(!bst.contains(vinny));
        assertTrue(bst.contains(flynn));
        assertTrue(!bst.contains(steve));
        assertTrue(bst.contains(bobby));
        assertTrue(!bst.contains(susan));
        assertTrue(bst.contains(haley));
        assertTrue(bst.contains(vince));
        assertTrue(!bst.contains(suzie));
        assertTrue(bst.contains(billy));

        assertEquals(bst.getRoot().getLeft().getData(), bobby);
    }

}