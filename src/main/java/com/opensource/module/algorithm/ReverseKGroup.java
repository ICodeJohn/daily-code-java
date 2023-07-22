package com.opensource.module.algorithm;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/21 12:48
 * @Version V1.0
 */
public class ReverseKGroup {
    public static ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(0);
        System.out.println(dummy);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        System.out.println(end);


        while (end != null) {
            for (int i = 0; i < k && end != null; i++) {
                System.out.println(end);
                end = end.next;
                System.out.println(end);
            }
            if (end == null){
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;

        }
        return pre;
    }


    public static void main(String[] args) {
//        ListNode list = new ListNode(1);
//        list.next = new ListNode(2);
//        list.next.next = new ListNode(3);
//        list.next.next.next = new ListNode(4);
//        list.next.next.next.next = new ListNode(5);
//        list.next.next.next.next.next = new ListNode(6);
//        list.next.next.next.next.next.next = new ListNode(7);
//        list.next.next.next.next.next.next.next = new ListNode(8);
//        ListNode listNode = ReverseKGroup.reverseKGroup(list, 3);
    }
}
