import java.util.List;
import java.util.ArrayList;

public class StringSearching implements StringSearchingInterface {

    @Override
    public List<Integer> kmp(CharSequence pattern, CharSequence text) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern is null");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern length is 0");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text is null");
        }
        if (pattern.length() > text.length()) {
            return new ArrayList<>();
        }
        int patternLen = pattern.length();
        int textLen = text.length();
        int [] ffArr = buildFailureTable(pattern);
        int i = 0;
        int j = 0;
        List<Integer> matches = new ArrayList<Integer>();
        while (i < textLen) {
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == patternLen - 1) {
                    matches.add(i - j);
                    j = ffArr[j - 1];
                } else {
                    i++;
                    j++;
                }
            } else {
                if (j > 0) {
                    j = ffArr[j - 1];
                } else {
                    i++;
                }
            }
        }
        return matches;
    }

    @Override
    public int[] buildFailureTable(CharSequence text) {
        if (text == null) {
            throw new IllegalArgumentException("Text is null");
        }
        int len = text.length();
        int[] ffArr = new int[len];
        ffArr[0] = 0;
        int i = 1;
        int j = 0;
        while (i < len) {
            if (text.charAt(i) == text.charAt(j)) {
                ffArr[i] = j + 1;
                i++;
                j++;
            } else if (j > 0) {
                j = ffArr[j - 1];
            } else {
                ffArr[i] = 0;
                i++;
            }
        }
        return ffArr;
    }

    @Override
    public List<Integer> boyerMoore(CharSequence pattern, CharSequence text) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern is null");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern length is 0");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text is null");
        }
        if (pattern.length() > text.length()) {
            return new ArrayList<>();
        }
        int [] table = buildLastTable(pattern);
        int textLen = text.length();
        int patternLen = pattern.length();
        int i = patternLen - 1;
        int j = patternLen - 1;
        List<Integer> matches = new ArrayList<Integer>();
        while (i <= textLen - 1) {
            char start = text.charAt(i);
            if (start == pattern.charAt(j)) {
                if (j == 0) {
                    matches.add(i);
                    j = patternLen - 1;
                    if (table[pattern.charAt(0)] != 0) {
                        i += table[pattern.charAt(0)] + patternLen - 1;
                    } else {
                        i += 2 * patternLen - 1;
                    }
                } else {
                    i--;
                    j--;
                }
            } else {
                int l = table[start];
                i = i + patternLen - Math.min(j, 1 + l);
                j = patternLen - 1;
            }
        }
        return matches;
    }

    @Override
    public int[] buildLastTable(CharSequence pattern) {
        int[] table = new int[Character.MAX_VALUE + 1];
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern is null");
        }
        if (pattern == "") {
            throw new IllegalArgumentException("Pattern is empty");
        }
        //make all values -1
        for (int i = 0; i < table.length; i++) {
            table[i] = -1;
        }
        for (int i = 0; i < pattern.length(); i++) {
            table[pattern.charAt(i)] = i;
        }
        return table;
    }

    @Override
    public int generateHash(CharSequence current, int length) {
        if (current == null) {
            throw new IllegalArgumentException("Current is null");
        }
        if (length < 0) {
            throw new IllegalArgumentException("Length is negative");
        }
        if (length > current.length()) {
            throw new IllegalArgumentException("Length greater than current");
        }
        int hash = 0;
        for (int i = 0; i < length; i++) {
            hash += current.charAt(i) * power(BASE, (length - 1 - i));
        }
        return hash;
    }

    /**
     * Finds integer of a number with the exponent
     * @param num the number
     * @param exponent power being raised by
     * @return the product
     */
    private static int power(int num, int exponent) {
        int c = num;
        if (exponent == 0) {
            return 1;
        }
        for (int i = 0; i < exponent - 1; i++) {
            num = c * num;
        }
        return num;
    }

    @Override
    public int updateHash(int oldHash, int length, char oldChar, char newChar) {
        if (length < 0) {
            throw new IllegalArgumentException("Length is negative");
        }
        int bo = BASE * oldHash;
        int ob = BASE * oldChar;
        int power = BASE;
        for (int i = 0; i < length - 2; i++) {
            power *= BASE;
        }
        return (bo - ob * power + newChar);
    }

    @Override
    public List<Integer> rabinKarp(CharSequence pattern, CharSequence text) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern is null");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern length is 0");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text is null");
        }
        if (pattern.length() > text.length()) {
            return new ArrayList<>();
        }
        List<Integer> matches = new ArrayList<Integer>();
        int patHash = generateHash(pattern, pattern.length());
        int ssHash = generateHash(text, pattern.length());
        if (ssHash == patHash) {
            boolean match = true;
            for (int j = 0; j < pattern.length(); j++) {
                if (text.charAt(j) != pattern.charAt(j)) {
                    match = false;
                }
            }
            if (match) {
                matches.add(0);
            }
        }
        for (int i = 0; i < text.length() - pattern.length(); i++) {
            char old = text.charAt(i);
            char update = text.charAt(i + pattern.length());
            ssHash = updateHash(ssHash, pattern.length(), old, update);
            if (ssHash == patHash) {
                boolean match = true;
                for (int j = 0; j < pattern.length(); j++) {
                    if (text.charAt(i + j + 1) != pattern.charAt(j)) {
                        match = false;
                    }
                }
                if (match) {
                    matches.add(i + 1);
                }
            }
        }
        return matches;
    }
}