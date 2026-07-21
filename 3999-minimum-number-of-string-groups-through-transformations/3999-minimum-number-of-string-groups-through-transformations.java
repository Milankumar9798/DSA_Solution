

class Solution {
    public int minimumGroups(String[] words) {
        Set<String> groups = new HashSet<>();
        for (String w : words) {
            String even = extract(w, 0);
            String odd = extract(w, 1);
            String canonEven = booth(even);
            String canonOdd = booth(odd);
            groups.add(canonEven + "|" + canonOdd);
        }
        return groups.size();
    }

    // Extract even or odd subsequence
    private String extract(String s, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < s.length(); i += 2) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    // Booth’s algorithm: minimal rotation in O(n)
    private String booth(String s) {
        if (s.isEmpty()) return "";
        String ss = s + s;
        int n = s.length();
        int i = 0, j = 1, k = 0;
        while (i < n && j < n && k < n) {
            char a = ss.charAt(i + k);
            char b = ss.charAt(j + k);
            if (a == b) {
                k++;
                continue;
            }
            if (a > b) {
                i = i + k + 1;
                if (i <= j) i = j + 1;
            } else {
                j = j + k + 1;
                if (j <= i) j = i + 1;
            }
            k = 0;
        }
        int start = Math.min(i, j);
        return ss.substring(start, start + n);
    }
}
