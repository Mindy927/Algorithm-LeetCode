/*
Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

Author:Mindy927 */


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

/*
Method 1: Recursion Beats 7%
Helper func reclusively flatten if current item is a list not Integer
*/

public class NestedIterator implements Iterator<Integer> {
    public List<Integer> flatten(List<NestedInteger> nestedList) { //recursively flatten nestedList
        List<Integer> result = new ArrayList<>();
        for(NestedInteger k:nestedList){
            if (k.isInteger()) result.add(k.getInteger());
            else {
                List<Integer> temp = flatten(k.getList());
                for (Integer i:temp){
                    result.add(i);
                }
            }
        }
        return result;
    }
    
    List<Integer> list;
    public NestedIterator(List<NestedInteger> nestedList) {
        list = flatten(nestedList);
    }

    @Override
    public Integer next() {
        Integer next = list.get(0);
        list.remove(0);
        return next;
    }

    @Override
    public boolean hasNext() {
        return list.size()!=0 ;
    }
}


/*
Method 2: Stack
*/

public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack = new Stack<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        for (int i=nestedList.size()-1; i>=0; i--){
           stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
       while (!stack.isEmpty()){
           if (stack.peek().isInteger()) return true;
           List<NestedInteger> cur = stack.pop().getList();
           for(int i = cur.size()-1; i>=0; i--){
               stack.push(cur.get(i));
           }
       }
      return false;
    }
}