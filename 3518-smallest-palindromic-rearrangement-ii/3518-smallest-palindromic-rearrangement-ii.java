import java.util.*;

public class Solution {
    public String smallestPalindrome(String s, long k) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        // 1. Validity Check
        int oddCount = 0;
        char oddChar = '\0';
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                oddChar = (char) (i + 'a');
            }
        }
        if ((n % 2 == 0 && oddCount != 0) || (n % 2 != 0 && oddCount != 1)) {
            return "";
        }

        // 2. Prepare half frequencies
        int halfLen = n / 2;
        int[] halfFreq = new int[26];
        for (int i = 0; i < 26; i++) halfFreq[i] = freq[i] / 2;

        // Precompute combinations C(n, k) capped at k to avoid long overflow
        long[][] nCr = new long[halfLen + 1][halfLen + 1];
        for (int i = 0; i <= halfLen; i++) {
            nCr[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                nCr[i][j] = Math.min(k + 1, nCr[i - 1][j - 1] + nCr[i - 1][j]);
            }
        }

        // Helper function to calculate permutations of current halfFreq
        // total = C(rem, freq[0]) * C(rem - freq[0], freq[1]) * ...
        if (countPermutations(halfFreq, halfLen, nCr, k) < k) {
            return "";
        }

        // 3. Greedy Construction
        StringBuilder half = new StringBuilder();
        int remaining = halfLen;

        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (halfFreq[c] == 0) continue;

                // Try placing char 'c'
                halfFreq[c]--;
                long cnt = countPermutations(halfFreq, remaining - 1, nCr, k);

                if (k <= cnt) {
                    half.append((char) (c + 'a'));
                    remaining--;
                    break; // Picked this character, move to next position
                } else {
                    k -= cnt;
                    halfFreq[c]++; // Backtrack
                }
            }
        }

        // 4. Mirror result
        StringBuilder res = new StringBuilder(half);
        if (n % 2 == 1) res.append(oddChar);
        res.append(new StringBuilder(half).reverse());

        return res.toString();
    }

    private long countPermutations(int[] freq, int totalLen, long[][] nCr, long cap) {
        long permutations = 1;
        int rem = totalLen;
        for (int f : freq) {
            if (f == 0) continue;
            permutations = capMultiply(permutations, nCr[rem][f], cap);
            rem -= f;
        }
        return permutations;
    }

    private long capMultiply(long a, long b, long cap) {
        if (a == 0 || b == 0) return 0;
        if (a > (cap + 1) / b) return cap + 1; // Prevent overflow beyond cap
        return Math.min(cap + 1, a * b);
    }
}