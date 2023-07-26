package com.opensource.module.algorithm;

import java.util.Objects;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/7/22 16:34
 * @Version V1.0
 */
public class BackendN {
    int solution(ListNode head,int n){
        if(Objects.isNull(head)){
            return -1;
        }
        ListNode p1= head;
        for (int i = 0; i < n-1; i++) {
            p1= p1.next;
            if(Objects.isNull(p1)){
                return -1;
            }
        }
        ListNode p2 = head;
        while(p1.next != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        return  p2.val;
    }
}
