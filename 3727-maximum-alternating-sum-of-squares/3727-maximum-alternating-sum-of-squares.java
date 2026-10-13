class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n=nums.length;
        for(int i=0;i<n;i++){
            nums[i]=Math.abs(nums[i]);
        }
        Arrays.sort(nums);
        int l = 0;
        int r = n - 1;
        long ans = 0;
        boolean plus = true;
        while(l <= r) {
            if(plus) {
                ans += 1L * nums[r] * nums[r];
                r--;
            } else {
                ans -= 1L * nums[l] * nums[l];
                l++;
            }
            plus = !plus;
        }
        return ans;
    }
}