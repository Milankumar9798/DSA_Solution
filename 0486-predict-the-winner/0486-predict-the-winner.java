class Solution {
    public int winner(int first,int second,int[] nums,Integer[][] dp){
        if(first==second){
            return nums[first];
        }
        if(dp[first][second]!=null){
            return dp[first][second];
        }
        int left=nums[first]-winner(first+1,second,nums,dp);
        int right=nums[second]-winner(first,second-1,nums,dp);
        return dp[first][second]=Math.max(left,right);
    }
    public boolean predictTheWinner(int[] nums) {
        int n=nums.length;
        int first=0;
        int second=n-1;
        Integer[][] dp=new Integer[n][n];
        return winner(first,second,nums,dp)>=0;
    }
}