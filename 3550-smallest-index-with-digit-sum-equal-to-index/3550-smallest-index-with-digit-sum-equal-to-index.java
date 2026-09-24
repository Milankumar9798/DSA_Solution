class Solution {
    public static int sum(int x){
        int tsum=0;
        while(x>0){
            tsum+=x%10;
            x=x/10;
        }
        return tsum;
    }
    public int smallestIndex(int[] nums) {
        for(int i=0;i<nums.length;i++){
            if(sum(nums[i])==i){
                return i;
            }
        }
        return -1;
    }
}