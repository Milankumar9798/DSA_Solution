class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long[] last = new long[26];
        long count = 1;
        for (char c : s.toCharArray()) {
            int ch = c - 'a';
            long newCount = (2 * count - last[ch] + MOD) % MOD;
            last[ch] = count;
            count = newCount;
        }
        return (int) ((count - 1 + MOD) % MOD);
    }
}