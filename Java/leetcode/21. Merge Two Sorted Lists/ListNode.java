//Использовать фиктивный указатель, который всегда указывает на головной узел как на следующий узел

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
        ListNode currentListNode = new ListNode();
        ListNode prevResultListNode = currentListNode;
        if (!(list1 == null && list2 == null)) {
            while (list1 != null && list2 != null) {

                if (list1.val <= list2.val) {
                    currentListNode.next = list1;
                    list1 = list1.next;
                } else {
                    currentListNode.next = list2;
                    list2 = list2.next;
                }

                currentListNode = currentListNode.next;

            }
            if (list1 == null) {
                currentListNode.next = list2;
            } else if (list2 == null) {
                currentListNode.next = list1;
            }

        }
        return prevResultListNode.next;
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
