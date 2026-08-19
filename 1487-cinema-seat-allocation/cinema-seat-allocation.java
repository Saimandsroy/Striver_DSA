class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.computeIfAbsent(row, k -> new HashSet<>()).add(col);
        }

       
        int answer = 2 * n;

        
        for (Set<Integer> seats : map.values()) {

            boolean left = !seats.contains(2) &&
                           !seats.contains(3) &&
                           !seats.contains(4) &&
                           !seats.contains(5);

            boolean middle = !seats.contains(4) &&
                             !seats.contains(5) &&
                             !seats.contains(6) &&
                             !seats.contains(7);

            boolean right = !seats.contains(6) &&
                            !seats.contains(7) &&
                            !seats.contains(8) &&
                            !seats.contains(9);

            if (left && right) {
                
            } 
            else if (left || middle || right) {
                // This row can place only one group
                answer--;
            } 
            else {
                // This row cannot place any group
                answer -= 2;
            }
        }

        return answer;
    }
}