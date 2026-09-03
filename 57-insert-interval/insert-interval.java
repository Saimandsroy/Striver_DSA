class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        
        List<int []> ans= new ArrayList<>();

        for(int i=0; i<intervals.length; i++){
            
            if(intervals[i][1]<newInterval[0]){
                ans.add(intervals[i]);
            }

            else if(intervals[i][0] > newInterval[1]){

                ans.add(newInterval);
                ans.add(intervals[i]);


                for(int j=i+1; j<intervals.length; j++){
                    ans.add(intervals[j]);
                }

                return ans.toArray(new int[ans.size()][]);
            }

            else{
                newInterval[0]=Math.min(newInterval[0], intervals[i][0]);
                newInterval[1]=Math.max(newInterval[1], intervals[i][1]);
            }
    
        }

         ans.add(newInterval);

        return ans.toArray(new int[ans.size()][]);
    }
}