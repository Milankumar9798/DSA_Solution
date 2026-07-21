class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
    int n = s.length();
    int ones = 0;
    List<int[]> segments = new ArrayList<>();
    for (int i = 0; i < n;) {
        char c = s.charAt(i);
        int j = i;
        while (j < n && s.charAt(j) == c) j++;
        segments.add(new int[]{c - '0', j - i});
        if (c == '1') ones += (j - i);
        i = j;
    }
    int maxGain = 0;
    for (int i = 1; i < segments.size() - 1; i++) {
        if (segments.get(i)[0] == 1) {
            int leftZeros = segments.get(i-1)[0] == 0 ? segments.get(i-1)[1] : 0;
            int rightZeros = segments.get(i+1)[0] == 0 ? segments.get(i+1)[1] : 0;
            maxGain = Math.max(maxGain, leftZeros + rightZeros);
        }
    }
    return ones + maxGain;
    }
}