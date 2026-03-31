package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

class Solution {
    public static int romanToInt(String s) {
        char[] str =s.toCharArray();

        LinkedHashMap<String, Integer> romanValues=new  LinkedHashMap<String, Integer>();
        romanValues.put("I" , 1);
        romanValues.put("IV" , 4);
        romanValues.put("V" , 5);
        romanValues.put("IX" , 9);
        romanValues.put("X" , 10);
        romanValues.put("XL" , 40);
        romanValues.put("L" , 50);
        romanValues.put("XC" , 90);
        romanValues.put("C" , 100);
        romanValues.put("CD" , 490);
        romanValues.put("D" , 500);
        romanValues.put("CM" , 900);
        romanValues.put("M" , 1000);
        int result= 0;
        for(int i=0;i< str.length;i++){
            String k="";
            if(i<str.length-1) {
                  k = "" + str[i] + str[i + 1];
            }
            if(romanValues.containsKey(k)){
                result=result+romanValues.get(k);
                i=i+1;

            }else{
                result=result + romanValues.get(""+str[i]);
            }

        }

        return result;



    }

    public static String longestCommonPrefix(String[] strs) {
        // Edge case
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Step 1: Take first string as prefix
        String prefix = strs[0];

        // Step 2: Compare with all other strings
        for (int i = 1; i < strs.length; i++) {

            // Step 3: Reduce prefix until it matches start of current string
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);

                // If prefix becomes empty → no common prefix
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result= new ArrayList<>();
        Arrays.sort(nums);
        for(int i =0; i< nums.length-2;i++){
            if(i>0 && nums[i]==nums[i-1] ){
                continue;
            }

            int left = i+1;
            int right = nums.length-1;



            while(left< right){
                int sum= nums[i]+ nums[left]+ nums[right];

                if(sum==0){
                    result.add(Arrays.asList(nums[i],nums[left], nums[right]));

                    while(left<right && nums[left]==nums[left+1]){
                        left++;
                    }
                    while(left<right && nums[right]== nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum<0) {
                    left++;

                } else{
                    right--;

                }
            }
        }



    return result;
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum=nums[0]+ nums[1]+ nums[2];

        for(int i=0;i< nums.length-2;i++){
            int left = i+1;
            int right= nums.length-1;

            while(left<right){
                int sum= nums[i]+ nums[left]+ nums[right];
                if(Math.abs(target-sum)<Math.abs(target-closestSum)){
                    closestSum= sum;
                }
                if(sum< target){
                    left++;
                } else if (sum> target) {
                    right--;

                }else{
                    return target;
                }


            }

        }
        return closestSum;




    }
    public int myAtoi(String s) {
        int i = 0, n = s.length();
        int sign = 1;
        int result = 0;

        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }


    public static void main(String[] args) {
        int[] nums = {0,0,0};
        System.out.println(threeSumClosest(nums, 1));
    }
}