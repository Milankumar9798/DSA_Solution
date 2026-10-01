class Solution {
    public boolean isValid(String s) {
        Stack<Character> stk=new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('){
                stk.push(')');
            }
            else if(ch=='{'){
                stk.push('}');
            }
            else if(ch=='['){
                stk.push(']');
            }
            else{
                if(stk.isEmpty() ||ch!=stk.peek()){
                    return false;
                }
                stk.pop();
            }
        }
        return stk.isEmpty();
    }
}