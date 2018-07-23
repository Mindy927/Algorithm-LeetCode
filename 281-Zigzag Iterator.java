/*
Given two 1d vectors, implement an iterator to return their elements alternately.

Example:

Input:
v1 = [1,2]
v2 = [3,4,5,6] 

Output: [1,3,2,4,5,6]

Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,3,2,4,5,6].
Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question:
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:

Input:
[1,2,3]
[4,5,6,7]
[8,9]

Output: [1,4,8,2,5,9,3,6,7].

Author: Mindy927*/

//Method 1
public class ZigzagIterator {
    boolean nextV1; //nextV1 is true if access v1 next
    int index1, index2;
    List<Integer> v1,v2;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
         nextV1 = v1!=null? true:false;
        index1 = 0;
        index2 = 0;
        this.v1 = v1;
        this.v2 = v2;
    }

    public int next() {  
        int res;
        if (nextV1 && index1 < v1.size()){
            res = v1.get(index1++);
            if (index2!= v2.size()) nextV1 = false;
        } else {
            res = v2.get(index2++);
            nextV1 = true;
        }
        return res;
    }

    public boolean hasNext() {
        return index1!=v1.size() || index2!=v2.size();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */


 //Method2 :Two iterators, one for each list. Switching them before reading the next number 

 public class ZigzagIterator {

    private Iterator<Integer> i, j, tmp;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i = v2.iterator();
        j = v1.iterator();
    }

    public int next() {
        if (j.hasNext()) { tmp = j; j = i; i = tmp; }
        return i.next();
    }

    public boolean hasNext() {
        return i.hasNext() || j.hasNext();
    }
}