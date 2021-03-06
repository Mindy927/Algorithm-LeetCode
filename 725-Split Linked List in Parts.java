/*
Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".

The length of each part should be as equal as possible: no two parts should have a size differing by more than 1. This may lead to some parts being null.

The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal parts occurring later.

Return a List of ListNode's representing the linked list parts that are formed.

Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
Example 1:
Input: 
root = [1, 2, 3], k = 5
Output: [[1],[2],[3],[],[]]
Explanation:
The input and each element of the output are ListNodes, not arrays.
For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3, and root.next.next.next = null.
The first element output[0] has output[0].val = 1, output[0].next = null.
The last element output[4] is null, but it's string representation as a ListNode is [].

Author: Mindy927 */

class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        int len = 0;
        ListNode cur = root;
        
        //Get list len
        while (cur!=null){
            cur = cur.next;
            len++;
        }
        
        
        int pLen = len/k;
        int offset = len%k;  //number of parts which with len pLen + 1
        
        ListNode[] result = new ListNode[k];
        cur = root;
        
        for (int i=0; i<k; i++){
            if (cur == null) {
                result[i] = null;
                continue;
            }
            
            ListNode dummy = new ListNode(0);
            dummy.next = cur;
            
            int curLen = pLen;
            if (offset!=0) {
                curLen = pLen + 1;
                offset--;
            }
            
            for (int j=0; j<curLen-1; j++){
                cur = cur.next;
            }
            
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
            result[i] = dummy.next;
         }
        
        return result;
    }
}