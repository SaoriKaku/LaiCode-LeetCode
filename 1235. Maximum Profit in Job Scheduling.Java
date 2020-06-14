
/*
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
You're given the startTime , endTime and profit arrays, you need to output the maximum profit you can take such that there are no 2 jobs in the subset with overlapping time range.
If you choose a job that ends at time X you will be able to start another job that starts at time X.

Example 1:
Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job. 
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.

Example 2:
Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job. 
Profit obtained 150 = 20 + 70 + 60.

Example 3:
Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6
 
Constraints:
1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
1 <= startTime[i] < endTime[i] <= 10^9
1 <= profit[i] <= 10^4
*/


// method 1: DP(1D) + binary search
// time complexity: O(sort: nlogn + dp,binary search: nlogn), space complexity: O(n)
class Solution {
    /* 
    dp[i]: max profit at job i
    dp[i] = Math.max(dp[i-1], dp[j + profit[i]])
    dp[j] + profit[i], take job i, then binary search a job j, j.endTime <= i.startTime
    dp[i-1], not take job i
    */
    class Job {
        int startTime;
        int endTime;
        int profit;
        public Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        if(startTime == null || startTime.length == 0) {
            return 0;
        }
        int n = startTime.length;
        Job[] jobs = new Job[n];
        for(int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, (a, b) -> (a.endTime - b.endTime));
        int[] dp = new int[n];
        dp[0] = jobs[0].profit;
        for(int i = 1; i < n; i++) {
            int j = binarySearch(jobs, i);
            if(j != -1) {
                dp[i] = Math.max(dp[i-1], dp[j] + jobs[i].profit);
            }
            else {
                dp[i] = Math.max(dp[i-1], jobs[i].profit);
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[n-1];
    }
    public int binarySearch(Job[] jobs, int i) {
        int left = 0, right = i - 1;
        int target = jobs[i].startTime;
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            if(jobs[mid].endTime <= target) {
                left = mid;
            }
            else {
                right = mid - 1;
            }
        }
        if(jobs[right].endTime <= target) return right;
        if(jobs[left].endTime <= target) return left;
        return -1;
    }
}





