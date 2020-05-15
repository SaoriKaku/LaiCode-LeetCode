
/*
In a list of songs, the i-th song has a duration of time[i] seconds. 
Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.

Example 1:
Input: [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60

Example 2:
Input: [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 
Note:
1 <= time.length <= 60000
1 <= time[i] <= 500
*/


// method 1: Math + map
// time complexity: O(n), space complexity: O(n)
class Solution {
    /* 
    num1 + num2 = 60 =>
    mod1 + mod2 = 60
    mod1 = mod2 = 0
    */
    public int numPairsDivisibleBy60(int[] time) {
        if(time == null || time.length == 0) {
            return 0;
        }
        // mod = [0, 59]
        int[] mod = new int[60];
        int count = 0;
        for(int num: time) {
            int currMod = num % 60;
            if(currMod == 0) {
                count += mod[0];
            }
            else {
                count += mod[60 - currMod];
            }
            mod[currMod]++;
        }
        return count;
    }
}


