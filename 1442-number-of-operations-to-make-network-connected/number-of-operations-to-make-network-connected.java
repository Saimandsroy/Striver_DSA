

class disJoint {

    ArrayList<Integer> parent = new ArrayList<>();
    ArrayList<Integer> size = new ArrayList<>();

    public disJoint(int n) {

        for(int i = 0; i < n; i++) {
            parent.add(i);
            size.add(1);
        }
    }

    public int findParent(int node) {

        if(parent.get(node) == node) {
            return node;
        }

        int ultimateParent = findParent(parent.get(node));

        parent.set(node, ultimateParent);

        return ultimateParent;
    }

    public boolean find(int u, int v) {

        return findParent(u) == findParent(v);
    }

    public void unionBySize(int u, int v) {

        int parentU = findParent(u);
        int parentV = findParent(v);

        if(parentU == parentV) {
            return;
        }

        if(size.get(parentU) < size.get(parentV)) {

            parent.set(parentU, parentV);

            size.set(
                parentV,
                size.get(parentU) + size.get(parentV)
            );

        } else {

            parent.set(parentV, parentU);

            size.set(
                parentU,
                size.get(parentU) + size.get(parentV)
            );
        }
    }
}

class Solution {

    public int makeConnected(int n, int[][] connections) {

        if(connections.length < n - 1) {
            return -1;
        }

        disJoint ds = new disJoint(n);

        int components = n;
        int extraCables = 0;

        for(int[] connection : connections) {

            int u = connection[0];
            int v = connection[1];

            if(ds.find(u, v)) {

                extraCables++;

            } else {

                ds.unionBySize(u, v);
                components--;
            }
        }

        int required = components - 1;

        if(extraCables >= required) {
            return required;
        }

        return -1;
    }
}