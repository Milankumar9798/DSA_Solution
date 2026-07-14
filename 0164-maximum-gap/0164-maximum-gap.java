class Solution {
    public int maximumGap(int[] nums) {
        Arrays.sort(nums);
        if(nums.length<2) return 0;
        int ans=0;
        for(int i=1;i<nums.length;i++){
            ans=Math.max(ans,nums[i]-nums[i-1]);
        }
        return ans;
    }
}