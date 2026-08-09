class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        List<List<Integer>> result=new ArrayList<>();

        List<Integer> current=new ArrayList<>();

        count(candidates, 0, current, target, result);

        return result;
    }


    public void count(int [] candidates, int index, List<Integer>current, int target, List<List<Integer>> result){

        if(target==0){
            result.add(new ArrayList<>(current));
            return;
        }


        if(index==candidates.length || target<0){
            return;
        }

        current.add(candidates[index]);


        count(candidates, index, current,target-candidates[index],result);


        current.remove(current.size()-1);

        count(candidates, index+1, current, target, result);

    }
}