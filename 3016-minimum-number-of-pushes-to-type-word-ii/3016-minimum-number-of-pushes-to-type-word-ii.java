class Solution {
    public int minimumPushes(String word) {
        int count=0;
        int idx=0;
        int n=word.length();
        int[] freq = new int[26];
        for (char c : word.toCharArray()) freq[c - 'a']++;
        Arrays.sort(freq);
        for (int i = 25; i >= 0 && freq[i] > 0; i--) {
            count += freq[i] * (idx / 8 + 1);
            idx++;
        }
        return count;
    }
}