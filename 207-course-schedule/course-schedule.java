class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {


        int [] inorder= new int[numCourses];


        List<Integer> [] adj= new ArrayList[numCourses];


        for(int i=0; i<numCourses; i++){
            adj[i]=new ArrayList<>();
        }

        for(int i=0; i<prerequisites.length; i++){
           int course=prerequisites[i][0];
            int prerequisiti= prerequisites[i][1];

            adj[prerequisiti].add(course);
           inorder[course]++;

        }


        Queue<Integer> queue= new LinkedList<>();

        for(int i=0; i<numCourses; i++){
            if(inorder[i]==0){
                queue.add(i);
            }
        }


        int processed=0;

        while(!queue.isEmpty()){

            int node= queue.poll();

            processed++;


            for(int next : adj[node]){

                inorder[next]--;

                if(inorder[next]==0){
                    queue.add(next);
                }
            }

        }

        if(processed==numCourses){
            return true;
        }else{
            return false;
        }
    }
}