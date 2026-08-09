class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffix = new int[n + 1];
        int[][] memo = new int[n][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }
        return dfs(0, 1, piles, suffix, memo);
    }
    private int dfs(int i, int M, int[] piles, int[] suffix, int[][] memo) {
        if (i >= piles.length) return 0;
        if (memo[i][M] != 0) return memo[i][M];

        int best = 0;
        for (int X = 1; X <= 2 * M && i + X <= piles.length; X++) {
            best = Math.max(best, suffix[i] - dfs(i + X, Math.max(M, X), piles, suffix, memo));
        }
        return memo[i][M] = best;
    }
}