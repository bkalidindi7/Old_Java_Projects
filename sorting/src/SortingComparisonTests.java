import java.util.Comparator;
import java.util.Random;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class SortingComparisonTests {
    Person[] smallList;
    Person[] bigList;
    Person[] smallBest;
    Person[] bigBest;
    Person[] smallWorst;
    Person[] bigWorst;
    private static final int SMALL = 1000;
    private static final int BIG = 8000;
    private static final int NSQUARED = BIG / SMALL * BIG / SMALL;
    private static final int NLOGN = (int) Math.round(BIG * Math.log(BIG)
            / (SMALL * Math.log(SMALL)));
    private static final int N = BIG / SMALL;
    private static final int ERROR_NSQ = NSQUARED / 10 + 1;
    private static final int ERROR_NLN = NLOGN / 10 + 1;
    private static final int ERROR_N = N / 10 + 1;
    public static int comparisons;
    private static class Person {
        private int number;
        public Person(int number) {
            this.number = number;
        }
        public boolean equals(Object other) {
            return other instanceof Person &&
                    ((Person) other).number == this.number;
        }
        public static Comparator<Person> compareNums() {
            return new Comparator<Person>() {
                public int compare(Person p1, Person p2) {
                    comparisons++;
                    return p1.number - p2.number;
                }
            };
        }
    }

    @BeforeClass
    public static void info() {
        System.out.println("Expected ratio in NLogN sorts: " + NLOGN);
        System.out.println("Expected ratio in N^2 sorts: " + NSQUARED);
        System.out.println("Error in NLogN sorts: " + ERROR_NLN);
        System.out.println("Error in N^2 sorts: " + ERROR_NSQ);
    }

    @Before
    public void setup() {
        smallList = new Person[SMALL];
        bigList = new Person[BIG];
        smallBest = new Person[SMALL];
        bigBest = new Person[BIG];
        smallWorst = new Person[SMALL];
        bigWorst = new Person[BIG];
        Random rand = new Random();
        for (int i = 0; i < SMALL; i++) {
            smallList[i] = new Person(rand.nextInt(100000));
            smallBest[i] = new Person(i);
            smallWorst[i] = new Person(SMALL - 1 - i);
        }
        for (int i = 0; i < BIG; i++) {
            bigList[i] = new Person(rand.nextInt(100000));
            bigBest[i] = new Person(i);
            bigWorst[i] = new Person(BIG - 1 - i);
        }
        comparisons = 0;

    }


    @Test
    public void testBubble() {
        Sorting.bubblesort(smallList, Person.compareNums());
        int smallComps = comparisons;
        comparisons = 0;
        Sorting.bubblesort(bigList, Person.compareNums());
        int bigComps = comparisons;
        for (int i = 0; i < SMALL - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(smallList[i], smallList[i+1]) <= 0);
        }
        for (int i = 0; i < BIG - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(bigList[i], bigList[i+1]) <= 0);
        }
        System.out.println("Bubble:");
        System.out.println("Comparisons in small: " + smallComps
                + "\nComparisons in big: " + bigComps);
        System.out.println("Ratio: "  + bigComps / smallComps);
        int ratio = bigComps / smallComps;
        assertTrue(ratio >= NSQUARED - ERROR_NSQ
                && ratio <= NSQUARED + ERROR_NSQ);
    }

    @Test
    public void testBubbleBest() {
        Sorting.bubblesort(smallBest, Person.compareNums());
        int smallComps = comparisons;
        comparisons = 0;
        Sorting.bubblesort(bigBest, Person.compareNums());
        int bigComps = comparisons;
        for (int i = 0; i < SMALL - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(smallBest[i], smallBest[i+1]) <= 0);
        }
        for (int i = 0; i < BIG - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(bigBest[i], bigBest[i+1]) <= 0);
        }
        System.out.println("Bubble Best Case:");
        System.out.println("Comparisons in small: " + smallComps
                + "\nComparisons in big: " + bigComps);
        System.out.println("Ratio: "  + bigComps / smallComps);
        int ratio = bigComps / smallComps;
        assertEquals(N, ratio);
        //BONUS
        assertEquals(SMALL - 1, smallComps);
        assertEquals(BIG - 1, bigComps);
    }

    @Test
    public void testBubbleWorst() {
        Sorting.bubblesort(smallWorst, Person.compareNums());
        int smallComps = comparisons;
        comparisons = 0;
        Sorting.bubblesort(bigWorst, Person.compareNums());
        int bigComps = comparisons;
        for (int i = 0; i < SMALL - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(smallWorst[i], smallWorst[i+1]) <= 0);
        }
        for (int i = 0; i < BIG - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(bigWorst[i], bigWorst[i+1]) <= 0);
        }
        System.out.println("Bubble Worst:");
        System.out.println("Comparisons in small: " + smallComps
                + "\nComparisons in big: " + bigComps);
        System.out.println("Ratio: "  + bigComps / smallComps);
        int ratio = bigComps / smallComps;
        assertEquals(ratio, NSQUARED);
        //Bonus
        assertTrue(SMALL * (SMALL - 1) >= smallComps);
        assertTrue(BIG * (BIG - 1) >= bigComps);

        //For those who optimized their bubble sort ;)
        /*
        assertEquals(SMALL * (SMALL - 1) / 2, smallComps);
        assertEquals(BIG * (BIG - 1) / 2, bigComps);
        */
    }

    @Test
    public void testInsertWorst() {
        Sorting.insertionsort(smallWorst, Person.compareNums());
        int smallComps = comparisons;
        comparisons = 0;
        Sorting.insertionsort(bigWorst, Person.compareNums());
        int bigComps = comparisons;
        for (int i = 0; i < SMALL - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(smallWorst[i], smallWorst[i+1]) <= 0);
        }
        for (int i = 0; i < BIG - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(bigWorst[i], bigWorst[i+1]) <= 0);
        }
        System.out.println("Insertion Worst:");
        System.out.println("Comparisons in small: " + smallComps
                + "\nComparisons in big: " + bigComps);
        System.out.println("Ratio: "  + bigComps / smallComps);
        int ratio = bigComps / smallComps;
        assertEquals(ratio, NSQUARED);
        //Bonus
        assertEquals(SMALL * (SMALL - 1) / 2, smallComps);
        assertEquals(BIG * (BIG - 1) / 2, bigComps);
    }

    @Test
    public void testInsert() {
        Sorting.insertionsort(smallList, Person.compareNums());
        int smallComps = comparisons;
        comparisons = 0;
        Sorting.insertionsort(bigList, Person.compareNums());
        int bigComps = comparisons;
        for (int i = 0; i < SMALL - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(smallList[i], smallList[i+1]) <= 0);
        }
        for (int i = 0; i < BIG - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(bigList[i], bigList[i+1]) <= 0);
        }
        System.out.println("Insertion:");
        System.out.println("Comparisons in small: " + smallComps
                + "\nComparisons in big: " + bigComps);
        System.out.println("Ratio: "  + (bigComps / smallComps));
        int ratio = bigComps / smallComps;
        assertTrue(ratio >= NSQUARED - ERROR_NSQ
                && ratio <= NSQUARED + ERROR_NSQ);
    }

    @Test
    public void testInsertBest() {
        Sorting.insertionsort(smallBest, Person.compareNums());
        int smallComps = comparisons;
        comparisons = 0;
        Sorting.insertionsort(bigBest, Person.compareNums());
        int bigComps = comparisons;
        for (int i = 0; i < SMALL - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(smallBest[i], smallBest[i+1]) <= 0);
        }
        for (int i = 0; i < BIG - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(bigBest[i], bigBest[i+1]) <= 0);
        }
        System.out.println("Insert Best Case:");
        System.out.println("Comparisons in small: " + smallComps
                + "\nComparisons in big: " + bigComps);
        System.out.println("Ratio: "  + bigComps / smallComps);
        int ratio = bigComps / smallComps;
        assertEquals(ratio, N);
        //BONUS
        assertEquals(SMALL - 1, smallComps);
        assertEquals(BIG - 1, bigComps);
    }

    @Test
    public void testMerge() {
        Sorting.mergesort(smallList, Person.compareNums());
        int smallComps = comparisons;
        comparisons = 0;
        Sorting.mergesort(bigList, Person.compareNums());
        int bigComps = comparisons;
        for (int i = 0; i < SMALL - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(smallList[i], smallList[i+1]) <= 0);
        }
        for (int i = 0; i < BIG - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(bigList[i], bigList[i+1]) <= 0);
        }
        System.out.println("Merge:");
        System.out.println("Comparisons in small: " + smallComps
                + "\nComparisons in big: " + bigComps);
        System.out.println("Ratio: "  + (bigComps / smallComps));
        int ratio = bigComps / smallComps;
        assertTrue(ratio >= NLOGN - ERROR_NLN
                && ratio <= NLOGN + ERROR_NLN);
    }

    @Test
    public void testMergeBest() {
        Sorting.mergesort(smallBest, Person.compareNums());
        int smallComps = comparisons;
        comparisons = 0;
        Sorting.mergesort(bigBest, Person.compareNums());
        int bigComps = comparisons;
        for (int i = 0; i < SMALL - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(smallBest[i], smallBest[i+1]) <= 0);
        }
        for (int i = 0; i < BIG - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(bigBest[i], bigBest[i+1]) <= 0);
        }
        System.out.println("Merge Best:");
        System.out.println("Comparisons in small: " + smallComps
                + "\nComparisons in big: " + bigComps);
        System.out.println("Ratio: "  + (bigComps / smallComps));
        int ratio = bigComps / smallComps;
        assertTrue(ratio >= NLOGN - ERROR_NLN
                && ratio <= NLOGN + ERROR_NLN);
    }

    @Test
    public void testQuick() {
        Sorting.quicksort(smallList, Person.compareNums(), new Random());
        int smallComps = comparisons;
        comparisons = 0;
        Sorting.quicksort(bigList, Person.compareNums(), new Random());
        int bigComps = comparisons;
        for (int i = 0; i < SMALL - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(smallList[i], smallList[i+1]) <= 0);
        }
        for (int i = 0; i < BIG - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(bigList[i], bigList[i+1]) <= 0);
        }
        System.out.println("Quick:");
        System.out.println("Comparisons in small: " + smallComps
                + "\nComparisons in big: " + bigComps);
        System.out.println("Ratio: "  + (bigComps / smallComps));
        int ratio = bigComps / smallComps;
        assertTrue(ratio >= NLOGN - ERROR_NLN
                && ratio <= NLOGN + ERROR_NLN);
    }

    @Test
    public void testQuickBest() {
        Sorting.quicksort(smallBest, Person.compareNums(), new Random());
        int smallComps = comparisons;
        comparisons = 0;
        Sorting.quicksort(bigBest, Person.compareNums(), new Random());
        int bigComps = comparisons;
        for (int i = 0; i < SMALL - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(smallBest[i], smallBest[i+1]) <= 0);
        }
        for (int i = 0; i < BIG - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(bigBest[i], bigBest[i+1]) <= 0);
        }
        System.out.println("Quick Best:");
        System.out.println("Comparisons in small: " + smallComps
                + "\nComparisons in big: " + bigComps);
        System.out.println("Ratio: "  + (bigComps / smallComps));
        int ratio = bigComps / smallComps;
        assertTrue(ratio >= NLOGN - ERROR_NLN
                && ratio <= NLOGN + ERROR_NLN);
    }

    @Test
    public void testShell() {
        Sorting.shellsort(smallList, Person.compareNums());
        int smallComps = comparisons;
        comparisons = 0;
        Sorting.shellsort(bigList, Person.compareNums());
        int bigComps = comparisons;
        for (int i = 0; i < SMALL - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(smallList[i], smallList[i+1]) <= 0);
        }
        for (int i = 0; i < BIG - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(bigList[i], bigList[i+1]) <= 0);
        }
        System.out.println("Shell:");
        System.out.println("Comparisons in small: " + smallComps
                + "\nComparisons in big: " + bigComps);
        System.out.println("Ratio: "  + (bigComps / smallComps));
        int ratio = bigComps / smallComps;
        assertTrue(ratio >= NLOGN - 5 * ERROR_NLN
                && ratio <= NLOGN + 5 * ERROR_NLN);
    }

    @Test
    public void testShellBest() {
        Sorting.shellsort(smallBest, Person.compareNums());
        int smallComps = comparisons;
        comparisons = 0;
        Sorting.shellsort(bigBest, Person.compareNums());
        int bigComps = comparisons;
        for (int i = 0; i < SMALL - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(smallBest[i], smallBest[i+1]) <= 0);
        }
        for (int i = 0; i < BIG - 1; i++) {
            assertTrue(Person.compareNums()
                    .compare(bigBest[i], bigBest[i+1]) <= 0);
        }
        System.out.println("Shell Best Case:");
        System.out.println("Comparisons in small: " + smallComps
                + "\nComparisons in big: " + bigComps);
        System.out.println("Ratio: "  + bigComps / smallComps);
        int ratio = bigComps / smallComps;
        assertTrue(ratio >= NLOGN - ERROR_NLN
                && ratio <= NLOGN + ERROR_NLN);
    }

    @Test
    public void testRadix() {
        System.out.println("Radix Test");
        final int SMALL_BOUND = 6;
        final int BIG_BOUND = 9;
        Random rand = new Random();
        int[] smallBeforeInts = new int[SMALL];
        int[] smallInts = new int[SMALL];
        int[] smallAfter;
        int[] bigBeforeInts = new int[BIG];
        int[] bigInts = new int[BIG];
        int[] bigAfter;
        for (int i = 0; i < SMALL; i++) {
            int a = rand.nextInt((int) Math.pow(10, SMALL_BOUND));
            smallInts[i] = a;
            smallBeforeInts[i] = a;
        }
        for (int i = 0; i < BIG; i++) {
            int a = rand.nextInt((int) Math.pow(10, BIG_BOUND));
            bigInts[i] = a;
            bigBeforeInts[i] = a;
        }
        long start = System.currentTimeMillis();
        smallAfter = Sorting.radixsort(smallInts);
        long smallTime = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        bigAfter = Sorting.radixsort(bigInts);
        long bigTime = System.currentTimeMillis() - start;
        assertArrayEquals(smallInts, smallBeforeInts);
        assertArrayEquals(bigInts, bigBeforeInts);
        for (int i = 0; i < SMALL - 1; i++) {
            assertTrue(smallAfter[i] <= smallAfter[i + 1]);
        }
        for (int i = 0; i < BIG - 1; i++) {
            assertTrue(bigAfter[i] <= bigAfter[i + 1]);
        }
        System.out.println("Small time: " + smallTime);
        System.out.println("Big time: " + bigTime);
        System.out.println("Ratio: " + bigTime / smallTime);
        System.out.println("Expected: " + (BIG * BIG_BOUND / SMALL / SMALL_BOUND));
        assertTrue(bigTime / smallTime < BIG * BIG_BOUND / SMALL / SMALL_BOUND);
    }

}