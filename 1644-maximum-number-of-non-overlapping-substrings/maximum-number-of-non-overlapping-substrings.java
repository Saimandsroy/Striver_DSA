class Solution {

    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Step 1: Find first and last occurrence
        for (int i = 0; i < n; i++) {

            int ch = s.charAt(i) - 'a';

            if (first[ch] == -1) {
                first[ch] = i;
            }

            last[ch] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Step 2: Generate all valid minimal intervals
        for (int i = 0; i < n; i++) {

            int ch = s.charAt(i) - 'a';

            // We only need to start from the first occurrence
            if (first[ch] != i) {
                continue;
            }

            int end = last[ch];
            boolean valid = true;

            for (int j = i; j <= end; j++) {

                int current = s.charAt(j) - 'a';

                // This character appeared before i,
                // so we cannot make a valid substring starting at i.
                if (first[current] < i) {
                    valid = false;
                    break;
                }

                // We must include ALL occurrences
                // of this character.
                end = Math.max(end, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{i, end});
            }
        }

        // Step 3: Sort by ending position
        intervals.sort((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }

            return Integer.compare(a[0], b[0]);
        });

        // Step 4: Greedily select non-overlapping intervals
        List<String> answer = new ArrayList<>();

        int prevEnd = -1;

        for (int[] interval : intervals) {

            int start = interval[0];
            int end = interval[1];

            if (start > prevEnd) {

                answer.add(s.substring(start, end + 1));

                prevEnd = end;
            }
        }

        return answer;
    }
}