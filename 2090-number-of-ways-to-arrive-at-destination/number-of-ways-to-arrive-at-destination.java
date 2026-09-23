class Solution {

    private static final int MOD = 1000000007;

    class Pair {
        long distance;
        int node;

        Pair(long distance, int node) {
            this.distance = distance;
            this.node = node;
        }
    }

    public int countPaths(int n, int[][] roads) {

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] road : roads) {

            int u = road[0];
            int v = road[1];
            int wt = road[2];

            adj.get(u).add(new Pair(wt, v));
            adj.get(v).add(new Pair(wt, u));
        }

        PriorityQueue<Pair> pq =
            new PriorityQueue<>((a, b) -> Long.compare(a.distance, b.distance));

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        long[] ways = new long[n];

        dist[0] = 0;
        ways[0] = 1;

        pq.add(new Pair(0, 0));

        while (!pq.isEmpty()) {

            Pair current = pq.poll();

            long distance = current.distance;
            int node = current.node;

            if (distance > dist[node]) {
                continue;
            }

            for (Pair neigh : adj.get(node)) {

                long newDist = distance + neigh.distance;
                int next = neigh.node;

                if (newDist < dist[next]) {

                    dist[next] = newDist;
                    ways[next] = ways[node];

                    pq.add(new Pair(newDist, next));

                } else if (newDist == dist[next]) {

                    ways[next] = (ways[next] + ways[node]) % MOD;
                }
            }
        }

        return (int) ways[n - 1];
    }
}