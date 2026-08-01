/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int count1=countDigit(headA);
        int count2=countDigit(headB);


        ListNode tempA=headA;
        ListNode tempB=headB;


        if(count1> count2){
            int diff= count1-count2;

            while(diff>0){
                tempA=tempA.next;
                diff--;
            }
        }
        else{
            int diff= count2-count1;

            while(diff>0){
                tempB=tempB.next;
                diff--;
            }

        }


        while(tempA != null && tempB != null){

            if(tempA==tempB){
                return tempA;
            }

            tempA=tempA.next;
            tempB=tempB.next;


        }

        return null;



    }

    private int  countDigit(ListNode head){
        ListNode node=head;
        int count =0;
         while(node!=null){
            count++;
            node=node.next;
         }
         return count;
    }
}