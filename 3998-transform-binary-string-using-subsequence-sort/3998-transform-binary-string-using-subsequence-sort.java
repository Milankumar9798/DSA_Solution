class Solution {
    public boolean[] transformStr(String s, String[] strs) {
        int n = s.length();
        int totalSOnes = 0;
        int[] prefS = new int[n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalSOnes++;
            }
            prefS[i] = totalSOnes;
        }

        boolean[] ans = new boolean[strs.length];

        for (int k = 0; k < strs.length; k++) {
            char[] t = strs[k].toCharArray();
            int reqOnes = totalSOnes;
            int qCount = 0;

            for (char c : t) {
                if (c == '1') reqOnes--;
                else if (c == '?') qCount++;
            }

            if (reqOnes < 0 || qCount < reqOnes) {
                ans[k] = false;
                continue;
            }

            int onesToPlace = reqOnes;
            for (int i = n - 1; i >= 0; i--) {
                if (t[i] == '?') {
                    if (onesToPlace > 0) {
                        t[i] = '1';
                        onesToPlace--;
                    } else {
                        t[i] = '0';
                    }
                }
            }

            boolean valid = true;
            int currTOnes = 0;
            for (int i = 0; i < n; i++) {
                if (t[i] == '1') currTOnes++;
                if (currTOnes > prefS[i]) {
                    valid = false;
                    break;
                }
            }

            ans[k] = valid;
        }

        return ans;
    }
}