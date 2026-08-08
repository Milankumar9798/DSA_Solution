class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[] last = new int[m];
        int k = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            while (k >= 0 && word1.charAt(k) != word2.charAt(j)) {
                k--;
            }
            last[j] = k;
            k--;
        }
        int[] result = new int[m];
        int j = 0;
        boolean mismatchUsed = false;
        for (int i = 0; i < n && j < m; i++) {
            boolean isMatch = word1.charAt(i) == word2.charAt(j);
            boolean canMismatch = !mismatchUsed && (j == m - 1 || last[j + 1] > i);
            if (isMatch || canMismatch) {
                if (!isMatch) {
                    mismatchUsed = true;
                }
                result[j++] = i;
            }
        }
        return j == m ? result : new int[0];
    }
}