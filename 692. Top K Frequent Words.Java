
/*
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
*/


// method 1: sort
// time complexity: O(nlogn), space complexity: O(n)
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for(String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        List<String> candidates = new ArrayList<>(map.keySet());
        Collections.sort(candidates, new Comparator<String>() {
            public int compare(String a, String b) {
                int result = map.get(b) - map.get(a);
                if(result == 0) {
                    return a.compareTo(b);
                }
                return result;
            }
        });
        return candidates.subList(0, k);
    }
}


// method 2: minHeap(size = k), result = minHeap.poll(), reverse(result)
// time complexity: O(nlogk), space complexity: O(n)
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for(String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> minHeap = new PriorityQueue<>(k, new Comparator<String>() {
            public int compare(String a, String b) {
                // ascending
                int result = map.get(a) - map.get(b);
                if(result == 0) {
                    // descending
                    return b.compareTo(a);
                }
                return result;
            }
        });
        for(String curr: map.keySet()) {
            minHeap.offer(curr);
            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }
        List<String> result = new ArrayList<>();
        while(!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        Collections.reverse(result);
        return result;
    }
}




