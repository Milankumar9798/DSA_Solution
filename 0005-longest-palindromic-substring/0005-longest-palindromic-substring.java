class Solution {
    public int expandfromcenter(String s,int l,int r){
        while(l>=0 && r<s.length() && s.charAt(l)==s.charAt(r)){
            l--;
            r++;
        }
        return r-l-1;
    }


    public String longestPalindrome(String s) {
        int l=0;
        int r=0;
        for(int i=0;i<s.length();i++){
            int l1=expandfromcenter(s,i,i);
            int l2=expandfromcenter(s,i,i+1);
            int len=Math.max(l1,l2);
            if(len>r-l){
                l=i-(len-1)/2;
                r=i+len/2;
            }
        }
        return s.substring(l,r+1);
    }
}