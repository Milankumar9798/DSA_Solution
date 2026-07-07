class Solution {
    public int[] plusOne(int[] digits) {
        // if(digits[digits.length-1]==9){
        //     int[] ans=new int[digits.length+1];
        //     for(int i=0;i<digits.length-1;i++){
        //         ans[i]=digits[i];
        //     }
        //     ans[digits.length-1]=1;
        //     ans[digits.length]=0;
        //     return ans;
        // }
        // digits[digits.length-1]=digits[digits.length-1]+1;
        // return digits;

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0; // carry over
        }
        // If all digits were 9
        int[] ans = new int[digits.length + 1];
        ans[0] = 1;
        return ans;
    }
}