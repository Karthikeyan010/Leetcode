package src.slidingwindow;

import java.util.*;

public class Solution {

    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for(char c : t.toCharArray()){
            need.put(c,need.getOrDefault(c, 0) + 1);
        }

        int required = need.size();
        int formed = 0;

        int left =0;
        int minLength = Integer.MAX_VALUE;
        int minStart =0;

        for(int right = 0;right< s.length();right++){
            char rightChar = s.charAt(right);
            window.put(rightChar, window.getOrDefault(rightChar,0) + 1);

            if (need.containsKey(rightChar)
                    && window.get(rightChar).intValue()
                    == need.get(rightChar).intValue()) {

                formed++;
            }

            while(formed == required){
                int currentLength = right - left +1;

                if(currentLength < minLength){
                    minLength = currentLength;
                    minStart = left;
                }

                char leftChar = s.charAt(left);


                window.put(leftChar, window.get(leftChar)-1 );

                if(need.containsKey(leftChar) && window.get(leftChar) < need.get(leftChar)  ){
                    formed--;
                }

                left++;

            }


        }
        if(minLength == Integer.MAX_VALUE){
            return "";
        }

        return s.substring(minStart,minStart + minLength);

    }

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<String>();
        Set<String> reapeated = new HashSet<String>();

        for(int i =0;i<=(s.length()-10);i++){
            String subsequence = s.substring(i,i+10);
            if(seen.contains(subsequence)){
                reapeated.add(subsequence);
            }else{
                seen.add(subsequence);
            }


        }
        return new ArrayList<String>(reapeated);
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {

        TreeSet<Long> window = new TreeSet<Long>();

        for(int i =0 ;i< nums.length;i++){
            long current = nums[i];

            Long candidate= window.ceiling(current - valueDiff);

            if(candidate != null && candidate <= current + valueDiff){
                return true;
            }

            window.add(current);

            if(i>=indexDiff){
                window.remove((long)(nums[i-indexDiff]));
            }
        }
        return false;


    }
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum =0;
        int minLength = Integer.MAX_VALUE;

        for(int right = 0; right < nums.length;right++){
            sum+= nums[right];

            while(sum>= target){
                int currentLength = right - left +1;
                minLength = Math.min(minLength , currentLength);
                sum-= nums[left];
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;


    }
    public int longestSubstring(String s, int k) {
        if(s.length()<k){
            return 0;
        }

        int[] freq=frequency(s);

        for(int i=0;i< s.length();i++){
            char c = s.charAt(i);
            if(freq[c-'a']< k){
                int left=longestSubstring(s.substring(0, i),k);
                int right = longestSubstring(s.substring(i+1),k);

                return Math.max(left, right);

            }
        }
        return s.length();

    }
    private static int[] frequency(String s ){
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        return freq;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n= nums.length;
        int[] result = new int[n - k + 1];
        int resultIndex=0;

        Deque<Integer> deque = new ArrayDeque<>();

        for(int right = 0;right< n;right++){

            while(!deque.isEmpty() && deque.peekFirst() < right - k + 1 ){
                deque.pollFirst();
            }

            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[right] ){
                deque.pollLast();
            }

            deque.offerLast(right);

            if(right >= k-1){
                result[resultIndex++]= nums[deque.peekFirst()];
            }
        }

        return result;

    }


    public int numberOfArithmeticSlices(int[] nums) {
        int current =0;
        int total =0;

        for(int i =2;i<nums.length;i++){
            if(nums[i]-nums[i-1]==nums[i-1]-nums[i-2]){
                current++;
                total+= current;
            }else{
                current=0;
            }
        }
        return total;

    }
}
