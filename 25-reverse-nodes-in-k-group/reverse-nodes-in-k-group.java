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
    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || k == 1) {
            return head;
        }

        ListNode temp = head;
        int count = 0;

        // Count total nodes
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        int groups = count / k;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prevGroupTail = dummy;
        ListNode current = head;

        while (groups > 0) {

            // Original head of this group
            ListNode groupHead = current;

            ListNode prev = null;
            int c = 0;

            // Reverse exactly k nodes
            while (current != null && c < k) {

                ListNode next = current.next;

                current.next = prev;

                prev = current;
                current = next;

                c++;
            }

            // Connect previous group to new head
            prevGroupTail.next = prev;

            // Connect new tail to remaining list
            groupHead.next = current;

            // Update previous group tail
            prevGroupTail = groupHead;

            groups--;
        }

        return dummy.next;
    }
}