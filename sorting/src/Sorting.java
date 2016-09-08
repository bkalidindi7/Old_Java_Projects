import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;

public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position
     * after sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubblesort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("array is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator is null");
        }
        int len = arr.length;
        while (len != 0) {
            int n = 0;
            for (int i = 1; i < len; i++) {
                if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                    T temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    n = i;
                }
            }
            len = n;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position
     * after sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionsort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("array is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator is null");
        }
        for (int j = 1; j < arr.length; j++) {
            T temp = arr[j];
            int i;
            for (i = j - 1; i >= 0 && comparator.compare(temp,
                    arr[i]) < 0; i--) {
                arr[i + 1] = arr[i];
            }
            arr[i + 1] = temp;
        }
    }

    /**
     * Implement shell sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log(n))
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void shellsort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("array is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator is null");
        }
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                T temp = arr[i];
                int j = i;
                while (j >= gap && comparator.compare(temp,
                        (arr[j - gap])) < 0) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quicksort(T[] arr, Comparator<T> comparator,
            Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("array is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator is null");
        }
        if (rand == null) {
            throw new IllegalArgumentException("random is null");
        }
        recursiveSort(arr, 0, arr.length - 1, comparator, rand);
    }

    /**
     * Recursive aspect of quicksort, where the upper and lower bounds of the
     * array are taken into consideration, and used in the recursive call on
     * the array
     * @param arr array being sorted
     * @param left bound
     * @param right bound
     * @param comparator used to compare the data in the array
     * @param rand the Random object used to select pivots
     * @param <T> data type to sort
     */
    private static <T> void recursiveSort(T[] arr, int left, int right,
                                          Comparator<T> comparator,
                                          Random rand) {
        if (left < right) {
            int rIndex = rand.nextInt(right - left) + left;
            int pivIndex = partition(arr, left, right, rIndex, comparator);
            recursiveSort(arr, left, pivIndex - 1, comparator, rand);
            recursiveSort(arr, pivIndex + 1, right, comparator, rand);
        }
    }

    /**
     * Finds the position of the data in the random index.
     * @param arr array being sorted
     * @param left bound
     * @param right bound
     * @param pivPos pivot selected by random generator
     * @param comparator used to compare the data in the array
     * @param <T> data type to sort
     * @return pivot position for the data
     */
    private static <T> int partition(T[] arr, int left, int right, int pivPos,
                                     Comparator<T> comparator) {
        T piv = arr[pivPos];
        arr[pivPos] = arr[right];
        arr[right] = piv;
        int index = left;
        for (int i = left; i < right; i++) {
            if (comparator.compare(arr[i], piv) <= 0) {
                T temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
                index++;
            }
        }
        T temp = arr[right];
        arr[right] = arr[index];
        arr[index] = temp;
        return index;
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * ********************* IMPORTANT ************************
     * FAILURE TO DO SO MAY CAUSE ClassCastException AND CAUSE
     * YOUR METHOD TO FAIL ALL THE TESTS FOR MERGE SORT
     * ********************************************************
     *
     * Any duplicates in the array should be in the same relative position
     * after sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergesort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("array is null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator is null");
        }
        if (arr.length > 1) {
            int m = arr.length / 2;
            T[] firstHalf = (T[]) new Object[m];
            for (int i = 0; i < m; i++) {
                firstHalf[i] = arr[i];
            }
            T[] secHalf = (T[]) new Object[arr.length - m];
            for (int i = m; i < arr.length; i++) {
                secHalf[i - m] = arr[i];
            }
            mergesort(firstHalf, comparator);
            mergesort(secHalf, comparator);
            int f = 0;
            int s = 0;
            int mIndex = 0;
            while (f < firstHalf.length && s < secHalf.length) {
                if (comparator.compare(firstHalf[f], secHalf[s]) <= 0) {
                    arr[mIndex] = firstHalf[f];
                    f++;
                } else {
                    arr[mIndex] = secHalf[s];
                    s++;
                }
                mIndex++;
            }
            while (f < firstHalf.length) {
                arr[mIndex] = firstHalf[f];
                f++;
                mIndex++;
            }
            while (s < secHalf.length) {
                arr[mIndex] = secHalf[s];
                s++;
                mIndex++;
            }
        }
    }

    /**
     * Implement radix sort.
     *
     * Remember you CANNOT convert the ints to strings.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position
     * after sorting as they were before sorting.
     *
     * You may use an ArrayList or LinkedList if you wish,
     * but it may only be used inside radixsort and any radix sort helpers
     * Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] radixsort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("array is null");
        }
        int posVals = 0;
        int negVals = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                negVals++;
            } else {
                posVals++;
            }
        }
        int[] sorted = new int[arr.length];
        int[] negBucket = new int[negVals];
        int[] posBucket = new int[posVals];
        int minVal = 0;
        int maxVal = 0;
        int nIndex = 0;
        int pIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                negBucket[nIndex] = Math.abs(arr[i]);
                nIndex++;
                if (arr[i] < minVal) {
                    minVal = arr[i];
                }
            } else {
                posBucket[pIndex] = arr[i];
                pIndex++;
                if (arr[i] > maxVal) {
                    maxVal = arr[i];
                }
            }
        }
        negBucket = radixHelper(negBucket, minVal);
        posBucket = radixHelper(posBucket, maxVal);
        int sIndex = 0;
        for (int i = negBucket.length - 1; i >= 0; i--, sIndex++) {
            sorted[sIndex] = negBucket[i] * -1;
        }
        for (int i = 0; i < posBucket.length; i++, sIndex++) {
            sorted[sIndex] = posBucket[i];
        }
        return sorted;
    }

    /**
     * Sorts the data using the radixsort algorithm
     * @param sorted the array being sorted, only taking in positive values
     * @param maxVal the highest value the array stores
     * @return sorted array
     */
    private static int[] radixHelper(int[] sorted, int maxVal) {
        int digits = 1;
        int sigFigs = 10;
        for (int i = 0; i < sorted.length; i++) {
            while (maxVal / sigFigs != 0) {
                digits++;
                sigFigs *= 10;
            }
        }
        LinkedList<Integer>[] bucket = new LinkedList[10];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new LinkedList<Integer>();
        }
        for (int i = 0; i < sorted.length; i++) {
            int digIndex = sorted[i] % 10;
            bucket[digIndex].addLast(sorted[i]);
        }
        int sf = 10;
        for (int num = 1; num <= digits; num++) {
            int[] buckSizes = new int[10];
            for (int i = 0; i < bucket.length; i++) {
                buckSizes[i] = bucket[i].size();
            }
            for (int i = 0; i < bucket.length; i++) {
                for (int j = 0; j < buckSizes[i]; j++) {
                    int index = bucket[i].removeFirst();
                    int goes = (index / sf) % 10;
                    bucket[goes].addLast(index);
                }
            }
            sf *= 10;
        }
        int sortedIndex = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i].size() != 0) {
                sorted[sortedIndex] = bucket[i].removeFirst();
                sortedIndex++;
            }
        }
        return sorted;
    }
}
