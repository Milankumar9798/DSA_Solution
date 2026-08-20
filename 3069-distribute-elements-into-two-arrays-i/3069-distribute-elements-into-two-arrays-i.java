class Solution {
    public int[] resultArray(int[] nums) {
        int n=nums.length;
        if(n==1) return nums;
        int[] arr1 =new int[n];
        int[] arr2 =new int[n];
        arr1[0]=nums[0];
        arr2[0]=nums[1];
        int j=0 , k=0;
        int[] result=new int[n];
        for(int i=2;i<n;i++){
            if(arr1[j]>arr2[k]){
                j++;
                arr1[j]=nums[i];
            }  
            else {
                k++;
                arr2[k]=nums[i];
            }
        }
        int index=0;
        for(int i=0;i<=j;i++){
            result[index++]=arr1[i];
        }
        for(int i=0;i<=k;i++){
            result[index++]=arr2[i];
        }
        return result;
    }
}