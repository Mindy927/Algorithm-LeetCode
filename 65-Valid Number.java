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


/*
-- dont forget to continue for each switch case
-- corner case "."(False)  "3."(True). --> if (hasE || hasDot) return hasDigit
*/

class Solution {
    public boolean isNumber(String s) {
        boolean hasE = false; //has e in any of the previous char
        boolean hasDigit = false; //digit in previous char
        boolean hasFirst = false;
        boolean hasDot = false; //has dot in any of the previous char
        
        s = s.trim();
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if ( Character.isDigit(c) ) {
                hasFirst = true;
                hasDigit = true;
            }
            else if (c == 'e'){  //after e is a start of new int number
                if (hasE || !hasDigit) return false;
                hasE = true;
                hasFirst = false; //we can apply +/- after e
                hasDigit = false; //there must be more digits after e
            }
            else if ( c == '.') {
                if (hasDot || hasE) return false;
                hasDot = true;
                hasFirst = true;
            } 
            else if ( c == '+' || c == '-'){
                if (hasFirst) return false;
                hasFirst = true;              
            }
            else return false;

        }
        
        if (hasE || hasDot) return hasDigit;//there must has digits either before '.' of  after '.', there must be digits after e
        return s.length()!=0;
    }
}
}