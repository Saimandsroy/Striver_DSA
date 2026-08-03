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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy=new ListNode(-1);
        ListNode tail=dummy;


        int carry=0;
        while(l1 != null || l2 != null){

            int x=0;
            if(l1!=null){
                x=l1.val;
            }

            int y=0;
            if(l2!=null){
                y=l2.val;
            }

            int sum=x+y+carry;

            carry=sum/10;
            int digit=sum%10;

            tail.next=new ListNode(digit);
            tail=tail.next;

            if(l1!=null){
                l1=l1.next;
            }

            if(l2!=null){
                l2=l2.next;
            }

        }


    if(carry>0){
        tail.next=new ListNode(carry);
    }

        return dummy.next;



        
    }

    
    
}