
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode resultListNode = new ListNode();
        ListNode prevResultListNode = new ListNode(0, resultListNode);
        if (!(list1 == null && list2 == null)) {

            ListNode currentListNode = resultListNode;

            while (list1.next != null || list2.next != null) {
                if (list1.next == null) {
                    currentListNode = list2;
                } else if (list2.next == null) { // идея обращения к currentListNode.next = list1;
                    currentListNode = list1;
                } else {
                    if (list1.val <= list2.val) {
                        currentListNode = list1;
                        list1 = list1.next;
                    } else {
                        currentListNode = list2;
                        list2 = list2.next;
                    }
                }

                currentListNode = currentListNode.next;

            }

        }
        return prevResultListNode;
    }

    public static void main(String[] var0) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        ListNode res = mergeTwoLists(list1, list2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}

// class Solution {
// public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
// ListNode resultListNode = new ListNode();
// if (!(list1 == null && list2 == null)) {

// ListNode currentListNode = resultListNode;

// while (list1.next != null || list2.next != null) {
// if (list1.next == null) {
// currentListNode = list2;
// } else if (list2.next == null) {
// currentListNode = list1;
// } else {
// if (list1.val <= list2.val) {
// currentListNode = list1;
// list1 = list1.next;
// } else {
// currentListNode = list2;
// list2 = list2.next;
// }
// }

// currentListNode = currentListNode.next;

// }

// }
// return resultListNode;
// }

// public static void main(String[] var0) {
// ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
// ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

// ListNode res = mergeTwoLists(list1, list2);
// while (res != null) {
// System.out.println(res.val);
// res = res.next;
// }
// }
// }