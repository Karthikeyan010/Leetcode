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
}
