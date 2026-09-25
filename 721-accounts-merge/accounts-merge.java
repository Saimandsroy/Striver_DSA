import java.util.*;

class DisJointSet {

    ArrayList<Integer> parent = new ArrayList<>();
    ArrayList<Integer> size = new ArrayList<>();

    public DisJointSet(int n) {
        for (int i = 0; i < n; i++) {
            parent.add(i);
            size.add(1);
        }
    }

    public boolean find(int u, int v) {
        return findParent(u) == findParent(v);
    }

    public int findParent(int node) {

        if (parent.get(node) == node) {
            return node;
        }

        int ultimateParent = findParent(parent.get(node));

        parent.set(node, ultimateParent);

        return ultimateParent;
    }

    public void unionBySize(int u, int v) {

        int parentU = findParent(u);
        int parentV = findParent(v);

        if (parentU == parentV) {
            return;
        }

        if (size.get(parentU) < size.get(parentV)) {

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

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        HashMap<String, Integer> map = new HashMap<>();

        int n = accounts.size();

        DisJointSet ds = new DisJointSet(n);

      
        for (int i = 0; i < n; i++) {

            for (int j = 1; j < accounts.get(i).size(); j++) {

                String mail = accounts.get(i).get(j);

                if (!map.containsKey(mail)) {
                    map.put(mail, i);
                } else {
                    ds.unionBySize(i, map.get(mail));
                }
            }
        }

    
        ArrayList<String>[] mergedMail = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            mergedMail[i] = new ArrayList<>();
        }


        for (Map.Entry<String, Integer> it : map.entrySet()) {

            String mail = it.getKey();

            int node = ds.findParent(it.getValue());

            mergedMail[node].add(mail);
        }


        List<List<String>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (mergedMail[i].size() == 0) {
                continue;
            }

            Collections.sort(mergedMail[i]);

            List<String> temp = new ArrayList<>();

            temp.add(accounts.get(i).get(0));

            for (String mail : mergedMail[i]) {
                temp.add(mail);
            }

            ans.add(temp);
        }

        return ans;
    }
}