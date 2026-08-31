class Solution {
    public int leastInterval(char[] tasks, int n) {
        
        int [] freq= new int [26];

        for(int task: tasks){
            freq[task-'A']++;
        }

        PriorityQueue<Integer>heap=new PriorityQueue<>(Collections.reverseOrder());

        
        for(int f: freq){
            if(f>0){
                heap.add(f);
            }
        }
        
        int time=0;

        while(!heap.isEmpty()){
            int cycle=n+1;

            List<Integer>temp=new ArrayList<>();

            while(cycle>0 && !heap.isEmpty()){
                int f=heap.poll();
                f--;

                if(f>0){
                    temp.add(f);
                }

                time++;
                cycle--;

            }

            for(int f: temp){
                heap.add(f);
            }

            if(!heap.isEmpty()){
                time+=cycle;
            }
        }
        return time;
    }
}