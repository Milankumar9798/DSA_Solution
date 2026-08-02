class Solution {
    // public int winner(int l,int r,int[] nums,Integer[][] dp){
    //     if(l==r){
    //         return nums[l];
    //     }
    //     if(dp[l][r]!=null){
    //         return dp[l][r];
    //     }
    //     int left=nums[l]-winner(l+1,r,nums,dp);
    //     int right=nums[r]-winner(l,r-1,nums,dp);
    //     return dp[l][r]=Math.max(left,right);
    // }
    public boolean stoneGame(int[] piles) {
        return true;
        // int n=piles.length;
        // int l=0;
        // int r=n-1;
        // Integer[][] dp=new Integer[n][n];
        // return winner(l,r,piles,dp)>=0;
    }
}