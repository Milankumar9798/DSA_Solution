class Solution {
    static class SparseTable {
        private final int n;
        private final int[][] st;
        private final int[] logTable;

        public SparseTable(int[] nums) {
            this.n = nums.length;
            if (n == 0) {
                st = new int[0][0];
                logTable = new int[0];
                return;
            }

            logTable = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                logTable[i] = logTable[i / 2] + 1;
            }

            int maxLog = logTable[n] + 1;
            st = new int[maxLog][n];
            System.arraycopy(nums, 0, st[0], 0, n);

            for (int j = 1; j < maxLog; j++) {
                for (int i = 0; i + (1 << j) <= n; i++) {
                    st[j][i] = Math.max(st[j - 1][i], st[j - 1][i + (1 << (j - 1))]);
                }
            }
        }

        public int query(int l, int r) {
            if (l > r || n == 0) return 0;
            int j = logTable[r - l + 1];
            return Math.max(st[j][l], st[j][r - (1 << j) + 1]);
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }
        int[] left0Len = new int[n];
        int[] right0Len = new int[n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                left0Len[i] = (i > 0 && s.charAt(i - 1) == '0') ? left0Len[i - 1] + 1 : 1;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                right0Len[i] = (i < n - 1 && s.charAt(i + 1) == '0') ? right0Len[i + 1] + 1 : 1;
            }
        }
        List<int[]> oneBlocks = new ArrayList<>(); 
        for (int i = 0; i < n; ) {
            if (s.charAt(i) == '1') {
                int start = i;
                while (i < n && s.charAt(i) == '1') {
                    i++;
                }
                oneBlocks.add(new int[]{start, i - 1});
            } else {
                i++;
            }
        }

        int m = oneBlocks.size();
        int[] fullGains = new int[m];

        for (int k = 0; k < m; k++) {
            int bStart = oneBlocks.get(k)[0];
            int bEnd = oneBlocks.get(k)[1];

            if (bStart > 0 && s.charAt(bStart - 1) == '0' && bEnd < n - 1 && s.charAt(bEnd + 1) == '0') {
                fullGains[k] = left0Len[bStart - 1] + right0Len[bEnd + 1];
            } else {
                fullGains[k] = 0;
            }
        }

        SparseTable st = new SparseTable(fullGains);
        List<Integer> result = new ArrayList<>();

        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int first = binarySearchFirst(oneBlocks, l + 1);
            int last = binarySearchLast(oneBlocks, r - 1);

            int maxGain = 0;

            if (first != -1 && last != -1 && first <= last) {
                if (first + 1 <= last - 1) {
                    maxGain = Math.max(maxGain, st.query(first + 1, last - 1));
                }
                for (int k : new int[]{first, last}) {
                    if (k >= first && k <= last) {
                        int bStart = oneBlocks.get(k)[0];
                        int bEnd = oneBlocks.get(k)[1];

                        int l0 = Math.min(left0Len[bStart - 1], bStart - l);
                        int r0 = Math.min(right0Len[bEnd + 1], r - bEnd);

                        maxGain = Math.max(maxGain, l0 + r0);
                    }
                }
            }

            result.add(totalOnes + maxGain);
        }

        return result;
    }

    private int binarySearchFirst(List<int[]> blocks, int minStart) {
        int low = 0, high = blocks.size() - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (blocks.get(mid)[0] >= minStart) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int binarySearchLast(List<int[]> blocks, int maxEnd) {
        int low = 0, high = blocks.size() - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (blocks.get(mid)[1] <= maxEnd) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}