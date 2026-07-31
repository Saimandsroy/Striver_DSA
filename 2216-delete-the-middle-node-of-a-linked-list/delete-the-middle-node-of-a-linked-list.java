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
    public ListNode deleteMiddle(ListNode head) {

       if (head == null || head.next == null) {
            return null;
        }

        ListNode node =head;

        int count =0;


        while(node!=null){
            count+=1;
            node=node.next;
        }

        int mid=count/2;

        ListNode prev=head;

        for(int i=0; i<mid-1; i++){
            prev=prev.next;
        }

        prev.next=prev.next.next;
        return head;
    }
}