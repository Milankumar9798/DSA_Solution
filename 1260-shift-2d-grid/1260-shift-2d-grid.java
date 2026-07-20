class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int row=grid.length;
        int col=grid[0].length;
        int sz=row*col;
        List<List<Integer>> ans=new ArrayList<>();
        for (int i=0;i<row;i++){
            ans.add(new ArrayList<>());
            for (int j=0;j<col;j++){
                ans.get(i).add(0);
            }
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                int newindex=(i*col+j+k)%sz;
                int newrow=(newindex/col);
                int newcol=newindex%col;
                ans.get(newrow).set(newcol,grid[i][j]);
            }
        }
        return ans;
    }
}