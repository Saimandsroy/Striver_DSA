/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {


        int minDist=Integer.MAX_VALUE;
        int maxDist=-1;

        ListNode prev=head;
        ListNode current=head.next;

        int firstCritical=-1;
        int prevCritical=-1;
        int index=1;


        while(current.next != null){

            ListNode next=current.next;

            boolean isCritical=
            (current.val>next.val && current.val > prev.val)||
            (current.val<next.val && current.val<prev.val);

            if(isCritical){

                if(firstCritical==-1){
                    firstCritical=index;
                }

                if(prevCritical!=-1){
                    minDist=Math.min(minDist, index-prevCritical);
                }
                maxDist=index-firstCritical;

                prevCritical=index;
            }

            prev=current;
            current=next;
            index++;

        }

        if(firstCritical==-1 || firstCritical == prevCritical){
            return new int []{-1,-1};
        }

        return new int [] {minDist, maxDist};

    }
}