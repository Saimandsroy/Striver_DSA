import java.util.*;

class Solution {

    private int timer = 0;

    private void dfs(
            int node,
            int parent,
            int[] vis,
            int[] tin,
            int[] low,
            ArrayList<ArrayList<Integer>> adj,
            List<List<Integer>> bridges) {

        vis[node] = 1;

        tin[node] = low[node] = timer;
        timer++;

        for (int next : adj.get(node)) {

            if (next == parent) {
                continue;
            }

            if (vis[next] == 0) {

                dfs(next, node, vis, tin, low, adj, bridges);

                low[node] = Math.min(low[node], low[next]);

                if (low[next] > tin[node]) {
                    bridges.add(Arrays.asList(node, next));
                }

            } else {

                low[node] = Math.min(low[node], tin[next]);
            }
        }
    }

    public List<List<Integer>> criticalConnections(
            int n,
            List<List<Integer>> connections) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (List<Integer> edge : connections) {

            int u = edge.get(0);
            int v = edge.get(1);

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] vis = new int[n];
        int[] tin = new int[n];
        int[] low = new int[n];

        List<List<Integer>> bridges = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (vis[i] == 0) {
                dfs(i, -1, vis, tin, low, adj, bridges);
            }
        }

        return bridges;
    }
}