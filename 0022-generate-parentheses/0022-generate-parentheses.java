class Solution {
    List<String> result=new ArrayList<>();
    public void gp(String st,int op, int cl, int n){
        if(st.length()==2*n){
            result.add(st);
            return;
        }
        if(op<n){
            gp(st+"(",op+1,cl,n);
        }
        if(cl<op){
            gp(st+")",op,cl+1,n);
        }
    }
    public List<String> generateParenthesis(int n) {
        gp("",0 ,0, n);
        return result;
    }
}