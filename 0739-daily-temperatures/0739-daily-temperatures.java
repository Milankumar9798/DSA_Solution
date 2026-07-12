class Solution {
    public int[] dailyTemperatures(int[] tem) {
        int n=tem.length;
        int[] ans=new int[n];
        Stack<Integer> stk=new Stack<>();
        for(int i=0;i<n;i++){
            while(!stk.isEmpty() && tem[i]>tem[stk.peek()]){
                int j=stk.pop();
                ans[j]=i-j;
            }
            stk.push(i);
        }
        return ans;
    }
}