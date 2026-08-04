class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prevGroupTail = dummy;
        ListNode current = head;

        while (current != null) {

            ListNode kthNode = getKthNode(current, k);

            if (kthNode == null) {
                break;
            }

            ListNode nextGroup = kthNode.next;
            kthNode.next = null;

            ListNode newHead = reverse(current);

            prevGroupTail.next = newHead;

            current.next = nextGroup;

            prevGroupTail = current;

            current = nextGroup;
        }

        return dummy.next;
    }

    private ListNode getKthNode(ListNode current, int k) {

        ListNode temp = current;

        while (temp != null && k > 1) {
            temp = temp.next;
            k--;
        }

        return temp;
    }

    private ListNode reverse(ListNode current) {

        ListNode prev = null;

        while (current != null) {

            ListNode next = current.next;

            current.next = prev;

            prev = current;

            current = next;
        }

        return prev;
    }
}