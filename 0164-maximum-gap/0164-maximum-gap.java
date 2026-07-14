class Solution {
    public int maximumGap(int[] nums) {
        Arrays.sort(nums);
        if(nums.length==1 && nums.length==0){
            return 0;
        }
        int sum=0;
        int ans=0;
        for(int i=0;i<nums.length;i++){
            int j=i+1;
            if(j<nums.length){
                if(nums[j]>nums[i]){
                    sum=nums[j]-nums[i];
                    ans=Math.max(sum,ans);
                    sum=0;
                }
            }
        }
        return ans;
    }
}