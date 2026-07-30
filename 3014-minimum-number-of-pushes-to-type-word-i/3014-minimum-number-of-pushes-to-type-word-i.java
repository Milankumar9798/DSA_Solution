class Solution {
    public int minimumPushes(String word) {
        int count = 0;
        int n=word.length();
        int[] freq=new int[26];
        for(int i=0;i<n;i++){
            freq[word.charAt(i)-'a']++;
        }
        Arrays.sort(freq);
        int idx=0;
        for(int i=25;i>=0;i--){
            if(freq[i]==0) break;
            int cost=idx/8+1;
            count+=freq[i]*cost;
            idx++;
        }
        return count;
    }
}