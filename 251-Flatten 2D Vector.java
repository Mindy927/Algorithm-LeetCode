/*
Implement an iterator to flatten a 2d vector.

Example:

Input: 2d vector =
[
  [1,2],
  [3],
  [4,5,6]
]
Output: [1,2,3,4,5,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,2,3,4,5,6].

Author: Mind927 */

public class Vector2D implements Iterator<Integer> {
    Iterator<List<Integer>> i;
    Iterator<Integer> j;
    
    public Vector2D(List<List<Integer>> vec2d) {
        i = vec2d.iterator();
    }

    @Override
    public Integer next() {
        hasNext();
        return j.next();
    }

    @Override
    public boolean hasNext() {
        while ((j == null || !j.hasNext()) && i.hasNext())  //while loop for case [[3],[], [1,4]]
            j = i.next().iterator();
        return j!=null && j.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
