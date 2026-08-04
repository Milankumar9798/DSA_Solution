class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> list=new ArrayList<>();
        HashSet<Integer> set=new HashSet<>();
        // Arrays.sort(nums);
        // int min=nums[0];
        // int max=nums[nums.length-1];
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        int n=nums.length;
        for(int num:nums){
            min=Math.min(min,num);
            max=Math.max(max,num);
        }
        for(int i=0;i<n;i++){
            set.add(nums[i]);
        }
        for(int i=min;i<=max;i++){
            if(!set.contains(i)){
                list.add(i);
            }
        }
        return list;
    }
}