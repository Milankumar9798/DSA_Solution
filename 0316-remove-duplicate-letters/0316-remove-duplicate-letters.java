class Solution {
    public String removeDuplicateLetters(String s) {
        int[] count=new int[26];
        boolean[] visit=new boolean[26];
        for(int i=0;i<s.length();i++){
            count[s.charAt(i)-'a']++;
        }
        StringBuilder ans=new StringBuilder();
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            count[c-'a']--;
            if(visit[c-'a']) continue;
            while (!stack.isEmpty() && c < stack.peek() && count[stack.peek() - 'a'] > 0) {
                visit[stack.pop() - 'a'] = false;
            }
            stack.push(c);
            visit[c-'a']=true;
        }
        for(char c : stack) ans.append(c);
        return ans.toString();
    }
}