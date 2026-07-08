class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list=new ArrayList<>();
        boolean[] seen=new boolean[nums.length+1];
        for(int i=0;i<nums.length;i++){
            int num=nums[i];
            seen[num]=true;
        } 
        for(int i=1;i<=nums.length;i++){
            if(!seen[i]){
                list.add(i);
            }
        }
        return list;
    }
}