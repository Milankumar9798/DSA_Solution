class Solution {
    private static class RowStatus {
        boolean l = false;
        boolean m = false;
        boolean r = false;
    }
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int total4People = n << 1; 
        Map<Integer, RowStatus> reserved = new HashMap<>();
        for (int[] seatInfo : reservedSeats) {
            int row = seatInfo[0];
            int seat = seatInfo[1];
            RowStatus status = reserved.computeIfAbsent(row, k -> new RowStatus());
            if (seat >= 2 && seat <= 5) status.l = true;
            if (seat >= 4 && seat <= 7) status.m = true;
            if (seat >= 6 && seat <= 9) status.r = true;
        }
        for (RowStatus status : reserved.values()) {
            if (status.l && status.m && status.r) {
                total4People -= 2; 
            } else if (status.l || status.m || status.r) {
                total4People -= 1;
            }
        }
        return total4People;
    }
}