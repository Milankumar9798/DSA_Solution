class Solution {
    public int minimumPushes(String word) {
        int count = 0;
        int n=word.length();
        for(int i=0;i<n;i++){
            count+=(i/8+1);
        }
        return count;
    }
}