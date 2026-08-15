class Solution {
    public int longestSubsequence(int[] nums) {
        int n=nums.length;
        int[] zero=new int[n];
        if(Arrays.equals(nums, zero)){
            return 0;
        }
        int count=0;
        for(int num:nums){
            count^=num;
        }
        if(count!=0){
            return n;
        }
        return n-1;
    }
}