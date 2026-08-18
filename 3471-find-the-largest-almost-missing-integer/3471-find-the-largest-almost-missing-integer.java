class Solution {
    public int largestInteger(int[] nums, int k) {
        // int n=nums.length;
        // int first=nums[0];
        // int last=nums[n-1];
        // int x=0, y=0;
        // if(first==last) return -1;
        // for(int i=1;i<n-1;i++){
        //     if(first==nums[i]) x++;
        //     if(last==nums[i]) y++;
        // }
        // int ans=-1;
        // if (x==0) ans=Math.max(ans, first);
        // if (y==0) ans=Math.max(ans, last);
        // return ans;
        int[] f = new int[51];
        for (int x : nums)
            f[x]++;
        int res = -1, n = nums.length;
        for (int i = 0; i < n; i++) 
            if (k == n || (f[nums[i]] == 1 && (k == 1 || i == 0 || i == n - 1)))
                res = Math.max(res, nums[i]);
        return res;
    }
}