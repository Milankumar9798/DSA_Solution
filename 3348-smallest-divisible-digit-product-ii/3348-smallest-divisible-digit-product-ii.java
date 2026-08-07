class Solution {
    public String smallestNumber(String num, long t) {
        long temp = t;
        int a = 0, b = 0, c = 0, d = 0;
        while (temp % 2 == 0) { a++; temp /= 2; }
        while (temp % 3 == 0) { b++; temp /= 3; }
        while (temp % 5 == 0) { c++; temp /= 5; }
        while (temp % 7 == 0) { d++; temp /= 7; }
        if (temp > 1) return "-1";
        int n = num.length();
        int firstZero = num.indexOf('0');
        if (firstZero == -1) {
            int ca = 0, cb = 0, cc = 0, cd = 0;
            for (int i = 0; i < n; i++) {
                int digit = num.charAt(i) - '0';
                ca += f2(digit); cb += f3(digit);
                cc += f5(digit); cd += f7(digit);
            }
            if (ca >= a && cb >= b && cc >= c && cd >= d) {
                return num;
            }
        }
        int[] prefA = new int[n + 1];
        int[] prefB = new int[n + 1];
        int[] prefC = new int[n + 1];
        int[] prefD = new int[n + 1];

        int limit = (firstZero == -1) ? n : firstZero;
        for (int i = 0; i < limit; i++) {
            int digit = num.charAt(i) - '0';
            prefA[i + 1] = prefA[i] + f2(digit);
            prefB[i + 1] = prefB[i] + f3(digit);
            prefC[i + 1] = prefC[i] + f5(digit);
            prefD[i + 1] = prefD[i] + f7(digit);
        }
        for (int i = Math.min(n - 1, limit); i >= 0; i--) {
            int curDigit = num.charAt(i) - '0';
            for (int nextDigit = curDigit + 1; nextDigit <= 9; nextDigit++) {
                int remA = a - (prefA[i] + f2(nextDigit));
                int remB = b - (prefB[i] + f3(nextDigit));
                int remC = c - (prefC[i] + f5(nextDigit));
                int remD = d - (prefD[i] + f7(nextDigit));

                int remLen = n - 1 - i;
                if (minDigits(remA, remB, remC, remD) <= remLen) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num.substring(0, i));
                    sb.append(nextDigit);
                    sb.append(getSmallestSuffix(remA, remB, remC, remD, remLen));
                    return sb.toString();
                }
            }
        }
        int targetLen = Math.max(n + 1, minDigits(a, b, c, d));
        return getSmallestSuffix(a, b, c, d, targetLen);
    }
    private int f2(int d) { return (d == 2 || d == 6) ? 1 : (d == 4 ? 2 : (d == 8 ? 3 : 0)); }
    private int f3(int d) { return (d == 3 || d == 6) ? 1 : (d == 9 ? 2 : 0); }
    private int f5(int d) { return d == 5 ? 1 : 0; }
    private int f7(int d) { return d == 7 ? 1 : 0; }
    private int minDigits(int r2, int r3, int r5, int r7) {
        r2 = Math.max(0, r2);
        r3 = Math.max(0, r3);
        r5 = Math.max(0, r5);
        r7 = Math.max(0, r7);
        int count = r7 + r5;
        int c9 = r3 / 2;
        int rem3 = r3 % 2;
        int c8 = r2 / 3;
        int rem2 = r2 % 3;
        count += c9 + c8;
        if (rem2 == 1 && rem3 == 1) { 
            count += 1;
        } else if (rem2 == 2 && rem3 == 1) { 
            count += 2;
        } else {
            if (rem2 > 0) count += 1;
            if (rem3 > 0) count += 1;
        }
        return count;
    }
    private String getSmallestSuffix(int r2, int r3, int r5, int r7, int length) {
        StringBuilder sb = new StringBuilder();
        int cur2 = r2, cur3 = r3, cur5 = r5, cur7 = r7;
        for (int i = 0; i < length; i++) {
            int remLen = length - 1 - i;
            for (int d = 1; d <= 9; d++) {
                int next2 = cur2 - f2(d);
                int next3 = cur3 - f3(d);
                int next5 = cur5 - f5(d);
                int next7 = cur7 - f7(d);
                if (minDigits(next2, next3, next5, next7) <= remLen) {
                    sb.append(d);
                    cur2 = next2;
                    cur3 = next3;
                    cur5 = next5;
                    cur7 = next7;
                    break;
                }
            }
        }
        return sb.toString();
    }
}