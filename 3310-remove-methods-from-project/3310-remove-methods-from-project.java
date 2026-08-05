class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : invocations) {
            int caller = edge[0], callee = edge[1];
            graph.computeIfAbsent(caller, x -> new ArrayList<>()).add(callee);
        }
        Set<Integer> removed = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(k);
        removed.add(k);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (graph.containsKey(cur)) {
                for (int dep : graph.get(cur)) {
                    if (!removed.contains(dep)) {
                        removed.add(dep);
                        queue.add(dep);
                    }
                }
            }
        }
        for (int[] edge : invocations) {
            int u = edge[0];
            int v = edge[1];

            if (!removed.contains(u) && removed.contains(v)) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++) ans.add(i);
                return ans;
            }
        }
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!removed.contains(i)) {
                remaining.add(i);
            }
        }
        return remaining;
    }
}
