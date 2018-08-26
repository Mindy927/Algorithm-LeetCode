/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true

Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.

Author: Mindy927 */

class Solution {
    public boolean isNumber(String s) {
        String str = s.trim();
        boolean hasDot = false;
        boolean hasE = false;
        boolean hasDigit = false; 
        boolean hasFirst = false; 
        
        for (int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if (Character.isDigit(c)){
                hasDigit = true;
                hasFirst = true;
                continue;
            }
            switch (c){
                case '.':
                    if (hasDot || hasE) return false;
                    hasDot = true;
                    hasFirst = true;
                    continue;
                    
                case 'e': //after e is a start of new int number
                    if (hasE || !hasDigit) return false;
                    hasE = true;
                    hasDigit = false; //there must be more digits after e
                    hasFirst = false; //we can apply +/- after e
                    continue;
                
                case '+': 
                case '-':
                    if (hasFirst) return false;
                    hasFirst = true;
                    continue;
                    
                default:
                    return false;
            }
        }
        
        if (hasE || hasDot) return hasDigit; //there must has digits either before '.' of  after '.', there must be digits after e
        return str.length()!=0;
        
    }
}