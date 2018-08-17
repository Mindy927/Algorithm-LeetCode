/*
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

Example 1:

add(1); add(3); add(5);
find(4) -> true
find(7) -> false
Example 2:

add(3); add(1); add(2);
find(3) -> true
find(6) -> false

Author: Mindy927*/


//O(1) find
class TwoSum {
    Set<Integer> nums;
    Set<Integer> sum;
    /** Initialize your data structure here. */
    public TwoSum() {
        nums = new HashSet<>();
        sum = new HashSet<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (nums.contains(number)) {
            sum.add(number * 2);
            return;
        }
        for (int num:nums){
            sum.add(num + number); //add to sum first, sum of current number with previous numbers
        }
        nums.add(number);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        return sum.contains(value);
    }
}

//O(1) add
class TwoSum {
    Map<Integer, Integer> map;
    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (!map.containsKey(number)){
            map.put(number, 1);
        } else if (map.get(number) == 1){
            map.put(number, 2);
        }
        return;
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (int num1:map.keySet()){
            int num2 = value - num1;
            if (map.containsKey(num2)){
                if (num1 != num2 || map.get(num1)==2){
                    return true;
                }
            }
        } 
        return false;
    }
}