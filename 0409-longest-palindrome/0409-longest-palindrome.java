class Solution {
    public int longestPalindrome(String s) {
        if(s.length()==0){
            return 0;
        }
        if(s.length()==1){
            return 1;
        }
        int[] fre=new int[128];
        for(int i=0;i<s.length();i++){
            fre[s.charAt(i)]++;
        }
        int count=0;
        boolean odd=false;
        for(int i=0;i<fre.length;i++){
            if(fre[i]%2==0){
                count+=fre[i];
            }else{
                count+=fre[i]-1;
                odd=true;
            }
        }
        if(odd) count+=1;
        return count;
    }
}