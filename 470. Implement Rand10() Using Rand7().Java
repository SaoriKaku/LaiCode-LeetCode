
/*
Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.

Do NOT use system's Math.random().
Example 1:

Input: 1
Output: [7]
Example 2:

Input: 2
Output: [8,4]
Example 3:

Input: 3
Output: [8,1,10]
 
Note:
rand7 is predefined.
Each testcase has one argument: n, the number of times that rand10 is called.
 
Follow up:
What is the expected value for the number of calls to rand7() function?
Could you minimize the number of calls to rand7()?
*/


// method 1: random
// time complexity: O(49 - 41 + 1) = O(9), space complexity: O(1)
/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    /*
       1  2  3  4  5  6  7
    1  1  2  3  4  5  6  7
    2  8  9  10 11 12 13 14
    3  15 16 17 18 19 20 21
    4  22 23 24 25 26 27 28
    5  29 30 31 32 33 34 35
    6  36 37 38 39 40 41 42
    7  43 44 45 46 47 48 49
    
    1  2  3  4  5  6  7
    8  9  10 1  2  3  4
    5  6  7  8  9  10 1
    2  3  4  5  6  7  8
    9  10 1  2  3  4  5
    6  7  8  9  10 1  2
    3  4  5  6  7  8  9
    */
    public int rand10() {
        int num = 41;
        while(num > 40) {
            num = (rand7() - 1) * 7 + rand7();
        }
        return num % 10 == 0 ? 10 : num % 10;
    }
}


