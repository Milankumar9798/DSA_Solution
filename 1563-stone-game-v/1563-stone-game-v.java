class Solution {
    int[][] dp;
    public int solve(int l,int r, int[] presum){
        if(l>=r){
            return 0;
        }
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        int score=0;
        for(int i=l;i<r;i++){
            int leftsum=presum[i+1]-presum[l];
            int rightsum=presum[r+1]-presum[i+1];
            if(leftsum<rightsum){
                score=Math.max(score,leftsum+solve(l,i,presum));
            }else if(leftsum>rightsum){
                score=Math.max(score,rightsum+solve(i+1,r,presum));
            }else{
                score=Math.max(score,
                      Math.max(
                        leftsum+solve(l,i,presum),
                        rightsum+solve(i+1,r,presum)
                ));
            }
        }
        return dp[l][r]=score;
    }

    public int stoneGameV(int[] stoneValue) {
        int n=stoneValue.length;
        int[] presum=new int[n+1];
        for(int i=0;i<n;i++){
            presum[i+1]=presum[i]+stoneValue[i];
        }
        dp=new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solve(0,n-1,presum);
    }
}